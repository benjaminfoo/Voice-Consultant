package de.bwulfert.voiceConsultant.server.plugins;

import java.io.IOException;

public class ExecuteShellScript implements AbstractCommand {

    @Override
    public String getName() {
        return "ExecuteShellScript";
    }

    @Override
    public String onExecute(String userInput) {
        try {
            Runtime.getRuntime().exec(userInput);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "Beende " + userInput;
    }

    @Override
    public String[] getAliases() {
        return new String[]{"mach mal", "starte"};
    }

}
