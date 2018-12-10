package org.owls.voice.plugins.impl;


import org.owls.voice.plugins.api.Command;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateCommand extends Command {


    @Override
    public void execute() {
        SimpleDateFormat sdf = new SimpleDateFormat("HH 'uhr' mm 'am' dd.MM.yyyy");
        getSpeechSynth().say("Die aktuelle Uhrzeit ist " + sdf.format(new Date()));
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
