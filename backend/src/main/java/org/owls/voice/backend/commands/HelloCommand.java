package org.owls.voice.backend.commands;

import org.owls.voice.backend.speech.synth.SpeechSynthesizer;
import org.springframework.context.ApplicationContext;

public class HelloCommand extends Command {

    public HelloCommand(String name, ApplicationContext applicationContext) {
        super(name, applicationContext);
    }

    @Override
    public String getName() {
        return "Hello";
    }

    @Override
    public void start(ApplicationContext context) {

    }

    @Override
    public void execute() {
        SpeechSynthesizer speechSynthesizer = (SpeechSynthesizer) getApplicationContext().getBean("speechSynthesizer");
        speechSynthesizer.say("Hallo Benutzer!");
    }

    @Override
    public void finish() {

    }

}
