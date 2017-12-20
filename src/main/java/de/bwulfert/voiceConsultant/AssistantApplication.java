package de.bwulfert.voiceConsultant;

import de.bwulfert.voiceConsultant.speech.recognition.KeywordLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class AssistantApplication {

	@Autowired
	public KeywordLauncher keywordLauncher;



	public static void main(String[] args) {
		SpringApplication.run(AssistantApplication.class, args);
	}

	@Bean
	public ApplicationRunner applicationRunner(ApplicationContext ctx) {
		return args -> {
			keywordLauncher.init();
			keywordLauncher.listenForKeyword();
		};
	}

}
