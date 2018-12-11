package org.owls.voice.backend.plugins;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;

@Component
public class PlugInWatcher {


    private static final Logger log = LoggerFactory.getLogger(PlugInWatcher.class);

    public void watch(String pathText, FileEvent fileEvent) throws IOException, InterruptedException {
        WatchService watchService
                = FileSystems.getDefault().newWatchService();

        Path path = Paths.get(pathText);

        path.register(
                watchService,
                StandardWatchEventKinds.ENTRY_CREATE,
                StandardWatchEventKinds.ENTRY_DELETE,
                StandardWatchEventKinds.ENTRY_MODIFY);

        WatchKey key;
        while ((key = watchService.take()) != null) {
            for (WatchEvent<?> event : key.pollEvents()) {

                File tempFileRef = new File(path.toFile(), event.context().toString());

                // so there has been a file created within the path
                if (event.kind().equals(StandardWatchEventKinds.ENTRY_CREATE)) {
                    if (fileEvent != null) {
                        fileEvent.onFileCreated(tempFileRef);
                    }
                } else if (event.kind().equals(StandardWatchEventKinds.ENTRY_DELETE)) {
                    if (fileEvent != null) {
                        fileEvent.onFileRemoved(tempFileRef);
                    }
                }

            }
            key.reset();
        }
    }

}
