package io.github.riniwtz.main;
import java.util.NoSuchElementException;
import java.util.Scanner;
import io.github.riniwtz.commands.*;
import io.github.riniwtz.mcc.Player;

public class Main implements Runnable {
	// FIXME - Java Reflections (WIP)
	private static final GiveCommand GIVE_COMMAND = new GiveCommand();
	private static final TimeCommand TIME_COMMAND = new TimeCommand();
	private static final KillCommand KILL_COMMAND = new KillCommand();
	private static final GameModeCommand GAME_MODE_COMMAND = new GameModeCommand();
	private static final HelpCommand HELP_COMMAND = new HelpCommand();

	public static final String NAME_ID = "MCCJava";
	public static final String AUTHOR = "riniwtz";
	public static final String VERSION = "Version 1.0";

	private static final Scanner sc = new Scanner(System.in);
	private static final Player player = new Player();

	private static void setCommand(String[] cmd) {
		switch (cmd[0]) {
			case "/give" -> GIVE_COMMAND.execute(cmd);
			case "/time" -> TIME_COMMAND.execute(cmd);
			case "/kill" -> KILL_COMMAND.execute(cmd);
			case "/gamemode" -> GAME_MODE_COMMAND.execute(cmd);
			case "/help" -> HELP_COMMAND.execute(cmd);
			case "/quit" -> System.exit(0);
			default -> {
				CommandOutputMessage.printUnknownCommandMessageOutput();
				CommandOutputMessage.printUnknownCommandDefaultMessageOutput(cmd);
			}
		}
	}

	private static void initializeName() {
		String name;
		boolean hasName = false;
		do {
			System.out.print("Enter your name: ");
			try {
				name = sc.nextLine();
				if (!(name.charAt(0) == '-') && (name.length() >= 3) && (name.length() <= 16)) {
					player.setName(name);
					hasName = true;
				}
				else System.out.println("The length of nickname should be at least 3 and no more than 16 characters, and should not start with the '-' symbol!");
			}
			catch (NoSuchElementException e) {
				e.printStackTrace();
			}
		} while (!hasName);
	}

	public static void printProgramInfo() {
		System.out.println(
				"Minecraft Commands Java [" + NAME_ID + ": " + VERSION + "]\n"
						+ "(c) " + AUTHOR + ". All rights reserved.\n"
						+ "Contact: riniwtzcode@gmail.com\n"
		);
	}

	@Override
	public void run() {
		printProgramInfo();
		initializeName();
		System.out.println("Type a message or a command");
		while (true) {
			System.out.print("> ");
			String text;
			try {
				text = sc.nextLine();
			}
			catch (NoSuchElementException e) {
				e.printStackTrace();
				break;
			}
			// TODO - Add all commands that are possible from Minecraft commands
			String[] cmd;
			if (text.length() > 0) {
				if (text.charAt(0) == '/') {
					cmd = text.split(" ");
					setCommand(cmd);
				}
				else {
					CommandOutputMessage.printMessageOutput(player, text);
				}
			}
		}
		sc.close();
	}

	public static void main(String[] args) {
		Thread thread = new Thread(new Main());
		thread.start();
	}
}
