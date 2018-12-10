package org.owls.voice.plugins.impl;


import org.owls.voice.plugins.api.Command;

public class HelloCommand extends Command {


    @Override
    public String getName() {
        return "Hello";
    }

    @Override
    public void start() {
    }

    @Override
    public void execute() {
        getSpeechSynth().say("Hallo Benutzer!");
    }

    @Override
    public void finish() {

    }

}
