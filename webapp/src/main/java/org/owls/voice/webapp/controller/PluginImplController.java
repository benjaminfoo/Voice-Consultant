package org.owls.voice.webapp.controller;

import org.owls.voice.backend.plugins.PluginController;
import org.owls.voice.plugins.api.Command;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.net.MalformedURLException;
import java.util.List;

@Controller
public class PluginImplController {

    private static final Logger log = LoggerFactory.getLogger(PluginImplController.class);

    @Autowired
    PluginController pluginController;

    @GetMapping("/plugins")
    public String listPlugins(Model model) {
        log.info("Serving plugins ... / ... ");

        try {
            List<Command> plugins = pluginController.snapshotPlugins();
            model.addAttribute("voiceCommands", plugins);
            model.addAttribute("voiceCount", plugins.size());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        return "plugins";
    }


}
