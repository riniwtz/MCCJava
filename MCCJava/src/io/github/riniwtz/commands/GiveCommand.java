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
	private String playerName;
	private long amount = 1;
	private boolean isAmountCharacter = false;
	private final int MINIMUM_ARGUMENT = 3;
	private final int MAXIMUM_ARGUMENT = 4;
	
	public long getConvertAmountToLong(String amount) {
		try {
			this.amount = Long.parseLong(amount);
		} catch (NumberFormatException e) {
			this.isAmountCharacter = true;
		}
		return this.amount;
	}
	
	public String getSplitString(String text, String letter) {
		return text.substring(text.indexOf(letter) + 1);
	}
	
	protected boolean hasCommandHandlerError(String[] cmd, long amount) {
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
		if (isAmountCharacter) {
			CommandOutputMessage.printExpectedIntegerOutput();
			CommandOutputMessage.printUnknownCommandDefaultOutput(cmd);
			return true;
		}
		return false;
	}
	
	@Override
	protected boolean hasCommandHandlerError(String[] cmd) {
		if (playerName.equals(player.getName())) {
			if (cmd.length > MAXIMUM_ARGUMENT) {
				CommandOutputMessage.printUnknownCommandOutput();
				CommandOutputMessage.printUnknownCommandDefaultOutput(cmd);
				return true;
			}
		}
		if ((!(block.exists(itemName)) && (!(item.exists(itemName))))) {
			CommandOutputMessage.printUnknownItemOutput(itemName);
			CommandOutputMessage.printUnknownCommandDefaultOutput(cmd);
			return true;
		}
		if (!(playerName.equals(player.getName()))) {
			if (block.exists(itemName) || item.exists(itemName)) {
				if (!(cmd.length > MAXIMUM_ARGUMENT))
					CommandOutputMessage.printNoPlayerFoundOutput();
				else {
					CommandOutputMessage.printIncorrectArgumentCommandOutput();
					CommandOutputMessage.printUnknownCommandDefaultOutput(cmd);
				}
			}
			return true;
		}
		return false;	
	}
	
	@Override
	public void execute(String[] cmd) {
		if (cmd.length < MINIMUM_ARGUMENT) {
			CommandOutputMessage.printUnknownCommandOutput();
			CommandOutputMessage.printUnknownCommandDefaultOutput(cmd);
		}
		if (cmd.length >= MINIMUM_ARGUMENT) {
			if ((cmd[2].length() > 10) && (cmd[2].startsWith("minecraft:")))
				cmd[2] = getSplitString(cmd[2], ":");

			playerName = cmd[1];
			itemName = cmd[2];
			if (!(hasCommandHandlerError(cmd))) {
				if (cmd.length == 4) amount = getConvertAmountToLong(cmd[3]);
				if (!(hasCommandHandlerError(cmd, amount))) {
					player.addItemInventory(cmd[2], (int)amount);
					CommandOutputMessage.printGivePlayerItemOutput(itemName, (int)amount, player);
				}
				this.amount = 1;
			}
		}
	}
}
