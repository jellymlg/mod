package net.mod;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.nbt.CompoundTag;

public class CountertopEntity extends BlockEntity {
    
    public CountertopEntity() {
        super(Main.COUNTERTOP_ENTITY);
    }
    @Override
    public CompoundTag toTag(CompoundTag tag) {
        return super.toTag(tag);
    }
    @Override
    public void fromTag(BlockState state, CompoundTag tag) {
        super.fromTag(state, tag);
    }
}