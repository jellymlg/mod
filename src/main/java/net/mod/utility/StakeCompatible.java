package net.mod.utility;

import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;

public class StakeCompatible extends Crop {
    public StakeCompatible(int maxAge) {
        super(maxAge);
    }
    @Override
    public boolean hasRandomTicks(BlockState state) {
        return false;
    }
    @Override
    public boolean isFertilizable(BlockView world, BlockPos pos, BlockState state, boolean isClient) {
        return false;
    }
}