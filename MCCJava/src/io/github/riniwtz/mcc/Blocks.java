package io.github.riniwtz.mcc;
import java.util.ArrayList;

import io.github.riniwtz.commands.GiveCommand;

public class Blocks {
	ArrayList<String> block = new ArrayList<>();
	public Blocks() {
		block.add("acacia_door");
		block.add("acacia_leaves");
		block.add("dirt");
	}
	
	public ArrayList<String> getBlocks() {
		return block;
	}
	
	public boolean exists(String[] cmd, String itemName) {
		for (String b : getBlocks()) {
			if (cmd[2].equals(b)) {
				new GiveCommand().setItemName(cmd[2]);
				return true;
			}
		}
		return false;
	}
}
