package backend.plugins;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.*;
import java.util.Iterator;
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
                    System.out.println("Jar detected - try to load it ... " + c + " ... ");
                    loadPlugin(c.toString());
                }
            }
            k.reset();
        }
    }

    public void loadPlugin(String name) throws MalformedURLException {
        System.out.println("Loading plug-in " + name + " ... ");
        File loc = new File(name);
        URL locUrl = loc.toURI().toURL();
        URLClassLoader ucl = new URLClassLoader(new URL[]{locUrl});
        System.out.println("URL: " + locUrl);

        ServiceLoader<PlugInInterface> sl = ServiceLoader.load(PlugInInterface.class, ucl);
        Iterator<PlugInInterface> apit = sl.iterator();
        while (apit.hasNext())
        {
            System.out.println("Starting plug-in ...");
            PlugInInterface next = apit.next();
            System.out.println("... " + next.getName());
            next.start(null);

        }
    }

}
