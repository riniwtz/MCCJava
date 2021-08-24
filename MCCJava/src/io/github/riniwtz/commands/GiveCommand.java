package io.github.riniwtz.commands;

import io.github.riniwtz.mcc.Player;

public class GiveCommand extends AbstractBaseCommand {
	private String itemID;
	private String playerName;
	private long amount = 1;
	private boolean isAmountCharacter;
	private final int MINIMUM_ARGUMENT = 3;

	public GiveCommand(Player player) {
		AbstractBaseCommand.player = player;
		player.write(player.getPlayerName());
		execute();
	}

	public void execute() {
		if (cmd.length > 2) playerName = cmd[1];
		if (cmd.length >= MINIMUM_ARGUMENT) {
			if (isPrefixValid())
				cmd[2] = getSplitString(cmd[2], ":");
			itemID = cmd[2];
		}
		if (cmd.length == 4) amount = getAmountToLongConverted(cmd[3]);
		if (!(hasCommandHandlerError())) {
			if (isAmountValid()) {
				player.addItemInventory(itemID, (int)amount);
				CommandOutputMessage.printGivePlayerItemMessageOutput(itemID, (int)amount, player);
			}
			this.amount = 1;
		}
		isAmountCharacter = false;
	}

	public boolean isPrefixValid() {
		return (cmd[2].length() > 10) && (cmd[2].startsWith("minecraft:"));
	}

	public String getSplitString(String text, String letter) {
		return text.substring(text.indexOf(letter) + 1);
	}
	String amountString;
	public long getAmountToLongConverted(String amount) {
		try {
			this.amount = Long.parseLong(amount);
		} catch (NumberFormatException e) {
			this.isAmountCharacter = true;
		}
		amountString = amount;
		return this.amount;
	}

	protected boolean hasCommandHandlerError() {
		if (cmd.length < MINIMUM_ARGUMENT) {
			CommandOutputMessage.printUnknownCommandMessageOutput();
			CommandOutputMessage.printUnknownCommandDefaultMessageOutput(cmd);
			return true;
		}
		// Checks error if player matches and command length is greater than the maximum argument
		final int MAXIMUM_ARGUMENT = 4;
		if (playerName.equals(player.getPlayerName())) {
			if (cmd.length > MAXIMUM_ARGUMENT) {
				CommandOutputMessage.printIncorrectArgumentCommandMessageOutput();
				CommandOutputMessage.printUnknownCommandDefaultMessageOutput(cmd);
				return true;
			}
		}
		// Checks error if block and item doesn't exist
		if ((!(block.exists(itemID)) && (!(item.exists(itemID))))) {
			CommandOutputMessage.printUnknownItemMessageOutput(itemID);
			CommandOutputMessage.printUnknownCommandDefaultMessageOutput(cmd);
			return true;
		}
		// Checks error if (player doesn't match) yet (block or item exists) and checks if (command length is not greater than maximum argument is false)
		// Checks error if (player doesn't match) yet (block or item exists) and checks if (command length is not greater than maximum argument is true)
		if (!(playerName.equals(player.getPlayerName()))) {
			if (block.exists(itemID) || item.exists(itemID)) {
				if (!(cmd.length > MAXIMUM_ARGUMENT)) {
					if (isAmountValid())
						CommandOutputMessage.printNoPlayerFoundMessageOutput();
				}
				if (cmd.length > MAXIMUM_ARGUMENT) {
					if (isAmountValid()) {
						CommandOutputMessage.printIncorrectArgumentCommandMessageOutput();
						CommandOutputMessage.printUnknownCommandDefaultMessageOutput(cmd);
					}
				}
			}
			return true;
		}
		return false;
	}

	public boolean isAmountValid() {
		if (amount > Integer.MAX_VALUE) {
			amount = Integer.MAX_VALUE;
			CommandOutputMessage.printInvalidIntegerMessageOutput(amount);
			CommandOutputMessage.printUnknownCommandDefaultMessageOutput(cmd);
			return false;
		}
		if (amount < Integer.MIN_VALUE) {
			amount = Integer.MIN_VALUE;
		}
		final int AMOUNT_LIMIT = 6400;
		if (playerName.equals(player.getPlayerName())) {
			if ((amount > AMOUNT_LIMIT) && (amount < Integer.MAX_VALUE)) {
				CommandOutputMessage.printGivePlayerAmountLimitMessageOutput(itemID);
				return false;
			}
		}
		if ((amount == 0) || (amount < 0)) {
			CommandOutputMessage.printIntegerLessMessageOutput(amount);
			CommandOutputMessage.printUnknownCommandDefaultMessageOutput(cmd);
			return false;
		}
		if (isAmountCharacter && !isAmountString(amountString))
			CommandOutputMessage.printExpectedIntegerMessageOutput();
		else
			CommandOutputMessage.printInvalidIntegerMessageOutput(amountString);
		CommandOutputMessage.printUnknownCommandDefaultMessageOutput(cmd);
		return false;
	}

	public boolean isAmountString(String amount) {
		return amount.length() > String.valueOf(Long.MAX_VALUE).length();
	}
}
