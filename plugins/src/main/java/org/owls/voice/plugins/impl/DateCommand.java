package org.owls.voice.plugins.impl;


import org.owls.voice.plugins.api.Command;

public class DateCommand extends Command {


    @Override
    public void execute() {
        /*
        SpeechSynthesizer speechSynthesizer = (SpeechSynthesizer) getApplicationContext().getBean("speechSynthesizer");
        SimpleDateFormat sdf = new SimpleDateFormat("HH 'uhr' mm 'am' dd.MM.yyyy");
        speechSynthesizer.say("Die aktuelle Uhrzeit ist " + sdf.format(new Date()));
        */
    }

    @Override
    public String getName() {
        return "Date";
    }

    @Override
    public void start() {
        System.out.println("Initializing DateCommand !");
    }

    @Override
    public void finish() {

    }
}
