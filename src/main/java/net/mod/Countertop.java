package net.mod;

import net.minecraft.block.Block;
import net.minecraft.block.BlockEntityProvider;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.world.BlockView;

public class Countertop extends Block implements BlockEntityProvider {
    public Countertop(Settings settings) {
        super(settings);
    }
    @Override
    public BlockEntity createBlockEntity(BlockView world) {
        return new CountertopEntity();
    }
    
}