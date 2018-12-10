package org.owls.voice.plugins;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication()
@ComponentScan()
public class PluginTest {

    private static final Logger log = LoggerFactory.getLogger(PluginTest.class);

    public static void main(String[] args) {

        SpringApplication.run(PluginTest.class, args);

    }

}