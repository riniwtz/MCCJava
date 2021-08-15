package io.github.riniwtz.mcc;
import java.util.NoSuchElementException;
import java.util.Scanner;

import io.github.riniwtz.commands.*;


/*
- GiveCommand 98% done
- Blocks and Items are ArrayList and is not full list
- TimeCommand (WIP)
- KillCommand (WIP)
- GameModeCommand (WIP)
 */

public class Main {
	// FIXME - Java Reflections (WIP)
	private static final GiveCommand GIVE_COMMAND = new GiveCommand();
	private static final TimeCommand TIME_COMMAND = new TimeCommand();
	private static final KillCommand KILL_COMMAND = new KillCommand();
	private static final GameModeCommand GAME_MODE_COMMAND = new GameModeCommand();

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		Player player = new Player();

		String text;
		String[] cmd;

		while (true) {
			try { text = sc.nextLine(); }
			catch (NoSuchElementException e) {
				e.printStackTrace();
				break;
			}

			// TODO - Add all commands that are possible from Minecraft commands
			if ((text.length() > 0) && (text.charAt(0) == '/')) {
				cmd = text.split(" ");
				switch (cmd[0]) {
					case "/give" -> GIVE_COMMAND.execute(cmd);
					case "/time" -> TIME_COMMAND.execute(cmd);
					case "/kill" -> KILL_COMMAND.execute(cmd);
					case "/gamemode" -> GAME_MODE_COMMAND.execute(cmd);
					default -> {
						CommandOutputMessage.printUnknownCommandOutput();
						CommandOutputMessage.printUnknownCommandDefaultOutput(cmd);
					}
				}
			}
			else
				CommandOutputMessage.printMessageOutput(player, text);
		}
		sc.close();
	}
}
