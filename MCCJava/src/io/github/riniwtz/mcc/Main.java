package io.github.riniwtz.mcc;
import java.util.NoSuchElementException;
import java.util.Scanner;

import io.github.riniwtz.commands.BaseCommand;
import io.github.riniwtz.commands.CommandOutputMessage;

public class Main {
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		BaseCommand baseCommand = new BaseCommand();
		Player player = new Player();
		String text = "";
		
		boolean running = true;
		while (running) {
			try { text = sc.nextLine(); } 
			catch (NoSuchElementException e) {
				System.out.println(e);
				break;
			}
			
			if ((text.length() > 0) && (text.substring(0, 1).equals("/")) == true) {
				baseCommand.setCommand(text);
				baseCommand.executeCommand();
			} 
			else
				CommandOutputMessage.printMessageOutput(player, text);

		}
		sc.close();
	}
}
