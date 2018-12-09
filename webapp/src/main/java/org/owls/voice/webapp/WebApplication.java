package org.owls.voice.webapp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication(scanBasePackages = "org.owls.voice.webapp.controller")
@ComponentScan(
        {
                "org.owls.voice.backend",
                "org.owls.voice.webapp",
                "org.owls.voice.webapp.controller"
        }
)
public class WebApplication {

    private static final Logger log = LoggerFactory.getLogger(WebApplication.class);

    public static void main(String[] args) {

        SpringApplication.run(WebApplication.class, args);

        log.info("");
        log.info("Call http://localhost:8080/ for results ...");
        log.info("Call view-source:http://localhost:8080/ to view the markup-code ...");
        log.info("");


    }



}