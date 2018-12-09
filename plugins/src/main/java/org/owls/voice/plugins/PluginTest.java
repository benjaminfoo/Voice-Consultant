package org.owls.voice.plugins;

import org.owls.voice.plugins.api.PluginController;

import java.io.IOException;

public class PluginTest {

    public static void main(String[] args) throws IOException, InterruptedException {
        PluginController pluginController = new PluginController();
        pluginController.watchAndHandleChanges("D:\\Temp\\");
    }

}