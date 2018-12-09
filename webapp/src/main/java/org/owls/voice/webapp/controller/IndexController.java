package org.owls.voice.webapp.controller;

import org.owls.voice.backend.model.VoiceCommand;
import org.owls.voice.backend.persistance.VoiceCommandService;
import org.owls.voice.backend.speech.recognition.KeywordLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.annotation.PostConstruct;

@Controller
public class IndexController {

    @Autowired
    private VoiceCommandService voiceCommandRepository;


    @Autowired
    private KeywordLauncher keywordLauncher;

    @PostConstruct
    public void initialize() {
        System.out.println("IndexController started!!!");
    }

    @GetMapping("/")
    public String index(Model model) {


        keywordLauncher.initializeCommands();

        System.out.println("Serving index ... / ... " );

        System.out.println("model is null ? " + (model == null ? "true" : "false"));

        System.out.println("Counting " + voiceCommandRepository.count() + " models ...");
        Iterable<VoiceCommand> attributeValue = voiceCommandRepository.findAll();
        attributeValue.forEach(vc -> {
                System.out.println("MODEL: "+ vc.toString());
            });
            model.addAttribute("voiceCommands", attributeValue);


        return "index";
    }
}