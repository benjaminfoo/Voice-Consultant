package org.owls.voice.webapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Map;

@Controller
public class SystemController {


    @GetMapping("/system")
    public String listPlugins(Model model) {
        System.out.println("Serving plugins ... / ... " );

        System.out.println("model is null ? " + (model == null ? "true" : "false"));

        Map<String, String> env = System.getenv();
        for (String envName : env.keySet()) {
            System.out.format("%s=%s%n",
                    envName,
                    env.get(envName));
        }

        model.addAttribute("envVars", env);
        model.addAttribute("envCount", env.size());

        return "system";
    }

}
