package net.mod.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.item.ItemConvertible;
import net.minecraft.state.property.IntProperty;
import net.minecraft.state.StateManager.Builder;
import net.mod.Main;
import net.mod.utility.Crop;

public class Tomato extends Crop {
    public static final IntProperty AGE = IntProperty.of("age", 0, 8);
    public Tomato() {
        super(8);
    }
    @Override
    protected ItemConvertible getSeedsItem() {
        return Main.TOMATO_SEED;
    }
    @Override
    public IntProperty getAgeProperty() {
        return AGE;
    }
    @Override
    protected void appendProperties(Builder<Block, BlockState> builder) {
        builder.add(AGE);
    }
    @Override
    public boolean hasRandomTicks(BlockState state) {
        return (Integer)state.get(this.getAgeProperty()) > 0 && super.hasRandomTicks(state);
    }
}