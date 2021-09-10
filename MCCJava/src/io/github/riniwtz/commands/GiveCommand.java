package io.github.riniwtz.commands;

import java.math.BigInteger;
import java.util.regex.Pattern;

public class GiveCommand extends AbstractBaseCommand {
	// Global Private Fields
	private String itemBlockArgument;
	private String amountStringPlaceholder = "1";
	private BigInteger amountArgument = BigInteger.valueOf(1L);
	private final int MINIMUM_ARGUMENT = 3;
	private final int MAXIMUM_ARGUMENT = 4;
	private boolean hasAmountArgumentException;
	private boolean isAmountArgumentIntegerFlow;
	private boolean isNameArgumentValid, isItemBlockArgumentValid;

	// Initialization
	public GiveCommand() {
		String nameArgument;
		AbstractBaseCommand.MINIMUM_ARGUMENT = MINIMUM_ARGUMENT;
		AbstractBaseCommand.MAXIMUM_ARGUMENT = MAXIMUM_ARGUMENT;

		if (cmd.length > 2) {
			nameArgument = cmd[1];
			isNameArgumentValid = player.exists(nameArgument);
		}
		if (cmd.length >= MINIMUM_ARGUMENT) {
			if ((cmd[2].length() > 10) && (cmd[2].startsWith("minecraft:")))
				cmd[2] = getLastSplitString(cmd[2]);

			isItemBlockArgumentValid = block.exists(cmd[2]) || item.exists(cmd[2]);
			itemBlockArgument = cmd[2];
		}
		if (cmd.length >= MAXIMUM_ARGUMENT) {
			amountStringPlaceholder = cmd[3];
			try {
				amountArgument = new BigInteger(amountStringPlaceholder);
			} catch (NumberFormatException e) {
				hasAmountArgumentException = true;
			}
			if (!hasAmountArgumentException)
				isAmountArgumentIntegerFlow = amountArgument.compareTo(BigInteger.valueOf(Integer.MAX_VALUE)) > 0
						|| amountArgument.compareTo(BigInteger.valueOf(Integer.MIN_VALUE)) < 0;
		}
	}

	// Command Error Handler
	private boolean argumentErrorHandler() {
		// Local Field
		final int AMOUNT_ARGUMENT_LIMIT = 6400;

		if (cmd.length < MINIMUM_ARGUMENT) {
			CommandOutputMessage.printUnknownCommandMessage();
			CommandOutputMessage.printUnknownCommandDefaultMessage();
			return true;
		}
		// Checks if [name=any, item=valid, amount=any]
		if (isItemBlockArgumentValid) {
			if (cmd.length > MAXIMUM_ARGUMENT) {
				CommandOutputMessage.printIncorrectArgumentMessage();
				CommandOutputMessage.printUnknownCommandDefaultMessage();
				return true;
			}
			// Checks if [name=any, item=valid, amount=valid]
			if (!hasAmountArgumentException) {
				if (!isAmountArgumentIntegerFlow) {
					// Checks if [name=any, item=valid, amount <= 0]
					if (amountArgument.compareTo(BigInteger.ZERO) <= 0) {
						CommandOutputMessage.printIntegerLessMessage(1, amountArgument);
						CommandOutputMessage.printUnknownCommandDefaultMessage();
						return true;
					}
					// Checks if [name=valid, item=valid, amount > 0]
					if (isNameArgumentValid) {
						if (amountArgument.compareTo(BigInteger.valueOf(AMOUNT_ARGUMENT_LIMIT)) > 0) {
							CommandOutputMessage.printItemBlockAmountLimitMessage(itemBlockArgument);
							return true;
						}
					} else {
						CommandOutputMessage.printNoPlayerFoundMessage();
						return true;
					}
				} else {
					CommandOutputMessage.printInvalidIntegerMessage(amountArgument);
					CommandOutputMessage.printUnknownCommandDefaultMessage();
					return true;
				}
			} else {
				// Checks if [name=any, item=valid, amount=error]
				for (int i = 0; i < amountStringPlaceholder.length(); i++) {
					String stringPlaceholder = Character.toString(amountStringPlaceholder.charAt(i));
					if (stringPlaceholder.equals(".") || stringPlaceholder.equals("-")) {
						CommandOutputMessage.printInvalidIntegerMessage(amountStringPlaceholder);
						break;
					} else if (!isStringHasNumber(amountStringPlaceholder, i)) {
						if (Character.toString(amountStringPlaceholder.charAt(0)).equals("0"))
							CommandOutputMessage.printIntegerLessMessage(1, 0);
						else if (isStringHasNumber(Character.toString(amountStringPlaceholder.charAt(0)), 0))
							CommandOutputMessage.printExpectedWhitespaceMessage();
						else
							CommandOutputMessage.printExpectedIntegerMessage();
						break;
					}
				}
				CommandOutputMessage.printUnknownCommandDefaultMessage();
				return true;
			}
		} else {
			// Checks if [name=any, item=error, amount=any]
			CommandOutputMessage.printUnknownItemMessage(itemBlockArgument);
			CommandOutputMessage.printUnknownCommandDefaultMessage();
			return true;
		}
		return false;
	}

	// Execution
	public void execute() {
		if (!argumentErrorHandler()) {
			player.addItemInventory(itemBlockArgument, amountArgument.intValue());
			CommandOutputMessage.printGiveCommandSuccessMessage(itemBlockArgument, amountArgument.intValue());
		}
	}

	// Tools
	private boolean isStringHasNumber(String numberString, int index) {
		return Pattern.compile("[\\d]").matcher(Character.toString(numberString.charAt(index))).matches();
	}
	private String getLastSplitString(String text) {
		return text.substring(text.indexOf(":") + 1);
	}
}
