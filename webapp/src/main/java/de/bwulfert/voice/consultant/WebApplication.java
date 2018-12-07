package de.bwulfert.voice.consultant;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class WebApplication {


    private static final Logger log = LoggerFactory.getLogger(WebApplication.class);


    public static void main(String[] args) {
        SpringApplication.run(WebApplication.class, args);
    }



}