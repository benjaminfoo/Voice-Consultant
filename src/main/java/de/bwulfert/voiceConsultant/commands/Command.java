package de.bwulfert.voiceConsultant.commands;

import org.springframework.context.ApplicationContext;

public abstract class Command {

    private String input;
    private ApplicationContext applicationContext;

    public Command(String input, ApplicationContext applicationContext) {
        this.input = input;
        this.applicationContext = applicationContext;
    }

    public abstract void execute();

    public ApplicationContext getApplicationContext() {
        return applicationContext;
    }
}
