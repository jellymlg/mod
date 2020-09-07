package net.mod.blocks;

import net.minecraft.item.ItemConvertible;
import net.mod.Stuff;
import net.mod.utility.StakeCompatible;

public class Pepper extends StakeCompatible {
    public Pepper() {
        super(7);
    }
    @Override
    protected ItemConvertible getSeedsItem() {
        return Stuff.Items.PEPPER_SEED.asItem();
    }
}