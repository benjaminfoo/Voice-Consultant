package de.bwulfert.voiceConsultant.server.plugins;

public class KeywordCommand implements AbstractCommand {

    @Override
    public String getName() {
        return "KeywordCommand";
    }

    @Override
    public String onExecute(String userInput) {
        return userInput;
    }

    @Override
    public String[] getAliases() {
        return new String[]{"ey alter", "ey alte"};
    }
}
