package org.owls.voice.plugins.api;


import java.io.IOException;
import java.net.MalformedURLException;
import java.util.LinkedList;
import java.util.List;

public class PluginController {

    private PlugInWatcher plugInWatcher;

    public PluginController() {
        plugInWatcher = new PlugInWatcher();
    }

    public void watchAndHandleChanges(String path) {
        try {
            plugInWatcher.watch(path);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public List<PlugInInterface> snapshotPlugins(String jarName) throws MalformedURLException {
        List<PlugInInterface> result = new LinkedList<>();
        result.addAll(plugInWatcher.loadPlugins(jarName));
        return result;
    }

}