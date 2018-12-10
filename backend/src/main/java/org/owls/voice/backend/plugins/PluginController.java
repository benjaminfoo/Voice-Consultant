package org.owls.voice.backend.plugins;


import org.owls.voice.plugins.api.Command;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.LinkedList;
import java.util.List;

@Component
public class PluginController {

    private ApplicationContext applicationContext;

    private PlugInWatcher plugInWatcher;

    public PluginController() {
    }

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

    public List<Command> snapshotPlugins(String jarName) throws MalformedURLException {
        List<Command> result = new LinkedList<>();
        result.addAll(plugInWatcher.loadPlugins(jarName));
        return result;
    }

}