
public class GiveCommand {
	Player player = new Player();
	Blocks block = new Blocks();
	Items item = new Items();
	String playerName;
	String itemName = null;
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

	public void execute(String[] cmd) {
		if ((cmd.length == 3) || (cmd.length == 4)) {
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
