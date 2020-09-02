package net.mod.blocks;

import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.minecraft.block.BlockState;
import net.minecraft.block.SlimeBlock;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;

public class Lube extends SlimeBlock {
    public Lube(Settings settings) {
        super(settings);
        BlockRenderLayerMap.INSTANCE.putBlock(this, RenderLayer.getCutout());
    }
    @Override
    public void onEntityLand(BlockView world, Entity entity) {
        if(entity.bypassesLandingEffects()) {
            super.onEntityLand(world, entity);
        }
    }
    @Override
    public boolean isTranslucent(BlockState state, BlockView world, BlockPos pos) {
        return true;
    }
}