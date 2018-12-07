package de.bwulfert.voice.consultant.commands;

import lombok.Data;
import lombok.ToString;
import org.springframework.context.ApplicationContext;

import javax.persistence.Entity;

@Entity
@Data
@ToString
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
