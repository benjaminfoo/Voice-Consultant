package org.owls.voice.backend.plugins;

import org.springframework.context.ApplicationContext;

public interface PlugInInterface {

    public String getName();

    default public String getProvider(){
        return "benjamin w." ;
    }

    default public String getVersion(){
        return "0.0.1-builtin";
    }

    public void start(ApplicationContext applicationContext);

    public void execute();

    public void finish();

}
