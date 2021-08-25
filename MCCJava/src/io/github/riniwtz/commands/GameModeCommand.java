package io.github.riniwtz.commands;
import io.github.riniwtz.mcc.World;

public class GameModeCommand extends AbstractBaseCommand {
    private static final int MINIMUM_ARGUMENT = 2;
    private static final int MAXIMUM_ARGUMENT = 3;
    private String gamemode;
    private String playerName;

    public GameModeCommand() {
        execute();
    }
    public void execute() {
        if (cmd.length > 1) gamemode = cmd[1];
        if (cmd.length > MINIMUM_ARGUMENT) playerName = cmd[2];
    	if (!(hasCommandHandlerError(cmd))) {
            switch (gamemode) {
                case "adventure" -> {
                    world.setGameMode(World.GameMode.ADVENTURE);
                    CommandOutputMessage.printSetGameModeMessageOutput();
                }
                case "creative" -> {
                    world.setGameMode(World.GameMode.CREATIVE);
                    CommandOutputMessage.printSetGameModeMessageOutput();
                }
                case "spectator" -> {
                    world.setGameMode(World.GameMode.SPECTATOR);
                    CommandOutputMessage.printSetGameModeMessageOutput();
                }
                case "survival" -> {
                    world.setGameMode(World.GameMode.SURVIVAL);
                    CommandOutputMessage.printSetGameModeMessageOutput();
                }
                default -> {
                    CommandOutputMessage.printIncorrectArgumentCommandMessageOutput();
                    CommandOutputMessage.printUnknownCommandDefaultMessageOutput();
                }
            }
    	}
    }

    protected boolean hasCommandHandlerError(String[] cmd) {
        if ((cmd.length > MINIMUM_ARGUMENT) && (cmd.length <= MAXIMUM_ARGUMENT)) {
            if (!(playerName.equals(player.getPlayerName()))) {
                CommandOutputMessage.printNoPlayerFoundMessageOutput();
                return true;
            }
        }
        if (cmd.length > MAXIMUM_ARGUMENT) {
            CommandOutputMessage.printIncorrectArgumentCommandMessageOutput();
            CommandOutputMessage.printUnknownCommandDefaultMessageOutput();
            return true;
        }
        if (cmd.length < MINIMUM_ARGUMENT) {
            CommandOutputMessage.printUnknownCommandMessageOutput();
            CommandOutputMessage.printUnknownCommandDefaultMessageOutput();
            return true;
        }
        if (!(world.getGameMode() == null)) {
            return gamemode.equals(world.getGameModeName().toLowerCase());
        }
        return false;
    }
}
