package org.owls.voice.backend.plugins;

import org.owls.voice.plugins.api.Command;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.*;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ServiceLoader;

import static com.sun.nio.file.ExtendedWatchEventModifier.FILE_TREE;
import static java.nio.file.StandardWatchEventKinds.*;

public class PlugInWatcher {

    public PlugInWatcher(){
        System.out.println(this.getClass().getSimpleName() + " has been started!");
    }

    public void watch(String path) throws IOException, InterruptedException {
        FileSystem fs = FileSystems.getDefault();
        WatchService ws = fs.newWatchService();
        // Path pTemp = Paths.get("D:/Temp");
        Path pTemp = Paths.get(path);
        pTemp.register(ws, new WatchEvent.Kind[] {ENTRY_MODIFY, ENTRY_CREATE, ENTRY_DELETE}, FILE_TREE);
        while(true)
        {
            WatchKey k = ws.take();
            for (WatchEvent<?> e : k.pollEvents())
            {
                Object c = e.context();
                System.out.printf("%s %d %s\n", e.kind(), e.count(), c);

                if(c.toString().endsWith(".jar")){
                    System.out.println("Jar file detected \"" + c + "\" ... checking for plug-ins ... ");
                    List<Command> plugInInterfaces = loadPlugins(c.toString());
                    for (Command command : plugInInterfaces) {
                        System.out.println("Found interface: " + command.getName());
                    }
                    System.out.println("\tFound " + plugInInterfaces.size() + " Interfaces!");
                    // foundCommand.accept(c);
                }
            }
            k.reset();
        }
    }

    public List<Command> loadPlugins(String jarName) throws MalformedURLException {
        List<Command> result = new LinkedList<>();
        File loc = new File(jarName);
        System.out.println("Loading plug-in-jar \"" + jarName + "\" @ (" + loc.getAbsolutePath() + ") ...");

        URL locUrl = loc.toURI().toURL();
        URLClassLoader ucl = new URLClassLoader(new URL[]{locUrl});
        ServiceLoader<Command> sl = ServiceLoader.load(Command.class, ucl);
        Iterator<Command> apit = sl.iterator();

        while (apit.hasNext())
        {
            Command next = apit.next();
            System.out.println("Starting plug-in \"" + next.getName() + "\" (" + next.getVersion() + ")");
            result.add(next);
            next.start();

        }
        System.out.println();

        return result;
    }

}
