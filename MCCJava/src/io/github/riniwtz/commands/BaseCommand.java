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
			case "/kill":
				new KillCommand().execute(getCommand());
				break;
		}
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
