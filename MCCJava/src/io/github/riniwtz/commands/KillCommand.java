package io.github.riniwtz.commands;
import io.github.riniwtz.mcc.Player;

public class KillCommand implements Executable {
	Player player = new Player();
	
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

}
