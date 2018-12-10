package org.owls.voice.webapp.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    private static final Logger log = LoggerFactory.getLogger(IndexController.class);

    @GetMapping("/")
    public String index(Model model) {
        return "index";
    }

}