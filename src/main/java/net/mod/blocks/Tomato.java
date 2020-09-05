package net.mod.blocks;

import net.minecraft.item.ItemConvertible;
import net.mod.Main;
import net.mod.utility.StakeCompatible;

public class Tomato extends StakeCompatible {
    public Tomato() {
        super(7);
    }
    @Override
    protected ItemConvertible getSeedsItem() {
        return Main.TOMATO_SEED;
    }
}