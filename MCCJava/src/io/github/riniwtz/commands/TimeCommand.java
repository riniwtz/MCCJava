package io.github.riniwtz.commands;

public class TimeCommand extends BaseCommand implements Executable {
	
	@Override
	public void execute(String[] cmd) {
		
		if ((cmd.length == 2) || (cmd.length == 3)) {
			checkCommandLengthError(cmd, 2, 3);
			try {
				switch (cmd[1]){
					case "add": {
						if (cmd.length == 3) {
							world.addTime(Float.parseFloat(cmd[2]));
							CommandOutputMessage.printTimeOutput(cmd[1], world);
						}
						break;
					}
	
					case "query": {
						break;
					}
						
					case "set": {
						if (cmd.length == 3) {
							world.setTime(Float.parseFloat(cmd[2]));
							CommandOutputMessage.printTimeOutput(cmd[1], world);
						}
						break;
					}
						
					//Not really part of Minecraft cmd. (Only used for fixing bugs)
					case "get": {
						if (cmd.length == 2) {
							System.out.println((int)world.getTime());
						}
							
						break;
					}
				}
			} catch (NumberFormatException e) {
				CommandOutputMessage.printExpectedFloatOutput();
				CommandOutputMessage.printUnknownCommandDefaultOutput(cmd);
			}
		}
	}
}
