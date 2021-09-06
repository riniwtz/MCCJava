package io.github.riniwtz.commands;
import java.math.BigInteger;

public class GiveCommand extends AbstractBaseCommand {
	private String itemBlockArgument;
	private String amountStringPlaceholder = "1";
	private BigInteger amountArgument = BigInteger.valueOf(1L);

	// Boolean and Limit (3:4)
	private final int MINIMUM_ARGUMENT = 3;
	private final int MAXIMUM_ARGUMENT = 4;
	private boolean hasAmountException;
	private boolean isAmountIntegerFlow;
	private boolean isNameArgumentValid, isItemBlockArgumentValid;

	public GiveCommand() {
		String nameArgument;

		if (cmd.length > 2) {
			nameArgument = cmd[1];
			isNameArgumentValid = player.exists(nameArgument);
		}
		if (cmd.length >= MINIMUM_ARGUMENT) {
			if ((cmd[2].length() > 10) && (cmd[2].startsWith("minecraft:")))
				cmd[2] = getLastSplitString(cmd[2], ":");

			isItemBlockArgumentValid = block.exists(cmd[2]) || item.exists(cmd[2]);
			itemBlockArgument = cmd[2];
		}
		if (cmd.length >= MAXIMUM_ARGUMENT) {
			amountStringPlaceholder = cmd[3];
			try {
				amountArgument = new BigInteger(amountStringPlaceholder);
			} catch (NumberFormatException e) {
				hasAmountException = true;
			}
			if (!hasAmountException)
				isAmountIntegerFlow = amountArgument.compareTo(BigInteger.valueOf(Integer.MAX_VALUE)) > 0
						|| amountArgument.compareTo(BigInteger.valueOf(Integer.MIN_VALUE)) < 0;
		}
	}

	public void execute() {
		if (!argumentErrorHandler()) {
			player.addItemInventory(itemBlockArgument, amountArgument.intValue());
			CommandOutputMessage.printGivePlayerItemMessageOutput(itemBlockArgument, amountArgument.intValue());
		}
	}

	private boolean argumentErrorHandler() {
		final int AMOUNT_ARGUMENT_LIMIT = 6400;
		boolean hasDotOrDash = false;
		boolean hasSymbol = false;

		if (cmd.length < MINIMUM_ARGUMENT) {
			CommandOutputMessage.printUnknownCommandMessageOutput();
			CommandOutputMessage.printUnknownCommandDefaultMessageOutput();
			return true;
		}
		if (isItemBlockArgumentValid) {
			if (cmd.length > MAXIMUM_ARGUMENT) {
				CommandOutputMessage.printIncorrectArgumentCommandMessageOutput();
				CommandOutputMessage.printUnknownCommandDefaultMessageOutput();
				return true;
			}
			if (!hasAmountException) {
				if (!isAmountIntegerFlow) {
					if (amountArgument.compareTo(BigInteger.ZERO) <= 0) {
						CommandOutputMessage.printIntegerLessMessageOutput(1, amountArgument);
						CommandOutputMessage.printUnknownCommandDefaultMessageOutput();
						return true;
					}
					if (isNameArgumentValid) {
						if (amountArgument.compareTo(BigInteger.valueOf(AMOUNT_ARGUMENT_LIMIT)) > 0) {
							CommandOutputMessage.printGivePlayerAmountLimitMessageOutput(itemBlockArgument);
							return true;
						}
					} else {
						CommandOutputMessage.printNoPlayerFoundMessageOutput();
						return true;
					}
				} else {
					CommandOutputMessage.printInvalidIntegerMessageOutput(amountArgument);
					CommandOutputMessage.printUnknownCommandDefaultMessageOutput();
					return true;
				}
			} else {
				for (int i = 0; i < amountStringPlaceholder.length(); i++) {
					if (Character.toString(amountStringPlaceholder.charAt(i)).equals(".") || Character.toString(amountStringPlaceholder.charAt(i)).equals("-")) {
						hasDotOrDash = true;
						break;
					} else if (!isStringNumberIndexOf(amountStringPlaceholder, i)) {
						hasSymbol = true;
						break;
					}
				}
				if (hasSymbol) {
					if (Character.toString(amountStringPlaceholder.charAt(0)).equals("0"))
						CommandOutputMessage.printIntegerLessMessageOutput(1, 0);
					else if (isStringNumberIndexOf(Character.toString(amountStringPlaceholder.charAt(0)), 0))
						CommandOutputMessage.printExpectedWhitespaceMessageOutput();
					else
						CommandOutputMessage.printExpectedIntegerMessageOutput();
				}
				else if (hasDotOrDash)
					CommandOutputMessage.printInvalidIntegerMessageOutput(amountStringPlaceholder);
				CommandOutputMessage.printUnknownCommandDefaultMessageOutput();
				return true;
			}
		} else {
			CommandOutputMessage.printUnknownItemMessageOutput(itemBlockArgument);
			CommandOutputMessage.printUnknownCommandDefaultMessageOutput();
			return true;
		}
		return false;
	}

	public String getLastSplitString(String text, String letter) {
		return text.substring(text.indexOf(letter) + 1);
	}

	private boolean isStringNumberIndexOf(String numberString, int index) {
		return String.valueOf(numberString.charAt(index)).chars().allMatch(Character::isDigit);
	}
}
