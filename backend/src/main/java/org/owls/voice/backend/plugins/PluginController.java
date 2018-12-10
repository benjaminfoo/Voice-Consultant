package org.owls.voice.backend.plugins;


import org.owls.voice.plugins.api.Command;
import org.owls.voice.plugins.api.SpeechSynthesizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ServiceLoader;

@Component
public class PluginController {

    private PlugInWatcher plugInWatcher;

    @Autowired
    SpeechSynthesizer speechSynthesizer;

    @Autowired
    ApplicationContext applicationContext;

    @Value("${plugins.directory}")
    private String defaultPluginDirectory;

    @PostConstruct
    public void initialize() {
        plugInWatcher = new PlugInWatcher();
        // watchAndHandleChanges("D:\\Temp\\");
    }

    public void watchAndHandleChanges(String path) {
        try {
            plugInWatcher.watch(path);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public List<Command> snapshotPlugins() throws MalformedURLException {
        List<Command> result = new LinkedList<>();
        result.addAll(loadPlugins());
        return result;
    }


    public List<Command> loadPlugins() throws MalformedURLException {
        return loadPlugins(defaultPluginDirectory);
    }

    public List<Command> loadPlugins(String jarName) throws MalformedURLException {
        List<Command> result = new LinkedList<>();
        File loc = new File(jarName);
        System.out.println("Loading plug-in-jar \"" + jarName + "\" @ (" + loc.getAbsolutePath() + ") ...");

        URL locUrl = loc.toURI().toURL();
        URLClassLoader ucl = new URLClassLoader(new URL[]{locUrl});
        ServiceLoader<Command> sl = ServiceLoader.load(Command.class, ucl);
        Iterator<Command> apit = sl.iterator();

        while (apit.hasNext()) {
            Command next = apit.next();
            // ======================================== setup plug-in
            next.setApplicationContext(applicationContext);
            next.setSpeechSynth(speechSynthesizer);
            // ======================================== setup plug-in
            next.start();
            next.setLoaded(true);
            // next.execute();

            System.out.println("Starting plug-in \"" + next.getName() + "\" (" + next.getVersion() + ")");
            result.add(next);
            next.start();

        }
        System.out.println();

        return result;
    }

}