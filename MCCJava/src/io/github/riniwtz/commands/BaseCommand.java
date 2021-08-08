package io.github.riniwtz.commands;

public class BaseCommand {
	String[] cmd;
	String cmdInput;
	String playerName;

	public void setCommand(String text) {
		cmd = text.split(" ");
		cmdInput = getCommand(0);
	}
	
	public String[] getCommand() {
		return cmd;
	}
	
	public String getCommand(int index) {
		return cmd[index];
	}
	
	public String getCommandInput() {
		return cmdInput;
	}
	
	public void executeCommand() {
		switch(getCommand(0)) {
			case "/give":
				new GiveCommand().execute(getCommand());
				checkCommandLengthError(3, 4);
				break;
			case "/time":
				new TimeCommand().execute(getCommand());
				break;
		}

//		
//		 // Time cmd
//		if ((getCommand(0).equals("/time")) && (cmd.length == 2) || (cmd.length == 3)) {
//			try {
//				switch (getCommand(1)){
//				case "add":
//					cmdListE.replace(cmdInput, 3);
//					if (cmd.length == 3) {
//						world.addTime(Float.parseFloat(cmd[2]));
//						CommandOutputMessage.printTimeOutput(cmd[1], world);
//					}
//					break;
//				case "query":
//					cmdListE.replace(cmdInput, 3);
//					break;
//				case "set":
//					cmdListE.replace(cmdInput, 3);
//					if (cmd.length == 3) {
//						world.setTime(Float.parseFloat(cmd[2]));
//						CommandOutputMessage.printTimeOutput(cmd[1], world);
//					}
//					break;
//				//Not really part of Minecraft cmd. (Only used for fixing bugs)
//				case "get":
//					cmdListE.replace(cmdInput, 2);
//					if (cmd.length == 2) {
//						System.out.println((int)world.getTime());
//					}
//						
//					break;
//				}
//			} catch (NumberFormatException e) {
//				System.out.println("Expected float");
//				CommandOutputMessage.printUnknownCommandDefaultOutput(cmd);
//			}
//		}
//		
//		//Kill cmd
//		
//		if (cmd[0].equals("/kill")) {
//			if (cmd.length == 1) {
//				cmdListE.replace(cmdInput, 1);
//				player.kill();
//				CommandOutputMessage.printKillPlayerOutput(player.getName());
//			}
//	
//			if (cmd.length == 2) {
//				for (String p : player.getPlayers()) {
//					if (cmd[1].equals(p)) {
//						cmdListE.replace(cmdInput, 2);
//						player.kill(cmd[1]);
//						CommandOutputMessage.printKillPlayerOutput(cmd[1]);
//					}
//				}
//			}
//		}
	}
	
	public void checkCommandLengthError(int min, int max) {
		if (cmdInput.length() > 0) {
			if ((cmdInput.substring(0,1).equals("/")) && (cmd.length < min) && (cmd.length > max)) {
				CommandOutputMessage.printUnknownCommandOutput();
				CommandOutputMessage.printUnknownCommandDefaultOutput(cmd);
			}
		}
	}
}
