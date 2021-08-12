package io.github.riniwtz.commands;
import io.github.riniwtz.mcc.Player;
import io.github.riniwtz.mcc.World;

public class CommandOutputMessage {
	static String playerName;
	static Player player = new Player();
	public static void printMessageOutput(Player player, String message) {
		System.out.println(("<") + (player.getName()) + (">") + (" ") + (message));
	}
	
	public static void printGivePlayerAmountLimitOutput(String item) {
		System.out.println(("Can't give more than 6400 of [") + (getConvertItemIDToItemName(item)) + ("]"));
	}
	
	public static void printGivePlayerItemOutput(String item, int amount, Player player) {
		System.out.println(("Gave ") + (amount) + (" ") + ("[") + (getConvertItemIDToItemName(item)) + ("] to ") + (player.getName()));
	}

	public static void printTimeOutput(String command, World world) {
		if (command.equals("set") || command.equals("add"))
			System.out.println("Set the time to " + (int) world.getTime());
		
		if (command.equals("query"))
			System.out.println("The time is " + (int) world.getTime()); // getTime() should be query time (WIP)
	}
	
	public static void printKillPlayerOutput(String name) {
		System.out.println((name) + (" fell out of the world"));
		System.out.println(("Killed ") + (name));
	}
	
	public static void printExpectedFloatOutput() {
		System.out.println("Expected float");
	}
	
	public static void printInvalidIntegerOutput(long amount) {
		System.out.println(("Invalid integer '") + (amount) + ("'"));
	}
	
	public static void printExpectedIntegerOutput() {
		System.out.println("Expected integer");
	}
	
	public static void printIntegerIsZeroOutput() {
		System.out.println("Integer must not be less than 1, found 0");
	}
	
	public static void printUnknownItemOutput(String itemName) {
		System.out.println("Unknown item 'minecraft:" + (itemName) + "'");
	}
	
	public static void printUnknownCommandOutput() {
		System.out.println("Unknown or incomplete command, see below for error");
	}
	
	public static void printUnknownCommandDefaultOutput(String[] cmd) {
		StringBuilder out = new StringBuilder();
		for (String c : cmd) out.append(c).append(" ");
		System.out.print(out.substring(0, out.length() - 1) + ("<--[HERE]\n"));
	}

	public static void printNoPlayerFoundOutput() {
		System.out.println("No player was found");
	}

	protected static void printCheckCommandLengthErrorOutput(String[] cmd, int range) {
		if (playerName.equals(player.getName())) {
			if ((cmd.length < range) || (cmd.length > range)) {
				CommandOutputMessage.printUnknownCommandOutput();
				CommandOutputMessage.printUnknownCommandDefaultOutput(cmd);
			}
		}
	}

	protected static void printCheckCommandLengthErrorOutput(String[] cmd, int MIN, int MAX) {
		if (playerName.equals(player.getName())) {
			if ((cmd.length < MIN) || (cmd.length > MAX)) {
				CommandOutputMessage.printUnknownCommandOutput();
				CommandOutputMessage.printUnknownCommandDefaultOutput(cmd);
			}
		}
	}

	protected static void printIncorrectArgumentCommandOutput() {
		System.out.println("Incorrect argument for command");
	}

	private static String getConvertItemIDToItemName(String itemID) {
		String[] itemName = itemID.split("_");
		StringBuilder itemIDBuilder = new StringBuilder();
		for (String i : itemName) {
			itemIDBuilder.append(getUpperCaseFirstChar(i)).append(" ");
		}
		itemID = itemIDBuilder.toString();
		return itemID.substring(0, itemID.length() - 1);
	}

	private static String getUpperCaseFirstChar(String text) {
		return (text.substring(0, 1).toUpperCase()) + (text.substring(1));
	}
}
