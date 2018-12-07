package org.owls.voice.webapp.controller;

import org.owls.voice.backend.persistance.VoiceCommandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PluginController {

    @Autowired
    private VoiceCommandService voiceCommandRepository;

    @GetMapping("/plugins")
    public String listPlugins(Model model) {
        return "plugins";
    }

}
