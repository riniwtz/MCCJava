import java.util.HashMap;

public class BaseCommand {
	String[] cmd;
	String cmdInput;
	String playerName;
	
	HashMap<String, Integer> cmdListE = new HashMap<>();
	
	public HashMap<String, Integer> getCommandListElements() {
		return cmdListE;
	}
	
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
		cmdListE.put("give", 3);
		cmdListE.put("time", 3);
		cmdListE.put("kill", 1);
		
		if ((getCommand(0).equals("/give")) && (getCommand().length == 3) || (getCommand().length == 4)) {
			new GiveCommand().execute(getCommand());
		}
		
		checkCommandLengthError();
		
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
	
	public void checkCommandLengthError() {
		for (String c : cmdListE.keySet()) {
			if (cmdInput.length() > 0) {
				if ((cmdInput.substring(0,1).equals("/")) && (cmd.length < cmdListE.get(c))) {
					CommandOutputMessage.printUnknownCommandOutput();
					CommandOutputMessage.printUnknownCommandDefaultOutput(cmd);
					break;	
				}
			}
		}
	}
}
