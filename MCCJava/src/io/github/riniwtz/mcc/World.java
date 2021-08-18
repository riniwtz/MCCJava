package io.github.riniwtz.mcc;

import io.github.riniwtz.main.Main;

public class World implements Time {
	private String worldName = "My World";
	private float time = 0.0F;

	public GameMode gamemode;
	public enum GameMode {
		ADVENTURE,
		CREATIVE,
		SPECTATOR,
		SURVIVAL
	}
	public void setGameMode(GameMode gamemode) {
		this.gamemode = gamemode;
	}
	public String getGameModeName() {
		return gamemode.name();
	}
	public GameMode getGameMode() {
		return gamemode;
	}
	public World() {
		boolean isNight = getTime() >= 13000;
	}

	public void addTime(float time) {
		this.time += time;
	}
	public void setTime(float time) {
		this.time = time;
	}
	public float getTime() {
		final float MAX_TIME = 24000F;
		if (time >= MAX_TIME)
			time = (float) ((time / MAX_TIME) - (Math.floor(time / MAX_TIME))) * MAX_TIME;

		return Math.round(time);
	}

	@Override
	public float getRealTime() {
		return time;
	}
}
