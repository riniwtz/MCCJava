package io.github.riniwtz.mcc;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Objects;

public class Items {
	HashMap<String, String> itemMap = new HashMap<>();

	public Items() {
		try {
			BufferedReader itemsIDList = new BufferedReader(new InputStreamReader(Objects.requireNonNull(Items.class.getResourceAsStream("/io/github/riniwtz/resources/items_id_list"))));
			String itemID;
			while (((itemID = itemsIDList.readLine()) != null)) {
				itemMap.put(itemID, getConvertItemIDToItemName(itemID));
			}
		} catch (NumberFormatException | IOException e) {
			e.printStackTrace();
		}
	}

	private String getConvertItemIDToItemName(String itemID) {
		if (itemID.equals("experience_bottle"))
			return "Bottle o' Enchanting";
		if (itemID.startsWith("music_disc"))
			return "Music Disc";
		if (itemID.equals("filled_map"))
			return "Map";
		if (itemID.equals("map"))
			return "Empty Map";
		if (itemID.endsWith("banner_pattern"))
			return "Banner Pattern";
		if (itemID.equals("lingering_potion"))
			return "Lingering Uncraftable Potion";
		if (itemID.equals("splash_potion"))
			return "Splash Uncraftable Potion";
		if (itemID.equals("potion"))
			return "Uncraftable Potion";
		if (itemID.equals("writable_book"))
			return "Book and Quill";

		String[] itemName = itemID.split("_");
		StringBuilder itemIDBuilder = new StringBuilder();
		for (String i : itemName) {
			itemIDBuilder.append(toUpperCaseFirstChar(i)).append(" ");
		}

		String formedID = "";
		if (itemID.endsWith("_minecart")) {
			if (itemID.startsWith("tnt"))
				return "Minecart with TNT";
			//return "Minecart with " + toUpperCaseFirstChar(itemID).charAt(0) + itemID.substring(1, itemID.indexOf("_"));
			formedID = itemIDBuilder.substring(0, itemIDBuilder.length() - 1);
			String Block = formedID.substring(0, formedID.indexOf("Minecart") - 1);
			String Minecart = formedID.substring(formedID.indexOf("Minecart"));
			return Minecart + " with " + Block;
		}

		return formedID;
	}

	private static String toUpperCaseFirstChar(String text) {
		return (text.substring(0, 1).toUpperCase()) + (text.substring(1).toLowerCase());
	}

	public HashMap<String, String> getItemMap() {
		return itemMap;
	}

	// value
	public boolean exists(String itemID) {
		String[] itemIDCollection = itemMap.keySet().toArray(new String[0]);
		for (String i : itemIDCollection) {
			if (itemID.equals(i))
				return true;
		}
		return false;
	}
}
