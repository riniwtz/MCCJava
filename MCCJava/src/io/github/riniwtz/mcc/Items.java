package io.github.riniwtz.mcc;
import java.util.ArrayList;

public class Items {
	// FIXME - block and item initialization especially the exceptions
	ArrayList<String> item = new ArrayList<>();
	public void initializeItem() throws FileNotFoundException, IOException {
		File file = new File("src\\io\\github\\riniwtz\\resources\\items_list").getAbsoluteFile();
		Scanner scan = new Scanner(file);

		String itemName = "";
		while (scan.hasNextLine()) {
			itemName = scan.nextLine();
			item.add(itemName);
		}
		scan.close();
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
