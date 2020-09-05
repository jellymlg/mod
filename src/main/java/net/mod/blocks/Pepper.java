package net.mod.blocks;

import net.minecraft.item.ItemConvertible;
import net.mod.Main;
import net.mod.utility.StakeCompatible;

public class Pepper extends StakeCompatible {
    public Pepper() {
        super(7);
    }
    @Override
    protected ItemConvertible getSeedsItem() {
        return Main.PEPPER_SEED;
    }
}