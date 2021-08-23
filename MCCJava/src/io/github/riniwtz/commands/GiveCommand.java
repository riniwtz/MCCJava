package io.github.riniwtz.commands;

public class GiveCommand extends AbstractBaseCommand {
	private String itemID;
	private String playerName;
	private long amount = 1;
	private boolean isAmountCharacter;
	private final int MINIMUM_ARGUMENT = 3;

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
	public boolean isPrefixValid(String[] cmd) {
		return (cmd[2].length() > 10) && (cmd[2].startsWith("minecraft:"));
	}
	public boolean isAmountValid(String[] cmd) {
		if (amount > Integer.MAX_VALUE) {
			CommandOutputMessage.printInvalidIntegerMessageOutput(amount);
			CommandOutputMessage.printUnknownCommandDefaultMessageOutput(cmd);
			return false;
		}
		final int AMOUNT_LIMIT = 6400;
		if (playerName.equals(player.getName())) {
			if ((amount > AMOUNT_LIMIT) && (amount < Integer.MAX_VALUE)) {
				CommandOutputMessage.printGivePlayerAmountLimitMessageOutput(itemID);
				return false;
			}
		}
		if (amount == 0) {
			CommandOutputMessage.printIntegerIsZeroMessageOutput();
			CommandOutputMessage.printUnknownCommandDefaultMessageOutput(cmd);
			return false;
		}
		if (isAmountCharacter) {
			CommandOutputMessage.printExpectedIntegerMessageOutput();
			CommandOutputMessage.printUnknownCommandDefaultMessageOutput(cmd);
			return false;
		}
		return true;
	}
	@Override
	protected boolean hasCommandHandlerError(String[] cmd) {
		if (cmd.length < MINIMUM_ARGUMENT) {
			CommandOutputMessage.printUnknownCommandMessageOutput();
			CommandOutputMessage.printUnknownCommandDefaultMessageOutput(cmd);
			return true;
		}
		// Checks error if player matches and command length is greater than the maximum argument
		final int MAXIMUM_ARGUMENT = 4;
		if (playerName.equals(player.getName())) {
			if (cmd.length > MAXIMUM_ARGUMENT) {
				CommandOutputMessage.printIncorrectArgumentCommandMessageOutput();
				CommandOutputMessage.printUnknownCommandDefaultMessageOutput(cmd);
				return true;
			}
		}
		// Checks error if block and item doesn't exist
		if ((!(block.exists(itemID)) || (!(item.exists(itemID))))) {
			CommandOutputMessage.printUnknownItemMessageOutput(itemID);
			CommandOutputMessage.printUnknownCommandDefaultMessageOutput(cmd);
			return true;
		}
		// Checks error if (player doesn't match) yet (block or item exists) and checks if (command length is not greater than maximum argument is false)
		// Checks error if (player doesn't match) yet (block or item exists) and checks if (command length is not greater than maximum argument is true)
		if (!(playerName.equals(player.getName()))) {
			if (block.exists(itemID) || item.exists(itemID)) {
				if (!(cmd.length > MAXIMUM_ARGUMENT)) {
					if (isAmountValid(cmd))
						CommandOutputMessage.printNoPlayerFoundMessageOutput();
				}
				if (cmd.length > MAXIMUM_ARGUMENT) {
					if (isAmountValid(cmd)) {
						CommandOutputMessage.printIncorrectArgumentCommandMessageOutput();
						CommandOutputMessage.printUnknownCommandDefaultMessageOutput(cmd);
					}
				}
			}
			return true;
		}
		return false;
	}
	@Override
	public void execute(String[] cmd) {
		if (cmd.length > 2) playerName = cmd[1];
		if (cmd.length >= MINIMUM_ARGUMENT) {
			if (isPrefixValid(cmd))
				cmd[2] = getSplitString(cmd[2], ":");
			itemID = cmd[2];
		}
		if (cmd.length == 4) amount = getConvertAmountToLong(cmd[3]);
		if (!(hasCommandHandlerError(cmd))) {
			if (isAmountValid(cmd)) {
				player.addItemInventory(itemID, (int)amount);
				CommandOutputMessage.printGivePlayerItemMessageOutput(itemID, (int)amount, player);
			}
			this.amount = 1;
		}
		isAmountCharacter = false;
	}
}
