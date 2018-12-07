package org.owls.voice.webapp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.jpa.JpaRepositoriesAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Service;

@SpringBootApplication
@ComponentScan(
        {
                "org.owls.voice.backend",
                "org.owls.voice.webapp"
        }
)
public class WebApplication {


    public static void main(String[] args) {
        System.out.println("Call http://localhost:8080/index for results ...");

        SpringApplication.run(WebApplication.class, args);
    }



}