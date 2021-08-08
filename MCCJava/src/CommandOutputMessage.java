
public class CommandOutputMessage {
	
	public static final void printMessageOutput(Player player, String message) {
		System.out.println(("<") + (player.getName()) + (">") + (" ") + (message));
	}
	
	public static final void printGivePlayerAmountLimitOutput(String item) {
		System.out.println(("Can't give more than 6400 of [") + (getUpperCaseFirstChar(item)) + ("]"));
	}
	
	public static final void printGivePlayerItemOutput(String item, int amount, Player player) {
		System.out.println(("Gave ") + (amount) + (" ") + ("[") + (getUpperCaseFirstChar(item)) + ("] to ") + (player.getName()));
	}

	public static final void printTimeOutput(String command, World world) {
		if (command.equals("set") || command.equals("add"))
			System.out.println("Set the time to " + (int) world.getTime());
		
		if (command.equals("query"))
			System.out.println("The time is " + (int) world.getTime()); // getTime() should be query time (WIP)
	}
	
	public static final void printKillPlayerOutput(String name) {
		System.out.println((name) + (" fell out of the world"));
		System.out.println(("Killed ") + (name));
	}
	
	public static final void printInvalidIntegerOutput(long amount) {
		System.out.println(("Invalid integer '") + (amount) + ("'"));
	}
	
	public static final void printExpectedIntegerOutput() {
		System.out.println("Expected integer");
	}
	
	public static final void printIntegerIsZeroOutput() {
		System.out.println("Integer must not be less than 1, found 0");
	}
	
	public static final void printUnknownItemOutput(String itemName) {
		System.out.println("Unknown item 'minecraft:" + (itemName) + "'");
	}
	
	public static final void printUnknownCommandOutput() {
		System.out.println("Unknown or incomplete command, see below for error");
	}
	
	public static final void printUnknownCommandDefaultOutput(String[] command) {
		String out = "";
		for (String c : command) {
			out += c + (" ");
		}
		System.out.print(out.substring(0, out.length() - 1) + ("<--[HERE]\n"));
	}
	
	public static final String getUpperCaseFirstChar(String text) {
		return (text.substring(0, 1).toUpperCase()) + (text.substring(1, text.length()));
	}
}
