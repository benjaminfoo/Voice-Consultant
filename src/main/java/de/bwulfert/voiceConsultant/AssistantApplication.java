package de.bwulfert.voiceConsultant;

import de.bwulfert.voiceConsultant.speech.recognition.KeywordLauncher;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
public class AssistantApplication {

	public AssistantApplication() {
/*
		AssistantServer homeServer = null;
		try {
			homeServer = new AssistantServer();
			homeServer.printStartup();
			homeServer.waitAndHandleConnection();
		} catch (IOException e) {
			e.printStackTrace();
		}
*/
		KeywordLauncher keywordLauncher = new KeywordLauncher();
		try {
			keywordLauncher.listenForKeyword();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static void main(String[] args) {
		SpringApplication.run(AssistantApplication.class, args);
	}
}
