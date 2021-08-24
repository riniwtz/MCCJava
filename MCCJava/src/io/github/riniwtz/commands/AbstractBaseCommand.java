package io.github.riniwtz.commands;
import io.github.riniwtz.mcc.Blocks;
import io.github.riniwtz.mcc.Items;
import io.github.riniwtz.mcc.Player;
import io.github.riniwtz.mcc.World;

public abstract class AbstractBaseCommand {
    protected String[] cmd;
    protected Player player;
    protected Blocks block;
    protected Items item;
    protected World world;

    protected AbstractBaseCommand(String[] cmd) {
        this.cmd = cmd;
    }

    protected AbstractBaseCommand(String[] cmd, World world) {
        this.cmd = cmd;
        this.world = world;
    }

    protected AbstractBaseCommand(String[] cmd, Player player) {
        this.cmd = cmd;
        this.player = player;
    }

    protected AbstractBaseCommand(String[] cmd, Player player, Blocks block) {
        this.cmd = cmd;
        this.player = player;
        this.block = block;
    }

    protected AbstractBaseCommand(String[] cmd, Player player, Blocks block, Items item) {
        this.cmd = cmd;
        this.player = player;
        this.block = block;
        this.item = item;
    }
}
