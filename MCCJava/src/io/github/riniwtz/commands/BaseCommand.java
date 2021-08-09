package io.github.riniwtz.commands;

import java.io.FileNotFoundException;
import java.io.IOException;

import io.github.riniwtz.mcc.Blocks;
import io.github.riniwtz.mcc.Items;
import io.github.riniwtz.mcc.Player;
import io.github.riniwtz.mcc.World;

public abstract class BaseCommand {
	protected Blocks block = new Blocks();
	protected World world = new World();
	protected Player player = new Player();
	protected Items item = new Items();
	public abstract void execute(String[] cmd) throws FileNotFoundException, IOException;
	protected abstract void checkCommandLengthError(String[] cmd, int range);
	protected abstract void checkCommandLengthError(String[] cmd, int min, int max);
	
}
