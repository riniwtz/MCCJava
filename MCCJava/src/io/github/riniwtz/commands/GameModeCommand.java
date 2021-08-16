package io.github.riniwtz.commands;

public class GameModeCommand extends BaseCommand {
    GameMode gamemode;
    enum GameMode {
        ADVENTURE,
        CREATIVE,
        SPECTATOR,
        SURVIVAL
    }

	private static final int MINIMUM_ARGUMENT = 2;
    private static final int MAXIMUM_ARGUMENT = 3;

    protected void setGameMode(GameMode gamemode) {
        this.gamemode = gamemode;
    }

    @Override
    public void execute(String[] cmd) {
    	if (!(hasCommandHandlerError(cmd))) {
    		
    	}
    }

    @Override
    protected boolean hasCommandHandlerError(String[] cmd) {
        if ((cmd.length < MINIMUM_ARGUMENT) && (cmd.length > MAXIMUM_ARGUMENT)) {

            CommandOutputMessage.printUnknownCommandOutput();
            CommandOutputMessage.printUnknownCommandDefaultOutput(cmd);
            return true;
        }
        return false;
    }
}
