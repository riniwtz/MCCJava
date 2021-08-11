package io.github.riniwtz.mcc;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Items {
	// FIXME - block and item initialization especially the exceptions
	ArrayList<String> item = new ArrayList<>();

	public Items() {
		try {
			File file = new File("MCCJava\\src\\io\\github\\riniwtz\\resources\\items_list").getAbsoluteFile();
			Scanner scan = new Scanner(file);

			String itemName;
			while (scan.hasNextLine()) {
				itemName = scan.nextLine();
				item.add(itemName);
			}
			scan.close();
		} catch (NumberFormatException | FileNotFoundException e) {
			e.printStackTrace();
		}
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
