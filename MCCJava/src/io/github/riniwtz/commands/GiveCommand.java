package io.github.riniwtz.commands;

import java.io.FileNotFoundException;
import java.io.IOException;

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

	private String playerName;
	private String itemName;
	private long amount = 1;
	private int amountLimit = 6400;
	
	@Override
	public void execute(String[] cmd) throws FileNotFoundException, IOException {
		CommandOutputMessage.printCheckCommandLengthErrorOutput(cmd, 3, 4);
		if ((cmd.length == 3) || (cmd.length == 4)) {
			if ((cmd[2].length() > 10) && (cmd[2].substring(0, 10).equals("minecraft:"))) 
				cmd[2] = splitString(cmd[2], ":");	
			
			playerName = cmd[1];
			itemName = cmd[2];
			
			//TODO - Add an error output for not matching playerName in /give command
			if (playerName.equals(player.getName())) {
				block.initializeBlock();
				if (block.exists(cmd) || item.exists(cmd)) {
					if (cmd.length == 4) amount = convertAmountToLong(cmd, cmd[3]);
					checkHasCommandErrors(cmd, amount);
					
					if ((amount > 0) && (amount <= amountLimit)) {
						player.addItemInventory(cmd[2], (int)amount);
						// FIXME - GiveItemCommandOutput itemName ex: oak_planks = Oak Planks || red_stained_glass = Red Stained Glass
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
	
	public void setItemName(String itemName) {
		this.itemName = itemName;
	} 
	
	public long convertAmountToLong(String[] commandArray, String commandInput) {
		try {
			amount = Long.parseLong(commandInput);
		} catch (NumberFormatException e) {
			CommandOutputMessage.printExpectedIntegerOutput();
			CommandOutputMessage.printUnknownCommandDefaultOutput(commandArray);
			System.exit(0);
		}
		return amount;
	}
	
	public void checkHasCommandErrors(String[] cmd, long amount) {
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
}
