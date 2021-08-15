package io.github.riniwtz.mcc;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Objects;

public class Items {
	ArrayList<String> item = new ArrayList<>();

	public Items() {
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(Objects.requireNonNull(Blocks.class.getResourceAsStream("/io/github/riniwtz/resources/items_list"))));
			String line;
			String itemName;
			while ((line = reader.readLine()) != null) {
				itemName = line;
				item.add(itemName);
			}
		} catch (NumberFormatException | IOException e) {
			e.printStackTrace();
		}
	}

	public ArrayList<String> getItems() {
		return item;
	}

	public boolean exists(String itemName) {
		for (String i : getItems()) {
			if (itemName.equals(i)) {
				return true;
			}
		}
		return false;
	}
}
