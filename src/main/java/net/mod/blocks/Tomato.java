package net.mod.blocks;

import net.minecraft.item.ItemConvertible;
import net.mod.Main;
import net.mod.utility.Crop;

public class Tomato extends Crop {
    public Tomato() {
        super(7);
    }
    @Override
    protected ItemConvertible getSeedsItem() {
        return Main.TOMATO_SEED;
    }
}