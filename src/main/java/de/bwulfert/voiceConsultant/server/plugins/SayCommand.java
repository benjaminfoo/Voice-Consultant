package de.bwulfert.voiceConsultant.server.plugins;

public class SayCommand implements AbstractCommand {

    @Override
    public String getName() {
        return "SayCommand";
    }


    @Override
    public String onExecute(String userInput) {
        return userInput;
    }

    @Override
    public String[] getAliases() {
        return new String[]{"say", "sag mal", "sag"};
    }
}
