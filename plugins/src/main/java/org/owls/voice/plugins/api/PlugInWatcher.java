package org.owls.voice.plugins.api;

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
                    List<PlugInInterface> plugInInterfaces = loadPlugins(c.toString());
                    for (PlugInInterface plugInInterface : plugInInterfaces) {
                        System.out.println("Found interface: " + plugInInterface.getName());
                    }
                    System.out.println("\tFound " + plugInInterfaces.size() + " Interfaces!");
                    // foundCommand.accept(c);
                }
            }
            k.reset();
        }
    }

    public List<PlugInInterface> loadPlugins(String jarName) throws MalformedURLException {
        List<PlugInInterface> result = new LinkedList<>();
        File loc = new File(jarName);
        System.out.println("Loading plug-in-jar \"" + jarName + "\" @ (" + loc.getAbsolutePath() + ") ...");

        URL locUrl = loc.toURI().toURL();
        URLClassLoader ucl = new URLClassLoader(new URL[]{locUrl});
        ServiceLoader<PlugInInterface> sl = ServiceLoader.load(PlugInInterface.class, ucl);
        Iterator<PlugInInterface> apit = sl.iterator();

        while (apit.hasNext())
        {
            PlugInInterface next = apit.next();
            System.out.println("Starting plug-in " + next.getName() + " - Version: " + next.getVersion());
            result.add(next);
            next.start();

        }
        System.out.println();

        return result;
    }

}
