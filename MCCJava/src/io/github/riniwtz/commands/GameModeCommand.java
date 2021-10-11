package io.github.riniwtz.commands;
import io.github.riniwtz.mcc.World;

public class GameModeCommand extends AbstractBaseCommand {
    // Global Private Fields
    private static final int MINIMUM_ARGUMENT = 2;
    private static final int MAXIMUM_ARGUMENT = 3;
    private String gameModeArgument;
    private String playerArgument;

    // Initialization
    public GameModeCommand() {
        AbstractBaseCommand.MINIMUM_ARGUMENT = MINIMUM_ARGUMENT;
        AbstractBaseCommand.MAXIMUM_ARGUMENT = MAXIMUM_ARGUMENT;

        if (cmd.length > 1) gameModeArgument = cmd[1];
        if (cmd.length > MINIMUM_ARGUMENT) playerArgument = cmd[2];
    }

    // Command Error Handler
    private boolean argumentErrorHandler() {
        // Only limits to valid argument length and checks if player doesn't match
        if ((cmd.length > MINIMUM_ARGUMENT) && (cmd.length <= MAXIMUM_ARGUMENT)) {
            if (!playerArgument.equals(player.getPlayerName())) {
                CommandOutputMessage.printNoPlayerFoundMessage();
                return true;
            }
        }
        if (cmd.length > MAXIMUM_ARGUMENT) {
            CommandOutputMessage.printIncorrectArgumentMessage();
            CommandOutputMessage.printUnknownCommandDefaultMessage();
            return true;
        }
        if (cmd.length < MINIMUM_ARGUMENT) {
            CommandOutputMessage.printUnknownCommandMessage();
            CommandOutputMessage.printUnknownCommandDefaultMessage();
            return true;
        }
        if (!(world.getGameMode() == null)) {
            return gameModeArgument.equals(world.getGameModeName().toLowerCase());
        }
        return false;
    }

    // Execution
    public void execute() {
        if (!argumentErrorHandler()) {
            switch (gameModeArgument) {
                case "adventure" -> {
                    world.setGameMode(World.GameMode.ADVENTURE);
                    CommandOutputMessage.printGameModeCommandSuccessMessage();
                }
                case "creative" -> {
                    world.setGameMode(World.GameMode.CREATIVE);
                    CommandOutputMessage.printGameModeCommandSuccessMessage();
                }
                case "spectator" -> {
                    world.setGameMode(World.GameMode.SPECTATOR);
                    CommandOutputMessage.printGameModeCommandSuccessMessage();
                }
                case "survival" -> {
                    world.setGameMode(World.GameMode.SURVIVAL);
                    CommandOutputMessage.printGameModeCommandSuccessMessage();
                }
                default -> {
                    CommandOutputMessage.printIncorrectArgumentMessage();
                    CommandOutputMessage.printUnknownCommandDefaultMessage();
                }
            }
    	}
    }
}
