package io.github.riniwtz.mcc;

public class World implements Time {
	private String worldName = "My World";
	private int gameTicks = 0;
	private double timeDouble = getTime();
	private boolean stopTime = false;

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

	public void addTime(double timeDouble) {
		this.timeDouble += (float) timeDouble;
	}
	public void setTime(double timeDouble) {
		this.timeDouble = timeDouble;
		gameTicks = (int) timeDouble;
	}
	public double getTime() {
		final double MAX_TIME = 24000D;
		if (timeDouble >= MAX_TIME) {
			double firstComputation = this.timeDouble / MAX_TIME;
			double secondComputation = Math.floor(this.timeDouble / MAX_TIME);
			timeDouble = (firstComputation - secondComputation) * MAX_TIME;
		}
		return Math.round(timeDouble);
	}

	@Override
	public int getGameTicks() {
		return gameTicks;
	}
}
