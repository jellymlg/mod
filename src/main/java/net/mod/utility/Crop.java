package net.mod.utility;

import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Blocks;
import net.minecraft.block.CropBlock;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class Crop extends CropBlock {
    private int maxAge;
    public Crop(int maxAge) {
        super(FabricBlockSettings.copy(Blocks.WHEAT));
        this.maxAge = maxAge;
        BlockRenderLayerMap.INSTANCE.putBlock(this, RenderLayer.getCutout());
    }
    @Override
    public int getMaxAge() {
        return maxAge;
    }
    @Override
    protected int getGrowthAmount(World world) {
        return MathHelper.nextInt(world.random, (int) Math.ceil(getMaxAge() / 3), (int) Math.ceil(getMaxAge() / 1.5));
    }
}