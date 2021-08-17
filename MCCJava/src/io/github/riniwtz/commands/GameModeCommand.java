package io.github.riniwtz.commands;
import io.github.riniwtz.mcc.World;

public class GameModeCommand extends AbstractBaseCommand {
    private static final int MINIMUM_ARGUMENT = 2;
    private static final int MAXIMUM_ARGUMENT = 3;
    private String gamemode;
    @Override
    public void execute(String[] cmd) {
        if (cmd.length > 1) gamemode = cmd[1];
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
