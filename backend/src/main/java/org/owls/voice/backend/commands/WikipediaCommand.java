package org.owls.voice.backend.commands;

import org.springframework.context.ApplicationContext;

public class WikipediaCommand extends Command {

    public WikipediaCommand(String input, ApplicationContext applicationContext) {
        super(input, applicationContext);
    }

    @Override
    public String getName() {
        return "Wikipedia";
    }

    @Override
    public void start(ApplicationContext context) {

    }

    @Override
    public void execute() {

    }

    @Override
    public void finish() {

    }

}
