package io.github.riniwtz.mcc;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

public class World implements Time {
	private String worldName = "My World";
	private int gameTicks = 0;
	private double time;
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
		boolean isNight = false;
	}

	public void addTime(double time) {
		this.time += time;
	}
	public void setTime(double time) {
		this.time = time;
		gameTicks = (int) time;
	}
	/*
	 * 	getTime() method uses a BigDecimal class which consists of an arbitrary precision integer unscaled value and a 32-bit integer scale.
	 * 	If zero or positive, the scale is the number of digits to the right of the decimal point. If negative, the unscaled value of the
	 * 	number is multiplied by ten to the power of the negation of the scale. The value of the number represented by the BigDecimal
	 * 	is therefore (unscaledValue × 10-scale).
	 *
	 * https://docs.oracle.com/javase/8/docs/api/java/math/BigDecimal.html
	 */
	public BigDecimal getTime() {
		final double MAX_TIME = 24000D;
		BigDecimal bigDecimal;
		if (time >= MAX_TIME) {
			bigDecimal = BigDecimal.valueOf(time)
					.divide(BigDecimal.valueOf(MAX_TIME), MathContext.DECIMAL64);

			return (
					bigDecimal.subtract(BigDecimal.valueOf(bigDecimal.intValue())))
					.multiply(BigDecimal.valueOf(MAX_TIME))
					.setScale(1, RoundingMode.HALF_UP
					);
		}
		return BigDecimal.valueOf(time);
	}

	@Override
	public int getGameTicks() {
		return gameTicks;
	}
}
