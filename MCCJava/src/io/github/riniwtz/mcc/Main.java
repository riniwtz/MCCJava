package io.github.riniwtz.mcc;
import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.Scanner;

import io.github.riniwtz.commands.CommandOutputMessage;
import io.github.riniwtz.commands.GiveCommand;
import io.github.riniwtz.commands.KillCommand;
import io.github.riniwtz.commands.TimeCommand;

public class Main {
	public static void main(String[] args) throws IOException {
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
				for (String s : cmd = text.split(" ")) {
					switch (s) {
						case "/give" -> new GiveCommand().execute(cmd);
						case "/time" -> new TimeCommand().execute(cmd);
						case "/kill" -> new KillCommand().execute(cmd);
						default -> {
							CommandOutputMessage.printUnknownCommandOutput();
							CommandOutputMessage.printUnknownCommandDefaultOutput(cmd);
						}
					}
					break;
				}
			}
			else
				CommandOutputMessage.printMessageOutput(player, text);
		}
		sc.close();
	}
}
