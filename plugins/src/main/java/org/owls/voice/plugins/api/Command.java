package org.owls.voice.plugins.api;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public abstract class Command implements PlugInInterface {

    protected String name;

    public abstract void execute();

}
