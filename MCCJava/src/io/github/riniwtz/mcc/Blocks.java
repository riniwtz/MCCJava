package io.github.riniwtz.mcc;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Objects;

public class Blocks {
	HashMap<String, String> blockMap = new HashMap<>();

	public Blocks() {
		try {
			BufferedReader blocksIDList = new BufferedReader(new InputStreamReader(Objects.requireNonNull(Blocks.class.getResourceAsStream("/io/github/riniwtz/resources/blocks_id_list"))));
			String blockID;
			while (((blockID = blocksIDList.readLine()) != null)) {
				blockMap.put(blockID, getConvertItemIDToItemName(blockID));
			}
		} catch (NumberFormatException | IOException e) {
			e.printStackTrace();
		}
	}
	private static String toUpperCaseFirstChar(String text) {
		return (text.substring(0, 1).toUpperCase()) + (text.substring(1).toLowerCase());
	}

	private String getConvertItemIDToItemName(String itemID) {
		String[] itemName = itemID.split("_");
		StringBuilder itemIDBuilder = new StringBuilder();
		for (String i : itemName) {
			itemIDBuilder.append(toUpperCaseFirstChar(i)).append(" ");
		}
		itemID = itemIDBuilder.toString();
		return itemID.substring(0, itemID.length() - 1);
	}

	public HashMap<String, String> getBlockMap() {
		return blockMap;
	}

	// value
	public boolean exists(String blockName) {
		String[] blockIDCollection = blockMap.keySet().toArray(new String[0]);
		for (String b : blockIDCollection) {
			if (blockName.equals(b))
				return true;
		}
		return false;
	}


}
