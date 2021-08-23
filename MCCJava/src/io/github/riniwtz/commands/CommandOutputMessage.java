package io.github.riniwtz.commands;
import io.github.riniwtz.mcc.Blocks;
import io.github.riniwtz.mcc.Items;
import io.github.riniwtz.mcc.Player;
import io.github.riniwtz.mcc.World;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Objects;

public class CommandOutputMessage {

	public static void printCommandInsufficientPermissionsMessageOutput() {
		System.out.println("Unknown command or insufficient permissions");
	}

	public static void printHelpCommandMessageOutput(String command, String argument1, String argument2, String argument3) {
		switch (command) {
			case "give" -> System.out.println("/" + command + " " + argument1 + " " + argument2 + " " + argument3);
		}
	}

	protected static final BufferedReader HELP_COMMAND_LIST = new BufferedReader(new InputStreamReader(Objects.requireNonNull(CommandOutputMessage.class.getResourceAsStream("/io/github/riniwtz/resources/help_command_list"))));

	public static void printHelpCommandListMessageOutput() {
		try {
			String line;
			while ((line = HELP_COMMAND_LIST.readLine()) != null) {
				System.out.println(line);
			}
		} catch (NumberFormatException | IOException e) {
			e.printStackTrace();
		}
	}

	public static void printExpectedWhitespaceMessageOutput() {
		System.out.println("Expected whitespace to end one argument, but found trailing data");
	}

	public static void printTickIsNegativeMessageOutput() {
		System.out.println("Tick count must be non-negative");
	}

	public static void printSetGameModeMessageOutput(World world) {
		System.out.println("Set own game mode to " + toUpperCaseFirstChar(world.getGameModeName()) + " Mode");
	}

	public static void printMessageOutput(Player player, String message) {
		System.out.println(("<") + (player.getName()) + (">") + (" ") + (message));
	}

	private static final Blocks BLOCK_MAP = new Blocks();
	private static final Items ITEM_MAP = new Items();
	public static void printGivePlayerAmountLimitMessageOutput(String item) {
		if (BLOCK_MAP.exists(BLOCK_MAP.getBlockMap().get(item)))
			System.out.println(("Can't give more than 6400 of [") + (BLOCK_MAP.getBlockMap().get(item)) + ("]"));
		else if (ITEM_MAP.exists(ITEM_MAP.getItemMap().get(item)))
			System.out.println(("Can't give more than 6400 of [") + (ITEM_MAP.getItemMap().get(item)) + ("]"));
	}
	public static void printGivePlayerItemMessageOutput(String item, int amount, Player player) {
		if (BLOCK_MAP.getBlockMap().get(item) != null)
			System.out.println(("Gave ") + (amount) + (" ") + ("[") + (BLOCK_MAP.getBlockMap().get(item)) + ("] to ") + (player.getName()));
		else
			System.out.println(("Gave ") + (amount) + (" ") + ("[") + (ITEM_MAP.getItemMap().get(item)) + ("] to ") + (player.getName()));
	}
	public static void printTimeMessageOutput(String timeMode, World world) {
		switch (timeMode) {
			case "set" -> System.out.println("Set the time to " + world.getGameTicks());
			case "add" -> System.out.println("Set the time to " + world.getTime().intValue());
		}
	}

	public static void printTimeQueryMessageOutput(String queryMode, World world) {
		switch (queryMode) {
			case "day" -> System.out.println("The time is " + world.getGameTicks() / world.getMaxTime());
			case "daytime" -> System.out.println("The time is " + world.getTime().intValue());
			case "gametime" -> {}
		}
	}
	
	public static void printKillPlayerMessageOutput(String name) {
		System.out.println((name) + (" fell out of the world"));
		System.out.println(("Killed ") + (name));
	}

	public static void printInvalidFloatMessageOutput(String cmd) {
		System.out.println("Invalid float '" + cmd + "'");
	}

	public static void printExpectedFloatMessageOutput() {
		System.out.println("Expected float");
	}
	
	public static void printInvalidIntegerMessageOutput(long amount) {
		System.out.println(("Invalid integer '") + (amount) + ("'"));
	}
	
	public static void printExpectedIntegerMessageOutput() {
		System.out.println("Expected integer");
	}
	
	public static void printIntegerIsZeroMessageOutput() {
		System.out.println("Integer must not be less than 1, found 0");
	}
	
	public static void printUnknownItemMessageOutput(String itemName) {
		if (!itemName.startsWith("minecraft:"))
			System.out.println("Unknown item '" + (itemName) + "'");
		else
			System.out.println("Unknown item 'minecraft:" + (itemName) + "'");
	}
	
	public static void printUnknownCommandMessageOutput() {
		System.out.println("Unknown or incomplete command, see below for error");
	}
	
	public static void printUnknownCommandDefaultMessageOutput(String[] cmd) {
		StringBuilder out = new StringBuilder();
		for (String c : cmd) out.append(c).append(" ");
		System.out.print(out.substring(0, out.length() - 1) + ("<--[HERE]\n"));
	}

	public static void printNoPlayerFoundMessageOutput() {
		System.out.println("No player was found");
	}

	public static void printIncorrectArgumentCommandMessageOutput() {
		System.out.println("Incorrect argument for command");
	}

//	private static String getConvertItemIDToItemName(String itemID) {
//		String[] itemName = itemID.split("_");
//		StringBuilder itemIDBuilder = new StringBuilder();
//		for (String i : itemName) {
//			itemIDBuilder.append(toUpperCaseFirstChar(i)).append(" ");
//		}
//		itemID = itemIDBuilder.toString();
//		return itemID.substring(0, itemID.length() - 1);
//	}

	private static String toUpperCaseFirstChar(String text) {
		return (text.substring(0, 1).toUpperCase()) + (text.substring(1).toLowerCase());
	}
}
