package org.owls.voice.webapp.controller;

import org.owls.voice.backend.persistance.VoiceCommandRepository;
import org.owls.voice.backend.plugins.PluginController;
import org.owls.voice.plugins.api.Command;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.net.MalformedURLException;
import java.util.List;

@Controller
public class PluginImplController {

    @Autowired
    PluginController pluginController;

    @GetMapping("/plugins")
    public String listPlugins(Model model) {
        System.out.println("Serving plugins ... / ... " );

        try {
            List<Command> plugins = pluginController.snapshotPlugins("D:\\Temp\\voice-assistant-plugins-impl.jar");
            model.addAttribute("voiceCommands", plugins);
            model.addAttribute("voiceCount", plugins.size());

        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        /*
        System.out.println("model is null ? " + (model == null ? "true" : "false"));

        Iterable<Command> attributeValue = serviceLoadController.getPlugins();
        attributeValue.forEach(vc -> {
            System.out.println("MODEL: "+ vc.toString());
        });


        org.owls.voice.backend.plugins.PluginController pluginController = new org.owls.voice.backend.plugins.PluginController();
        try {
            List<Command> commands = pluginController.snapshotPlugins("D:\\Temp\\");

            model.addAttribute("voiceCommands", commands);
            model.addAttribute("voiceCount", commands.size());

        } catch (MalformedURLException e) {
            e.printStackTrace();
        }*/

        return "plugins";
    }


}
