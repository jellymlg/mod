package net.mod.blocks;

import net.minecraft.item.ItemConvertible;
import net.mod.Stuff;
import net.mod.utility.StakeCompatible;

public class Tomato extends StakeCompatible {
    public Tomato() {
        super(7);
    }
    @Override
    protected ItemConvertible getSeedsItem() {
        return Stuff.Items.TOMATO_SEED.asItem();
    }
}