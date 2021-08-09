package io.github.riniwtz.commands;

import io.github.riniwtz.mcc.Blocks;
import io.github.riniwtz.mcc.Items;
import io.github.riniwtz.mcc.Player;
import io.github.riniwtz.mcc.World;

public class BaseCommand {
	protected World world = new World();
	protected Player player = new Player();
	protected Blocks block = new Blocks();
	protected Items item = new Items();
	
	private String[] cmd;
	private String cmdInput;

	public void setCommand(String text) {
		cmd = text.split(" ");
		cmdInput = getCommand(0);
	}
	
	protected String[] getCommand() {
		return cmd;
	}
	
	protected String getCommand(int index) {
		return cmd[index];
	}
	
	protected String getCommandInput() {
		return cmdInput;
	}
	
	public void executeCommand() {
		switch(getCommand(0)) {
			case "/give": {
				new GiveCommand().execute(getCommand());
				break;
			}
			
			case "/time": {
				new TimeCommand().execute(getCommand());
				break;
			}
			
			case "/kill": {
				new KillCommand().execute(getCommand());
				break;
			}
			
			default: {
				CommandOutputMessage.printUnknownCommandOutput();
				CommandOutputMessage.printUnknownCommandDefaultOutput(cmd);
			}	
		}
	}
	
	protected void checkCommandLengthError(String[] cmd, int range) {
		if ((cmd.length < range) || (cmd.length > range)) {
			CommandOutputMessage.printUnknownCommandOutput();
			CommandOutputMessage.printUnknownCommandDefaultOutput(cmd);
		}
	}
	
	protected void checkCommandLengthError(String[] cmd, int min, int max) {
		if ((cmd.length < 3) || (cmd.length > 4)) {
			CommandOutputMessage.printUnknownCommandOutput();
			CommandOutputMessage.printUnknownCommandDefaultOutput(cmd);
		}
	}
}
