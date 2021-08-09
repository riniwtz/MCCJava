package io.github.riniwtz.commands;

public class KillCommand extends BaseCommand {
	// FIXME - KillCommand.java code
	@Override
	public void execute(String[] cmd) {
		if (cmd.length == 1) {
			player.kill();
			CommandOutputMessage.printKillPlayerOutput(player.getName());
		}

		if (cmd.length == 2) {
			for (String p : player.getPlayers()) {
				if (cmd[1].equals(p)) {
					player.kill(cmd[1]);
					CommandOutputMessage.printKillPlayerOutput(cmd[1]);
				}
			}
		}
	}
	
	@Override
	protected void checkCommandLengthError(String[] cmd, int range) {
		if ((cmd.length < range) || (cmd.length > range)) {
			CommandOutputMessage.printUnknownCommandOutput();
			CommandOutputMessage.printUnknownCommandDefaultOutput(cmd);
		}
	}
	
	@Override
	protected void checkCommandLengthError(String[] cmd, int min, int max) {
		if ((cmd.length < 3) || (cmd.length > 4)) {
			CommandOutputMessage.printUnknownCommandOutput();
			CommandOutputMessage.printUnknownCommandDefaultOutput(cmd);
		}
	}
}
