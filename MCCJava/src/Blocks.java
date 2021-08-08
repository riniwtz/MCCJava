import java.util.ArrayList;

public class Blocks {
	ArrayList<String> block = new ArrayList<>();
	Blocks() {
		block.add("dirt");
		block.add("cobblestone");
		block.add("stone");
		block.add("");
	}
	
	public ArrayList<String> getBlocks() {
		return block;
	}
	
	public boolean verifyBlockCommand(String[] cmd, String itemName) {
		for (String b : getBlocks()) {
			if (cmd[2].equals(b)) {
				new GiveCommand().setItemName(cmd[2]);
				return true;
			}
		}
		return false;
	}
	
}
