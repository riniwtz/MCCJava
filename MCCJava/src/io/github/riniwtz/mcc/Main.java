package io.github.riniwtz.mcc;
import java.util.NoSuchElementException;
import java.util.Scanner;

import io.github.riniwtz.commands.CommandOutputMessage;
import io.github.riniwtz.commands.GiveCommand;
import io.github.riniwtz.commands.KillCommand;
import io.github.riniwtz.commands.TimeCommand;

public class Main {
	protected static final GiveCommand GIVE_COMMAND = new GiveCommand();
	protected static final TimeCommand TIME_COMMAND = new TimeCommand();
	protected static final KillCommand KILL_COMMAND = new KillCommand();

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
