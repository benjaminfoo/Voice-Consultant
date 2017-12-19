package de.bwulfert.voiceConsultant.server.plugins;

// IMPLEMENT ME
public class ListenCommand implements AbstractCommand {

    @Override
    public String getName() {
        return "ListenCommand";
    }

    @Override
    public String onExecute(String userInput) {
        return null; // IMPLEMENT ME
    }

    @Override
    public String[] getAliases() {
        return new String[0]; // IMPLEMENT ME
    }
}
