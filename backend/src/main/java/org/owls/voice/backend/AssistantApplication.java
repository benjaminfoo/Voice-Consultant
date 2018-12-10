package org.owls.voice.backend;

import org.owls.voice.backend.persistance.VoiceCommandRepository;
import org.owls.voice.backend.speech.recognition.KeywordLauncher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan("org.owls.voice.backend.model")
@EnableJpaRepositories (value = "org.owls.voice.backend.persistance")
@ComponentScan(
        {
                "org.owls.voice.backend.persistance",
                "org.owls.voice.backend.speech.recognition",
                "org.owls.voice.backend.speech.synth",
				"org.owls.voice.backend.plugins",
        }
)
public class AssistantApplication {

    private static final Logger log = LoggerFactory.getLogger(AssistantApplication.class);


    @Autowired
	public KeywordLauncher keywordLauncher;

    @Autowired
	public VoiceCommandRepository voiceCommandRepository;

	public static void main(String[] args) {
		SpringApplication.run(AssistantApplication.class, args);
	}

}
