package de.bwulfert.voiceConsultant.commands;

import de.bwulfert.voiceConsultant.speech.synth.SpeechSynthesizer;
import org.springframework.context.ApplicationContext;

public class HelloCommand extends Command {

    public HelloCommand(String input, ApplicationContext applicationContext) {
        super(input, applicationContext);
    }

    @Override
    public void execute() {
        SpeechSynthesizer speechSynthesizer = (SpeechSynthesizer) getApplicationContext().getBean("speechSynthesizer");
        speechSynthesizer.say("Hallo Benutzer!");
    }

}
