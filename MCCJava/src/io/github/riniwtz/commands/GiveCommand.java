package io.github.riniwtz.commands;
import io.github.riniwtz.mcc.Blocks;
import io.github.riniwtz.mcc.Items;
import io.github.riniwtz.mcc.Player;


public class GiveCommand {
	Player player = new Player();
	Blocks block = new Blocks();
	Items item = new Items();
	String playerName;
	String itemName;
	String[] itemNameSplit;
	long amount = 1;
	int amountLimit = 6400;
	int minGiveCommandArguments = 3;
	int maxGiveCommandArguments = 4;
	
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	
	public void setAmount(long amount) {
		this.amount = amount;
	}
	
	public long getAmount() {
		return amount;
	}
	
	public long convertAmountToLong(String[] commandArray, String commandInput) {
		try {
			amount = Long.parseLong(commandInput);
		} catch (NumberFormatException e) {
			CommandOutputMessage.printExpectedIntegerOutput();
			CommandOutputMessage.printUnknownCommandDefaultOutput(commandArray);
		}
		return amount;
	}
	
	public void checkGiveCommandErrors(String[] cmd, long amount) {
		if (amount > Integer.MAX_VALUE) {
			CommandOutputMessage.printInvalidIntegerOutput(amount);
			CommandOutputMessage.printUnknownCommandDefaultOutput(cmd);
		}
						
		if ((amount > amountLimit) && (amount < Integer.MAX_VALUE))
			CommandOutputMessage.printGivePlayerAmountLimitOutput(itemName);
			
		if (amount == 0) {
			CommandOutputMessage.printIntegerIsZeroOutput();
			CommandOutputMessage.printUnknownCommandDefaultOutput(cmd);
		}
	}
	
	public String splitString(String text, String letter) {
		String newString = text.substring(text.indexOf(letter) + 1, text.length());
		return newString;
	}
	
	public void execute(String[] cmd) {
		if ((cmd.length == 3) || (cmd.length == 4)) {
			if ((cmd[2].length() > 10) && (cmd[2].substring(0, 10).equals("minecraft:"))) 
				cmd[2] = splitString(cmd[2], ":");	
			
			playerName = cmd[1];
			itemName = cmd[2];

			if (playerName.equals(player.getName())) {
				if (block.verifyBlockCommand(cmd, itemName) || item.verifyItemCommand(cmd, itemName)) {
					if (cmd.length == 4) amount = convertAmountToLong(cmd, cmd[3]);
					
					checkGiveCommandErrors(cmd, amount);
					if ((amount > 0) && (amount <= amountLimit)) {
						itemName = cmd[2];
						player.addItemInventory(cmd[2], (int)amount);
						CommandOutputMessage.printGivePlayerItemOutput(itemName, (int)amount, player);
					}
				} else {
					CommandOutputMessage.printUnknownItemOutput(itemName);
					CommandOutputMessage.printUnknownCommandDefaultOutput(cmd);
				}
				
			} else {
				CommandOutputMessage.printUnknownCommandOutput();
				CommandOutputMessage.printUnknownCommandDefaultOutput(cmd);
			}
		}
	}
}
