package io.github.riniwtz.mcc;
import java.util.NoSuchElementException;
import java.util.Scanner;

import io.github.riniwtz.commands.CommandOutputMessage;
import io.github.riniwtz.commands.GiveCommand;
import io.github.riniwtz.commands.KillCommand;
import io.github.riniwtz.commands.TimeCommand;

public class Main {
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
					case "/give" -> new GiveCommand().execute(cmd);
					case "/time" -> new TimeCommand().execute(cmd);
					case "/kill" -> new KillCommand().execute(cmd);
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
