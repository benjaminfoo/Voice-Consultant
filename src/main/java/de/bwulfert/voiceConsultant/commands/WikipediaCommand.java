package de.bwulfert.voiceConsultant.commands;

import org.springframework.context.ApplicationContext;

public class WikipediaCommand extends Command {

    public WikipediaCommand(String input, ApplicationContext applicationContext) {
        super(input, applicationContext);
    }

    @Override
    public void execute() {

    }

}
