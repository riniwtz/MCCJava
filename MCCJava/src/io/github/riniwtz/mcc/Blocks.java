package io.github.riniwtz.mcc;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Blocks {
	// FIXME - block and item initialization especially the exceptions
	// TODO - Add all block and item names in blocks_list and items_list files
	ArrayList<String> block = new ArrayList<>();
	
	public void initializeBlock() throws FileNotFoundException, IOException {
		File file = new File("D:\\Documents\\Coding\\GitHub\\MCCJava\\MCCJava\\src\\io\\github\\riniwtz\\resources\\blocks_list");
		Scanner scan = new Scanner(file);
		
		String fileContent = "";
		while (scan.hasNextLine()) {
			fileContent = scan.nextLine();
			block.add(fileContent);
		}
		scan.close();
	}
	
	public ArrayList<String> getBlocks() {
		return block;
	}
	
	public boolean exists(String[] cmd) {
		for (String b : getBlocks()) {
			if (cmd[2].equals(b)) {
				return true;
			}
		}
		return false;
	}
}
