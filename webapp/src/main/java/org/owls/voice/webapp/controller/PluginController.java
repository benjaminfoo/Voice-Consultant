package org.owls.voice.webapp.controller;

import org.owls.voice.backend.model.VoiceCommand;
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
        System.out.println("Serving plugins ... / ... " );

        System.out.println("model is null ? " + (model == null ? "true" : "false"));

        System.out.println("Counting " + voiceCommandRepository.count() + " models ...");
        Iterable<VoiceCommand> attributeValue = voiceCommandRepository.findAll();
        attributeValue.forEach(vc -> {
            System.out.println("MODEL: "+ vc.toString());
        });
        model.addAttribute("voiceCommands", attributeValue);

        return "plugins";
    }

}
