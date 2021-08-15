package io.github.riniwtz.commands;

public class TimeCommand extends BaseCommand {
	// FIXME - TimeCommand.java code
	@Override
	public void execute(String[] cmd) {
		if ((cmd.length == 2) || (cmd.length == 3)) {
			//CommandOutputMessage.printCheckCommandLengthErrorOutput(cmd, 2, 3);
			try {
				switch (cmd[1]) {
					case "add" -> {
						if (cmd.length == 3) {
							world.addTime(Float.parseFloat(cmd[2]));
							CommandOutputMessage.printTimeOutput(cmd[1], world);
						}
					}
					case "query" -> {
					}
					case "set" -> {
						if (cmd.length == 3) {
							world.setTime(Float.parseFloat(cmd[2]));
							CommandOutputMessage.printTimeOutput(cmd[1], world);
						}
					}
					//Not really part of Minecraft cmd. (Only used for fixing bugs)
					case "get" -> {
						if (cmd.length == 2) {
							System.out.println((int) world.getTime());
						}
					}
				}
			} catch (NumberFormatException e) {
				CommandOutputMessage.printExpectedFloatOutput();
				CommandOutputMessage.printUnknownCommandDefaultOutput(cmd);
			}
		}
	}

	@Override
	protected boolean hasCommandHandlerError(String[] cmd) {
		return false;
	}
}
