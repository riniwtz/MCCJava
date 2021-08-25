package io.github.riniwtz.commands;

public class KillCommand extends AbstractBaseCommand {
	public KillCommand() {
		execute();
	}

	public void execute() {
		if (cmd.length == 1) {
			player.kill();
			CommandOutputMessage.printKillPlayerMessageOutput();
		}

		if (cmd.length == 2) {
			if (cmd[1].equals(player.getPlayerName())) {
				player.kill(cmd[1]);
				CommandOutputMessage.printKillPlayerMessageOutput();
			}
		}
	}
}
