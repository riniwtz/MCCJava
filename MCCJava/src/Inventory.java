import java.util.ArrayList;

public class Inventory {
	ArrayList<String> item = new ArrayList<>();
	
	// amount is work in progress if hashmaps or hashsets are introduce
	public void addItemInventory(String item) {
		this.item.add(item);
	}
	
	public void addItemInventory(String item, int amount) {
		this.item.add(item);
	}
	
	public ArrayList<String> getItemInventory() {
		return item;
	}
}
