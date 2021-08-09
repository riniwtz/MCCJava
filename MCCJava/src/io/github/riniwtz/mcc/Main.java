package io.github.riniwtz.mcc;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.Scanner;

import io.github.riniwtz.commands.BaseCommand;
import io.github.riniwtz.commands.CommandOutputMessage;
import io.github.riniwtz.commands.GiveCommand;
import io.github.riniwtz.commands.KillCommand;
import io.github.riniwtz.commands.TimeCommand;

public class Main {
	public Main() throws FileNotFoundException, IOException {
		Scanner sc = new Scanner(System.in);
		Player player = new Player();

		String text = "";
		String[] cmd;
		
		boolean running = true;
		while (running) {
			try { text = sc.nextLine(); } 
			catch (NoSuchElementException e) {
				System.out.println(e);
				break;
			}
			
			// TODO - Add all commands that are possible from Minecraft commands
			if ((text.length() > 0) && (text.substring(0, 1).equals("/"))) {
				cmd = text.split(" ");
				switch(cmd[0]) {
					case "/give": {
						new GiveCommand().execute(cmd);
						break;
					}
					
					case "/time": {
						new TimeCommand().execute(cmd);
						break;
					}
					
					case "/kill": {
						new KillCommand().execute(cmd);
						break;
					}
					default: {
						CommandOutputMessage.printUnknownCommandOutput();
						CommandOutputMessage.printUnknownCommandDefaultOutput(cmd);
					}	
				}
			} 
			else
				CommandOutputMessage.printMessageOutput(player, text);
		}
		sc.close();
	}
	
	public static void main(String[] args) throws FileNotFoundException, IOException {
		new Main();
	}
}
