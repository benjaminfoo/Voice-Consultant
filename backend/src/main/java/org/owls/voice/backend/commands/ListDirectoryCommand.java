package org.owls.voice.backend.commands;

import org.owls.voice.backend.speech.synth.SpeechSynthesizer;
import org.springframework.context.ApplicationContext;

import java.io.File;

public class ListDirectoryCommand extends Command {
    public ListDirectoryCommand(String input, ApplicationContext applicationContext) {
        super(input, applicationContext);
    }

    @Override
    public String getName() {
        return "List-directory";
    }

    @Override
    public void start(ApplicationContext context) {

    }

    @Override
    public void execute() {
        SpeechSynthesizer speechSynthesizer = (SpeechSynthesizer) getApplicationContext().getBean("speechSynthesizer");

        try {
            speechSynthesizer.say("Unter dem Pfad root befinden sich folgende Ordner:");
            File f = new File("/");
            for (String file : f.list()) {
                speechSynthesizer.say(file);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void finish() {

    }
}
