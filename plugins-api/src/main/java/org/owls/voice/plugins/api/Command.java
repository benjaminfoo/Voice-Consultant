package org.owls.voice.plugins.api;

public interface Command {

    public String getName();

    public default String getVersion() {
        return "0.0.2-built-in";
    };

    public default String getProvider() {
        return "built-in";
    };

    public void start();

    public void execute();

    public void finish();

    public default void unload() {
        System.out.println("Unloading " + getName() + " ... ");
    }

    public default void disable() {
        System.out.println("Disable " + getName() + " ... ");
    }
}
