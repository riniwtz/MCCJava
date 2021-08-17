package io.github.riniwtz.mcc;
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
		float maxTime = 24000F;
		if (time >= maxTime)
			this.time = (float) ((this.time / maxTime) - (Math.floor(this.time / maxTime))) * maxTime;

		return Math.round(time);
	}
}
