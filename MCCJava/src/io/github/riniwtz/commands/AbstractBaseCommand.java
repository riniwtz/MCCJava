package io.github.riniwtz.commands;
import io.github.riniwtz.mcc.Blocks;
import io.github.riniwtz.mcc.Items;
import io.github.riniwtz.mcc.Player;
import io.github.riniwtz.mcc.World;

public abstract class AbstractBaseCommand {
    public static String[] cmd;
    public static Player player = new Player();
    public static Blocks block = new Blocks();
    public static Items item = new Items();
    public static World world = new World();
}
