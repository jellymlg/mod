package net.mod;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.item.ItemConvertible;
import net.minecraft.state.property.IntProperty;
import net.minecraft.state.StateManager.Builder;

public class Barley extends Crop {
    public static final IntProperty AGE = IntProperty.of("age", 0, 6);
    public Barley() {
        super(6);
    }
    @Override
    protected ItemConvertible getSeedsItem() {
        return Main.BARLEY_SEED;
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