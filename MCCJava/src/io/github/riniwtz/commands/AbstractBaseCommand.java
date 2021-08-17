package io.github.riniwtz.commands;
import io.github.riniwtz.mcc.Blocks;
import io.github.riniwtz.mcc.Items;
import io.github.riniwtz.mcc.Player;
import io.github.riniwtz.mcc.World;

public abstract class AbstractBaseCommand {
	protected Blocks block = new Blocks();
	protected World world = new World();
	protected Player player = new Player();
	protected Items item = new Items();
	public abstract void execute(String[] cmd);
	protected abstract boolean hasCommandHandlerError(String[] cmd);
}
