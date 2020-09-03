package net.mod.utility;

import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.CropBlock;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class Crop extends CropBlock {
    private int maxAge;
    private int age = 0;
    private int uses = 4;
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
    public int getAge() {
        return age;
    }
    public Crop setAge(int age) {
        if(age >= 0 && age <= maxAge) {
            this.age = age;
        }
        return this;
    }
    @Override
    public BlockState withAge(int age) {
        this.age = age;
        return super.withAge(age);
    }
    @Override
    protected int getAge(BlockState state) {
        return age = super.getAge(state);
    }
    public boolean use() {
        return --uses < 0;
    }
    public Crop setUses(int uses) {
        this.uses = uses;
        return this;
    }
    public int getUses() {
        return uses;
    }
    public boolean isMature() {
        return age == maxAge;
    }
}