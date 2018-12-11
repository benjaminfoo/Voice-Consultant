package org.owls.voice.backend.plugins;


import org.owls.voice.plugins.api.Command;
import org.owls.voice.plugins.api.SpeechSynthesizer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.*;

@Component
public class PluginController {

    private static final Logger log = LoggerFactory.getLogger(PluginController.class);

    @Autowired
    SpeechSynthesizer speechSynthesizer;

    @Autowired
    ApplicationContext applicationContext;

    @Autowired
    PlugInWatcher plugInWatcher;

    @Value("${plugins.directory}")
    private String defaultPluginDirectory;

    private Set<Command> loadedPlugins;

    public Set<Command> getLoadedPlugins() {
        return loadedPlugins;
    }

    @PostConstruct
    public void initialize() {
        loadedPlugins = new HashSet<>();

        try {
            loadedPlugins.addAll(snapshotPlugins());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        /*
        try {
            plugInWatcher.watch("", new FileEvent() {
                @Override
                public void onFileCreated(File file) {
                    try {
                        List<Command> commands = loadPlugins(file.getAbsolutePath());
                        getLoadedPlugins().addAll(commands);
                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onFileRemoved(File file) {

                }
            });
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        */
    }


    public List<Command> loadPlugins() throws MalformedURLException {
        return loadPlugins(defaultPluginDirectory);
    }

    public List<Command> snapshotPlugins() throws MalformedURLException {
        List<Command> result = new LinkedList<>();
        result.addAll(loadPlugins());
        return result;
    }

    public List<Command> loadPlugins(String jarName) throws MalformedURLException {
        List<Command> loadedPlugins = new LinkedList<>();

        File jarFile = new File(jarName);
        URL jarFileURL = jarFile.toURI().toURL();

        URLClassLoader urlClassLoader = new URLClassLoader(new URL[]{jarFileURL});
        ServiceLoader<Command> serviceLoader = ServiceLoader.load(Command.class, urlClassLoader);

        log.info("Loading plug-in-jar \"" + jarName + "\" @ (" + jarFile.getAbsolutePath() + ") ...");

        Iterator<Command> serviceLoaderIterator = serviceLoader.iterator();

        while (serviceLoaderIterator.hasNext()) {
            Command plugin = serviceLoaderIterator.next();
            log.info("Found plug-in \"" + plugin.getName() + "\" (" + plugin.getVersion() + ") ...");

            // ======================================== setup plug-in
            plugin.setApplicationContext(applicationContext);
            plugin.setSpeechSynth(speechSynthesizer);
            plugin.start();
            plugin.setLoaded(true);
            // plugin.execute();
            // ======================================== setup plug-in

            log.info("Starting plug-in \"" + plugin.getName() + "\" (" + plugin.getVersion() + ")");
            loadedPlugins.add(plugin);
            plugin.start();

        }

        return loadedPlugins;
    }

}