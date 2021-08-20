package io.github.riniwtz.commands;

public class TimeCommand extends AbstractBaseCommand {
	// FIXME - TimeCommand.java code

	private final int RANGE = 3;
	private String timeMode;
	private String queryMode;
	private double time;
	private boolean isTimeCharacter;

	public double getConvertTimeToDouble(String time) {
		try {
			this.time = Double.parseDouble(time);
		} catch (NumberFormatException e) {
			this.isTimeCharacter = true;
		}
		return this.time;
	}

	public boolean isTimeValid(String[] cmd) {
		if ((time < 0.0D) && (!isTimeCharacter)) {
			CommandOutputMessage.printTickIsNegativeMessageOutput();
			return false;
		}

		String numberList = "1234567890";
		if (isTimeCharacter) {
			queryMode = cmd[2];
			switch (queryMode) {
				case "day", "daytime", "gametime" -> {
					return true;
				}
			}

			for (int i = 0; i < numberList.length(); i++) {
				if (cmd[2].charAt(0) == numberList.charAt(i)) {
					if (cmd[2].substring(1).contains(",")) {
						CommandOutputMessage.printExpectedWhitespaceMessageOutput();
						CommandOutputMessage.printUnknownCommandDefaultMessageOutput(cmd);
						return false;
					}
				}
			}

			if ((cmd[2].charAt(0) == '-') || (cmd[2].charAt(0) == '.'))
				CommandOutputMessage.printInvalidFloatMessageOutput(cmd[2]);
			else
				CommandOutputMessage.printExpectedFloatMessageOutput();

			CommandOutputMessage.printUnknownCommandDefaultMessageOutput(cmd);
			return false;
		}

		return true;
	}

	@Override
	protected boolean hasCommandHandlerError(String[] cmd) {
		if (cmd.length == (RANGE - 2)) {
			CommandOutputMessage.printUnknownCommandMessageOutput();
			CommandOutputMessage.printUnknownCommandDefaultMessageOutput(cmd);
			return true;
		}

		if (cmd.length > RANGE) {
			CommandOutputMessage.printIncorrectArgumentCommandMessageOutput();
			CommandOutputMessage.printUnknownCommandDefaultMessageOutput(cmd);
			return true;
		}

		// Check time mode error
		if (cmd.length == (RANGE - 1)) {
			if (!(timeMode.equals("get"))) {
				switch (timeMode) {
					case "add", "query", "set" -> CommandOutputMessage.printUnknownCommandMessageOutput();
					default -> CommandOutputMessage.printIncorrectArgumentCommandMessageOutput();
				}
				CommandOutputMessage.printUnknownCommandDefaultMessageOutput(cmd);
				return true;
			}
		}

		return false;
	}

	@Override
	public void execute(String[] cmd) {
		if (cmd.length >= 2) timeMode = cmd[1];
		if (cmd.length == RANGE) time = getConvertTimeToDouble(cmd[2]);
		if (!(hasCommandHandlerError(cmd))) {
			if (isTimeValid(cmd)) {
				switch (timeMode) {
					case "add" -> {
						if (time > Integer.MAX_VALUE)
							world.addTime(Integer.MAX_VALUE);
						else
							world.addTime(time);
						CommandOutputMessage.printTimeMessageOutput(timeMode, world);
					}

					/*
						day	= Returns the number of days elapsed in the game (each day is 24000 game ticks)
						daytime	= Returns the number of game ticks since dawn
						gametime = Returns the age of the Minecraft world in game ticks
					 */

					case "query" -> CommandOutputMessage.printTimeQueryMessageOutput(queryMode, world);
					case "set" -> {
						if (time > Integer.MAX_VALUE)
							world.setTime(Integer.MAX_VALUE);
						else
							world.setTime(time);

						CommandOutputMessage.printTimeMessageOutput(timeMode, world);
					}
					case "get" -> System.out.println(world.getTime().intValue());
				}
			}
			isTimeCharacter = false;
		}
	}
}
