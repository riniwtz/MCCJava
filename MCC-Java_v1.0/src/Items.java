import java.util.ArrayList;

public class Items {
	ArrayList<String> item = new ArrayList<>();
	Items() {
		item.add("stick");
		item.add("diamond");
	}
	
	public ArrayList<String> getItems() {
		return item;
	}
	
	public boolean verifyItemCommand(String[] cmd, String itemName) {
		for (String i : getItems()) {
			if (cmd[2].equals(i)) {
				new GiveCommand().setItemName(cmd[2]);
				return true;
			}
			break;
		}
		return false;
	}
}
