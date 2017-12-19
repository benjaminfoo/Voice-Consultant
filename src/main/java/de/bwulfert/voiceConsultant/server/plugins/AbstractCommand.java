package de.bwulfert.voiceConsultant.server.plugins;

public interface AbstractCommand {

    public default String getName(){
        return "AbstractCommand - renname me!";
    }

    public default void onEnable() {

    }

    public default void onDisable() {

    }

    public default void beforeExecute(String userInput) {

    }

    public String onExecute(String userInput);

    public default void onEnd() {

    }

    public String[] getAliases();

}
