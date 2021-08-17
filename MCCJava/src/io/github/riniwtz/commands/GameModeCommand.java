package io.github.riniwtz.commands;
import io.github.riniwtz.mcc.World;

public class GameModeCommand extends AbstractBaseCommand {
    private static final int MINIMUM_ARGUMENT = 2;
    private static final int MAXIMUM_ARGUMENT = 3;
    private String gamemode;
    private String playerName;
    @Override
    public void execute(String[] cmd) {
        if (cmd.length > 1) gamemode = cmd[1];
        if (cmd.length > MINIMUM_ARGUMENT) playerName = cmd[2];
    	if (!(hasCommandHandlerError(cmd))) {
            switch (gamemode) {
                case "adventure" -> {
                    world.setGameMode(World.GameMode.ADVENTURE);
                    CommandOutputMessage.printSetGameModeMessageOutput(world);
                }
                case "creative" -> {
                    world.setGameMode(World.GameMode.CREATIVE);
                    CommandOutputMessage.printSetGameModeMessageOutput(world);
                }
                case "spectator" -> {
                    world.setGameMode(World.GameMode.SPECTATOR);
                    CommandOutputMessage.printSetGameModeMessageOutput(world);
                }
                case "survival" -> {
                    world.setGameMode(World.GameMode.SURVIVAL);
                    CommandOutputMessage.printSetGameModeMessageOutput(world);
                }
                default -> {
                    CommandOutputMessage.printIncorrectArgumentCommandMessageOutput();
                    CommandOutputMessage.printUnknownCommandDefaultMessageOutput(cmd);
                }
            }
    	}
    }
    @Override
    protected boolean hasCommandHandlerError(String[] cmd) {
        if ((cmd.length > MINIMUM_ARGUMENT) && (cmd.length <= MAXIMUM_ARGUMENT)) {
            if (!(playerName.equals(player.getName()))) {
                CommandOutputMessage.printNoPlayerFoundMessageOutput();
                return true;
            }
        }
        if (cmd.length > MAXIMUM_ARGUMENT) {
            CommandOutputMessage.printIncorrectArgumentCommandMessageOutput();
            CommandOutputMessage.printUnknownCommandDefaultMessageOutput(cmd);
            return true;
        }
        if (cmd.length < MINIMUM_ARGUMENT) {
            CommandOutputMessage.printUnknownCommandMessageOutput();
            CommandOutputMessage.printUnknownCommandDefaultMessageOutput(cmd);
            return true;
        }
        if (!(world.getGameMode() == null)) {
            return gamemode.equals(world.getGameModeName().toLowerCase());
        }
        return false;
    }
}
