package net.mod;

import java.util.Comparator;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.CropBlock;
import net.minecraft.item.ItemConvertible;
import net.minecraft.state.StateManager.Builder;
import net.minecraft.state.property.IntProperty;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class Crop extends CropBlock {
    public static final IntProperty AGE = IntProperty.of("age", 0, 6);;
    public Crop(Settings settings) {
        super(settings);
    }
    @Override
    public int getMaxAge() {
        return AGE.getValues().stream().max(Comparator.naturalOrder()).get().intValue();
    }
    @Override
    protected int getGrowthAmount(World world) {
        return MathHelper.nextInt(world.random, 2, 4);
    }
    @Override
    protected ItemConvertible getSeedsItem() {
        return Main.CROPLOOT;
    }
    @Override
    protected void appendProperties(Builder<Block, BlockState> builder) {
        builder.add(AGE);
    }
    @Override
    public IntProperty getAgeProperty() {
        return AGE;
    }
}