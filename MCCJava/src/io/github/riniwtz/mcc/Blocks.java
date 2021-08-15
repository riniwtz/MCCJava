package io.github.riniwtz.mcc;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Objects;

public class Blocks {
	// TODO - Add all block and item names in blocks_list and items_list files
	ArrayList<String> block = new ArrayList<>();

	public Blocks() {
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(Objects.requireNonNull(Blocks.class.getResourceAsStream("/io/github/riniwtz/resources/blocks_list"))));

			String line;
			String blockName;
			while ((line = reader.readLine()) != null) {
				blockName = line;
				block.add(blockName);
			}
		} catch (NumberFormatException | IOException e) {
			e.printStackTrace();
		}
	}

	public ArrayList<String> getBlocks() {
		return block;
	}

	public boolean exists(String blockName) {
		for (String b : getBlocks()) {
			if (blockName.equals(b)) {
				return true;
			}
		}
		return false;
	}
}
