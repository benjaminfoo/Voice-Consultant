package de.bwulfert.voice.consultant;

import de.bwulfert.voice.consultant.model.VoiceCommand;
import de.bwulfert.voice.consultant.persistance.VoiceCommandRepository;
import de.bwulfert.voice.consultant.speech.recognition.KeywordLauncher;
import marytts.modules.synthesis.Voice;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import javax.persistence.Entity;

@SpringBootApplication
@EnableJpaRepositories (value = "de.bwulfert.voice.consultant.persistance")
@ComponentScan(
        {
                "de.bwulfert.voice.consultant.persistance",
                ""
        }
) //to scan packages mentioned
@EntityScan("de.bwulfert.voice.consultant.model")
public class AssistantApplication {

    private static final Logger log = LoggerFactory.getLogger(AssistantApplication.class);


    @Autowired
	public KeywordLauncher keywordLauncher;

    @Autowired
    @Qualifier("voiceCommandRepository")
	public VoiceCommandRepository voiceCommandRepository;

	public static void main(String[] args) {
		SpringApplication.run(AssistantApplication.class, args);
	}

    @Bean
    public ApplicationRunner applicationRunner(ApplicationContext ctx) {
        return args -> {
            keywordLauncher.init();
            // keywordLauncher.listenForKeyword();


        };
    }

    @Bean
    public CommandLineRunner demo(VoiceCommandRepository repository) {
        return (args) -> {
            // save a couple of customers
            /*
            repository.save(new VoiceCommand("Jack", "Bauer"));
            repository.save(new Customer("Chloe", "O'Brian"));
            repository.save(new Customer("Kim", "Bauer"));
            repository.save(new Customer("David", "Palmer"));
            repository.save(new Customer("Michelle", "Dessler"));
            */
            // fetch all customers
            log.info("Customers found with findAll():");
            log.info("-------------------------------");
            for (VoiceCommand customer : repository.findAll()) {
                log.info(customer.toString());
            }
            log.info("");
        };
    }

}
