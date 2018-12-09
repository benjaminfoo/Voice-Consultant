package org.owls.voice.plugins.impl;

import org.owls.voice.plugins.api.Command;

public class ListDirectoryCommand extends Command {

    @Override
    public String getName() {
        return "List-directory";
    }

    @Override
    public void start() {

    }


    @Override
    public void execute() {
        /*
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
        */
    }

    @Override
    public void finish() {

    }
}
