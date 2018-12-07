package org.owls.voice.webapp.controller;

import org.owls.voice.backend.model.VoiceCommand;
import org.owls.voice.backend.persistance.VoiceCommandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class LogController {

    @Autowired
    private VoiceCommandService voiceCommandRepository;

    @GetMapping("/log")
    public String listPlugins(Model model) {
        return "log";
    }

}
