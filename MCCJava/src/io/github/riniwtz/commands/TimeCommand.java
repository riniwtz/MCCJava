package io.github.riniwtz.commands;

public class TimeCommand extends AbstractBaseCommand {
	// FIXME - TimeCommand.java code

	private final int RANGE = 3;
	private String timeMode = "";
	private double time;
	private boolean isTimeCharacter;

	private void setTime(double time) {
		this.time = time;
	}

	public double getConvertTimeToDouble(String time) {
		try {
			setTime(Double.parseDouble(time));
		} catch (NumberFormatException e) {
			this.isTimeCharacter = true;
		}
		return this.time;
	}

	public boolean isTimeValid(String[] cmd) {
		if ((time < 0.0D) && (!isTimeCharacter)) {
			CommandOutputMessage.printTickIsNegative();
			return false;
		}

		if (cmd.length == 3) {
			if (isTimeCharacter) {
				if ((cmd[2].charAt(0) == '-') || (cmd[2].charAt(0) == '.'))
					CommandOutputMessage.printInvalidFloatMessageOutput(cmd[2]);
				else
					CommandOutputMessage.printExpectedFloatMessageOutput();

				for (int i = 0; i < 10; i++) {
					if ((cmd[2].charAt(0) == (char) i) && (cmd[2].substring(1).contains(","))) {
						CommandOutputMessage.printExpectedWhitespaceMessageOutput();
					}
				}
				CommandOutputMessage.printUnknownCommandDefaultMessageOutput(cmd);
				return false;
			}
		}

		if (cmd.length == RANGE) {
			setTime(getConvertTimeToDouble(cmd[2]));
			return !isTimeCharacter;
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
		if (!(hasCommandHandlerError(cmd))) {
			if (isTimeValid(cmd)) {
				switch (timeMode) {
					case "add" -> {
						world.addTime(time);
						CommandOutputMessage.printTimeMessageOutput(timeMode, world);
					}
					case "query" -> System.out.println(timeMode);
					case "set" -> {
						if (time > Integer.MAX_VALUE)
							world.setTime(Integer.MAX_VALUE);
						else
							world.setTime(time);

						CommandOutputMessage.printTimeMessageOutput(timeMode, world);
					}
					//Not really part of Minecraft cmd. (Only used for fixing bugs)
					case "get" -> System.out.println((int) world.getTime());
				}
			}
			isTimeCharacter = false;
		}
	}
}
