package org.owls.voice.backend.plugins;

import org.owls.voice.plugins.api.SpeechSynthesizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.*;

import static com.sun.nio.file.ExtendedWatchEventModifier.FILE_TREE;
import static java.nio.file.StandardWatchEventKinds.*;

@Component
public class PlugInWatcher {

    @Autowired
    SpeechSynthesizer speechSynthesizer;

    @Autowired
    ApplicationContext applicationContext;


    public PlugInWatcher(){
        System.out.println(this.getClass().getSimpleName() + " has been started!");
    }

    public void watch(String path) throws IOException, InterruptedException {
        FileSystem fs = FileSystems.getDefault();
        WatchService ws = fs.newWatchService();
        // Path pTemp = Paths.get("D:/Temp");
        Path pTemp = Paths.get(path);
        pTemp.register(ws, new WatchEvent.Kind[] {ENTRY_MODIFY, ENTRY_CREATE, ENTRY_DELETE}, FILE_TREE);
        while(true) {
            WatchKey k = ws.take();
            for (WatchEvent<?> e : k.pollEvents()) {
                Object c = e.context();
                System.out.printf("%s %d %s\n", e.kind(), e.count(), c);

                if(c.toString().endsWith(".jar")){
                    System.out.println("Jar file detected \"" + c + "\" ... checking for plug-ins ... ");
                    // TODO: REIMPLEMENT THIS ONE LAYER HIGHER!
                    // List<Command> plugInInterfaces = loadPlugins(c.toString());
                    /*
                    for (Command command : plugInInterfaces) {
                        System.out.println("Found interface: " + command.getName());
                    }
                    System.out.println("\tFound " + plugInInterfaces.size() + " Interfaces!");
                    // foundCommand.accept(c);
                    */
                }
            }
            k.reset();
        }
    }


}
