package io.github.riniwtz.mcc;
import java.util.ArrayList;

public class Items {
	// FIXME - block and item initialization especially the exceptions
	ArrayList<String> item = new ArrayList<>();
	public Items() {
		item.add("stick");
		item.add("diamond");
	}
	
	public ArrayList<String> getItems() {
		return item;
	}
	
	public boolean exists(String[] cmd) {
		for (String i : getItems()) {
			if (cmd[2].equals(i)) {
				return true;
			}
		}
		return false;
	}
}
