package io.github.riniwtz.mcc;

import java.io.*;

public class Player extends Inventory {
	private String player = getPlayerName();
	private int health = 20;

	public void write(String text) {
		try {
			BufferedWriter playerWriter = new BufferedWriter(new FileWriter("players.txt", true));
			playerWriter.write(text + "\n");
			playerWriter.close();
		} catch (NumberFormatException | IOException e) {
			e.printStackTrace();
		}
	}

	public void setHealth(int health) {
		this.health = health;
	}

	public void setPlayerName(String player) {
		this.player = player;
	}

	public String getPlayerName() {
		return player;
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
