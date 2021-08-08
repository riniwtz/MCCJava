import java.util.NoSuchElementException;
import java.util.Scanner;

public class Main {
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		BaseCommand command = new BaseCommand();
		Player player = new Player();
		String text = "";
		
		boolean running = true;
		while (running) {
			try {
				text = sc.nextLine();
			} catch (NoSuchElementException e) {
				System.out.println(e);
				break;
			}
			
			if ((text.length() > 0) && (text.substring(0, 1).equals("/")) == false) {
				CommandOutputMessage.printMessageOutput(player, text);
			} else { 
				command.setCommand(text);
				command.executeCommand();
			} 
		}
		sc.close();
	}
}
