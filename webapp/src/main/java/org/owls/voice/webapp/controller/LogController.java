package org.owls.voice.webapp.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class LogController {

    private static final Logger log = LoggerFactory.getLogger(LogController.class);


    @GetMapping("/log")
    public String listPlugins(Model model) {
        return "log";
    }

}
