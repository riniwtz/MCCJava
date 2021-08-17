package io.github.riniwtz.commands;

public class KillCommand extends AbstractBaseCommand {
	// FIXME - KillCommand.java code
	@Override
	public void execute(String[] cmd) {
		if (cmd.length == 1) {
			player.kill();
			CommandOutputMessage.printKillPlayerMessageOutput(player.getName());
		}

		if (cmd.length == 2) {
			for (String p : player.getPlayers()) {
				if (cmd[1].equals(p)) {
					player.kill(cmd[1]);
					CommandOutputMessage.printKillPlayerMessageOutput(cmd[1]);
				}
			}
		}
	}

	@Override
	protected boolean hasCommandHandlerError(String[] cmd) {
		return false;
	}
}
