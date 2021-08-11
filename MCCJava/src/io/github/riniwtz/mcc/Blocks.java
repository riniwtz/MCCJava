package io.github.riniwtz.mcc;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Blocks {
	// TODO - Add all block and item names in blocks_list and items_list files
	ArrayList<String> block = new ArrayList<>();

	public Blocks() {
		try {
			File file = new File("MCCJava\\src\\io\\github\\riniwtz\\resources\\blocks_list").getAbsoluteFile();
			Scanner scan = new Scanner(file);

			String blockName;
			while (scan.hasNextLine()) {
				blockName = scan.nextLine();
				block.add(blockName);
			}
			scan.close();
		} catch (NumberFormatException | FileNotFoundException e) {
			e.printStackTrace();
		}
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
