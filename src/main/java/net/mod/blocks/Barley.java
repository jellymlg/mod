package net.mod.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.item.ItemConvertible;
import net.minecraft.state.property.IntProperty;
import net.minecraft.state.StateManager.Builder;
import net.mod.Stuff;
import net.mod.utility.Crop;

public class Barley extends Crop {
    public static final IntProperty AGE = IntProperty.of("age", 0, 6);
    public Barley() {
        super(6);
    }
    @Override
    protected ItemConvertible getSeedsItem() {
        return Stuff.Items.BARLEY_SEED.asItem();
    }
    @Override
    public IntProperty getAgeProperty() {
        return AGE;
    }
    @Override
    protected void appendProperties(Builder<Block, BlockState> builder) {
        builder.add(AGE);
    }
}