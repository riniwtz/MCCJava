package io.github.riniwtz.mcc;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

public class World implements Time {
	private String worldName = "My World";
	private int gameTime = 0;
	private int gameTicks;
	private double time;
	private final int MAX_TIME = 24000;
	private boolean stopTime = false;
	public GameMode gamemode;

	public enum GameMode {
		ADVENTURE,
		CREATIVE,
		SPECTATOR,
		SURVIVAL
	}
	public World() {
		boolean isNight = false;
		gameTime += 20;
		gameTicks += gameTime;
	}
	public String getGameModeName() {
		return gamemode.name();
	}
	public GameMode getGameMode() {
		return gamemode;
	}
	public BigDecimal getTime() {
		BigDecimal bigDecimal;
		if (time >= MAX_TIME) {
			bigDecimal = BigDecimal.valueOf(time)
					.divide(BigDecimal.valueOf(MAX_TIME), MathContext.DECIMAL64);

			return (bigDecimal.subtract(BigDecimal.valueOf(bigDecimal.intValue())))
					.multiply(BigDecimal.valueOf(MAX_TIME))
					.setScale(1, RoundingMode.HALF_UP);
		}
		return BigDecimal.valueOf(time);
	}
	@Override
	public int getGameTicks() {
		return gameTicks;
	}
	public int getMaxTime() {
		return MAX_TIME;
	}
	public double getGameTime() {
		return gameTime;
	}
	public void setGameMode(GameMode gamemode) {
		this.gamemode = gamemode;
	}
	public void setTime(double time) {
		this.time = time;
		gameTicks = (int) time;
	}
	public void addTime(double time) {
		this.time += time;
	}
}
