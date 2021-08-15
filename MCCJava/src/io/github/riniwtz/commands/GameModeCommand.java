package io.github.riniwtz.commands;

public class GameModeCommand extends BaseCommand {
    private static final int MAXIMUM_ARGUMENT = 4;

    @Override
    public void execute(String[] cmd) {

    }

    @Override
    protected boolean hasCommandHandlerError(String[] cmd) {
        if (cmd.length > MAXIMUM_ARGUMENT) {
            CommandOutputMessage.printUnknownCommandOutput();
            CommandOutputMessage.printUnknownCommandDefaultOutput(cmd);
            return true;
        }
        return false;
    }
}