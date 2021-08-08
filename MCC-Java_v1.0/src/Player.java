import java.util.ArrayList;

public class Player extends Inventory {
	private String name;
	private int health = 20;
	private ArrayList<String> player = new ArrayList<>();
	
	Player() {
		setName("riniwtz");
		player.add(getName());
	}
	
	Player(String name) {
		setName(name);
	}
	
	public ArrayList<String> getPlayers() {
		return player;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	public void setHealth(int health) {
		this.health = health;
	}
	
	public int getHealth() {
		return health;
	}
	
	public void kill() {
		setHealth(0);
	}
	
	public void kill(String name) {
		setHealth(0);
	}
}
