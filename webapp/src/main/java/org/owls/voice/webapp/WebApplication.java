package org.owls.voice.webapp;

import org.owls.voice.backend.plugins.PluginController;
import org.owls.voice.backend.speech.recognition.KeywordLauncher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import javax.annotation.PostConstruct;
import java.io.IOException;

@SpringBootApplication
@EntityScan(
        {
                "org.owls.voice.backend.model"
        }
)
@EnableJpaRepositories(
        {
                "org.owls.voice.backend.persistance"
        }
)
@ComponentScan(
        {
                "org.owls.voice.backend.plugins",
                "org.owls.voice.backend.speech.recognition",
                "org.owls.voice.backend.speech.synth",
                "org.owls.voice.webapp",
                "org.owls.voice.webapp.controller",
        }
)
@PropertySource({
        "classpath:application.properties",
        "classpath:webapp.properties",
        "classpath:common.properties"
})
public class WebApplication {

    private static final Logger log = LoggerFactory.getLogger(WebApplication.class);

    @Autowired
    PluginController pluginController;

    @Autowired
    KeywordLauncher keywordLauncher;

    @PostConstruct
    public void initialize() {
        try {
            keywordLauncher.listenForKeyword();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {

        SpringApplication.run(WebApplication.class, args);

        log.info("");
        log.info("Call http://localhost:8080/ for results ...");
        log.info("Call view-source:http://localhost:8080/ to view the markup-code ...");
        log.info("");
    }


}