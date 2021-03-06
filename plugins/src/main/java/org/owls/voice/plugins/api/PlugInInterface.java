package org.owls.voice.plugins.api;


public interface PlugInInterface {

    public String getName();

    default public String getProvider(){
        return "benjamin w." ;
    }

    default public String getVersion(){
        return "0.0.1-builtin";
    }

    public void start();

    public void execute();

    public void finish();

}
