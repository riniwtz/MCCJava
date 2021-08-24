package io.github.riniwtz.main;
import java.util.NoSuchElementException;
import java.util.Scanner;

import io.github.riniwtz.commands.*;
import io.github.riniwtz.mcc.Player;

public class Main implements Runnable {
	public static final String NAME_ID = "MCCJava";
	public static final String AUTHOR = "riniwtz";
	public static final String VERSION = "Version 1.0";
	private static final Scanner sc = new Scanner(System.in);
	private static String[] cmd;
	private static Player player = new Player();

	public static void main(String[] args) {
		Thread thread = new Thread(new Main());
		thread.start();
	}

	public static void printProgramInfo() {
		System.out.println(
				"Minecraft Commands Java [" + NAME_ID + ": " + VERSION + "]\n"
						+ "(c) " + AUTHOR + ". All rights reserved.\n"
						+ "Contact: riniwtzcode@gmail.com\n"
		);
	}

	private static void initializeName() {
		boolean hasName = false;
		do {
			System.out.print("Enter your name: ");
			String name = sc.nextLine();
			if (name != null) {
				if (!(name.charAt(0) == '-') && (name.length() >= 3) && (name.length() <= 16)) {
					player.setPlayerName(name);
					hasName = true;
				}
				else System.out.println("The length of nickname should be at least 3 and no more than 16 characters, and should not start with the '-' symbol!");
			}
		} while (!hasName);
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
			if (text.length() > 0) {
				if (text.charAt(0) == '/') {
					cmd = text.split(" ");
					setCommand(cmd);
					runCommand();
				}
				else {
					CommandOutputMessage.printMessageOutput(text);
				}
			}
		}
		sc.close();
	}

	private static void runCommand() {
		switch (cmd[0]) {
			case "/give" -> new GiveCommand(player);
			case "/time" -> new TimeCommand();
			case "/kill" -> new KillCommand();
			case "/gamemode" -> new GameModeCommand();
			case "/help" -> new HelpCommand();
			case "/quit" -> System.exit(0);
			default -> {
				CommandOutputMessage.printUnknownCommandMessageOutput();
				CommandOutputMessage.printUnknownCommandDefaultMessageOutput(cmd);
			}
		}
	}

	private static void setCommand(String[] cmd) {
		AbstractBaseCommand.cmd = cmd;
	}
}
