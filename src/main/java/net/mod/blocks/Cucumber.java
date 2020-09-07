package net.mod.blocks;

import net.minecraft.item.ItemConvertible;
import net.mod.Stuff;
import net.mod.utility.StakeCompatible;

public class Cucumber extends StakeCompatible {
    public Cucumber() {
        super(7);
    }
    @Override
    protected ItemConvertible getSeedsItem() {
        return Stuff.Items.CUCUMBER_SEED.asItem();
    }
}