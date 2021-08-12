package io.github.riniwtz.commands;

/*
 * Checks if command length is less than or greater than arguments needed
 * Checks if command length is equal to 3 or equal to 4
 * Checks if itemName length is greater than 10 and is equals to "minecraft:"
 * "minecraft:" is removed from itemName and prints out the itemName
 * 
 * name is assigned to playerName
 * commandArray(2) is assigned to itemName
 * 
 * Checks if playerName is equal to player.getName() else prints an Error Message
 * Checks if block or item exist else prints an Error message
 * Checks if command length is 4 to convert amount to long
 * Checks if it has command errors
 * 
 * Checks if amount is greater than 0 and amount is less than the amount limit
 * player.addItemInventory(cmd[2], (int)amount);
 * CommandOutputMessage.printGivePlayerItemOutput(itemName, (int)amount, player);
 */

public class GiveCommand extends BaseCommand {

	private String itemName;
	private long amount = 1;
	private boolean isAmountCharacter = false;
	private final int MINIMUM_ARGUMENT = 3;
	private final int MAXIMUM_ARGUMENT = 4;

	@Override
	public void execute(String[] cmd) {
		CommandOutputMessage.printCheckCommandLengthErrorOutput(cmd, MINIMUM_ARGUMENT, MAXIMUM_ARGUMENT);
		if ((cmd.length == 3) || (cmd.length == 4)) {
			if ((cmd[2].length() > 10) && (cmd[2].startsWith("minecraft:")))
				cmd[2] = getSplitString(cmd[2], ":");

			String playerName = cmd[1];
			itemName = cmd[2];
			
			//TODO - Add an error output for not matching playerName in /give command
			if (block.exists(cmd) || item.exists(cmd)) {
				if (cmd.length == 4) amount = convertAmountToLong(cmd[3]);
				if (!(checkHasCommandHandlerErrors(cmd, playerName, amount))) {
					player.addItemInventory(cmd[2], (int)amount);
					CommandOutputMessage.printGivePlayerItemOutput(itemName, (int)amount, player);
				}
				this.amount = 1;
			} else {
				CommandOutputMessage.printUnknownItemOutput(itemName);
				CommandOutputMessage.printUnknownCommandDefaultOutput(cmd);
			}
		}
	}
	
	public long convertAmountToLong(String amount) {
		try {
			this.amount = Long.parseLong(amount);
		} catch (NumberFormatException e) {
			this.isAmountCharacter = true;
		}
		return this.amount;
	}

	public boolean checkHasCommandHandlerErrors(String[] cmd, String playerName, long amount) {
		if (amount > Integer.MAX_VALUE) {
			CommandOutputMessage.printInvalidIntegerOutput(amount);
			CommandOutputMessage.printUnknownCommandDefaultOutput(cmd);
			return true;
		}

		int AMOUNT_LIMIT = 6400;
		if ((amount > AMOUNT_LIMIT) && (amount < Integer.MAX_VALUE)) {
			CommandOutputMessage.printGivePlayerAmountLimitOutput(itemName);
			return true;
		}

		if (amount == 0) {
			CommandOutputMessage.printIntegerIsZeroOutput();
			CommandOutputMessage.printUnknownCommandDefaultOutput(cmd);
			return true;
		}

		if (!(playerName.equals(player.getName()))) {
			if (block.exists(cmd) || item.exists(cmd)) {
				if (cmd.length > MAXIMUM_ARGUMENT) {
					System.out.println("WORKING WORKING");
					CommandOutputMessage.printIncorrectArgumentCommandOutput();
					CommandOutputMessage.printUnknownCommandDefaultOutput(cmd);
				} else {
					System.out.println(cmd.length);
					CommandOutputMessage.printNoPlayerFoundOutput();
				}
			}
			return true;
		}

		if (isAmountCharacter) {
			CommandOutputMessage.printExpectedIntegerOutput();
			CommandOutputMessage.printUnknownCommandDefaultOutput(cmd);
			return true;
		}

		return false;
	}
	
	public String getSplitString(String text, String letter) {
		return text.substring(text.indexOf(letter) + 1);
	}
}
