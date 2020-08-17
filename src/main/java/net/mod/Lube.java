package net.mod;

import net.minecraft.block.SlimeBlock;
import net.minecraft.entity.Entity;
import net.minecraft.world.BlockView;

public class Lube extends SlimeBlock{
    public Lube(Settings settings) {
        super(settings);
    }
    @Override
    public void onEntityLand(BlockView world, Entity entity) {
        if(entity.bypassesLandingEffects()) {
            super.onEntityLand(world, entity);
        }
    }
}