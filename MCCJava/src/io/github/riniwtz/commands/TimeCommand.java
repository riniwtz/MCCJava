package io.github.riniwtz.commands;

public class TimeCommand extends AbstractBaseCommand {
	// FIXME - TimeCommand.java code

	private final int RANGE = 3;
	private String timeAction = "";
	private float time;
	private boolean isTimeCharacter;

	public float getConvertTimeToFloat(String time) {
		try {
			this.time = Float.parseFloat(time);
		} catch (NumberFormatException e) {
			this.isTimeCharacter = true;
		}
		return this.time;
	}

	public boolean isTimeValid(String[] cmd) {
		if ((time < 0) && (!isTimeCharacter)) {
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

		return true;
	}

	@Override
	protected boolean hasCommandHandlerError(String[] cmd) {
		if (cmd.length == 1) {
			CommandOutputMessage.printUnknownCommandMessageOutput();
			CommandOutputMessage.printUnknownCommandDefaultMessageOutput(cmd);
			return true;
		}

		return false;
	}

	private void checkTimeActionValid(String[] cmd) {
		if (cmd.length == 2) {
			switch (timeAction) {
				case "add", "query", "set" -> CommandOutputMessage.printUnknownCommandMessageOutput();
				default -> CommandOutputMessage.printIncorrectArgumentCommandMessageOutput();
			}
			CommandOutputMessage.printUnknownCommandDefaultMessageOutput(cmd);
		}
	}

	@Override
	public void execute(String[] cmd) {
		if (cmd.length < RANGE) checkTimeActionValid(cmd);
		if (cmd.length == 3) time = getConvertTimeToFloat(cmd[2]);
		if (!(hasCommandHandlerError(cmd))) {
			if (isTimeValid(cmd)) {
				switch (timeAction) {
					case "add" -> {
						world.addTime(time);
						CommandOutputMessage.printTimeMessageOutput(cmd[1], world, false);
					}
					case "query" -> {}
					case "set" -> {
						world.setTime(time);
						CommandOutputMessage.printTimeMessageOutput(cmd[1], world, true);
					}
					//Not really part of Minecraft cmd. (Only used for fixing bugs)
					case "get" -> System.out.println((int) world.getTime());
				}
			}
			isTimeCharacter = false;
		}


//		if ((cmd.length == 2) || (cmd.length == 3)) {
//			//CommandOutputMessage.printCheckCommandLengthErrorOutput(cmd, 2, 3);
//			try {
//				switch (cmd[1]) {
//					case "add" -> {
//						if (cmd.length == 3) {
//							world.addTime(Float.parseFloat(cmd[2]));
//							CommandOutputMessage.printTimeMessageOutput(cmd[1], world);
//						}
//					}
//					case "query" -> {
//					}
//					case "set" -> {
//						if (cmd.length == 3) {
//							world.setTime(Float.parseFloat(cmd[2]));
//							CommandOutputMessage.printTimeMessageOutput(cmd[1], world);
//						}
//					}
//					//Not really part of Minecraft cmd. (Only used for fixing bugs)
//					case "get" -> {
//						if (cmd.length == 2) {
//							System.out.println((int) world.getTime());
//						}
//					}
//				}
//			} catch (NumberFormatException e) {
//				CommandOutputMessage.printExpectedFloatMessageOutput();
//				CommandOutputMessage.printUnknownCommandDefaultMessageOutput(cmd);
//			}
//		}
	}
}
