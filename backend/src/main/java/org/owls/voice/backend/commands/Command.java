package org.owls.voice.backend.commands;

import lombok.Data;
import lombok.ToString;
import org.owls.voice.backend.plugins.PlugInInterface;
import org.springframework.context.ApplicationContext;

import javax.persistence.Entity;

@Entity
@Data
@ToString
public abstract class Command implements PlugInInterface {

    protected String name;
    private ApplicationContext applicationContext;

    public Command(String name, ApplicationContext applicationContext) {
        this.name = name;
        this.applicationContext = applicationContext;
    }

     public ApplicationContext getApplicationContext(){
        return this.applicationContext;
    }

    public abstract void execute();

}
