package io.github.riniwtz.commands;

import java.io.IOException;

public class HelpCommand extends AbstractBaseCommand {
    public String getCommandFromList() {
        int i = 0;
        String[] commandList;
        String line;
        try {
            while ((line = CommandOutputMessage.helpCommandList.readLine()) != null) {
                commandList = new String[i++];
                return commandList[i] = line.substring(1, line.indexOf(" "));

            }
        } catch (NumberFormatException | IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    @Override
    public void execute(String[] cmd) {
        String commandName = "";
        if (cmd.length == 1) commandName = cmd[1];
        CommandOutputMessage.printHelpCommandListMessageOutput();
        // Add a switch statement
    }

    @Override
    protected boolean hasCommandHandlerError(String[] cmd) {
        return false;
    }
}
