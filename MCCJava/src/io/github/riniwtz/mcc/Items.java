package io.github.riniwtz.mcc;
import java.util.ArrayList;

import io.github.riniwtz.commands.GiveCommand;

public class Items {
	ArrayList<String> item = new ArrayList<>();
	public Items() {
		item.add("stick");
		item.add("diamond");
	}
	
	public ArrayList<String> getItems() {
		return item;
	}
	
	public boolean exists(String[] cmd, String itemName) {
		for (String i : getItems()) {
			if (cmd[2].equals(i)) {
				new GiveCommand().setItemName(cmd[2]);
				return true;
			}
		}
		return false;
	}
}
