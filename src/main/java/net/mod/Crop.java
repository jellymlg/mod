package net.mod;

import net.minecraft.block.BlockState;
import net.minecraft.block.CropBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Items;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class Crop extends CropBlock {
    public Crop(Settings settings) {
        super(settings);
    }
    @Override
    public int getMaxAge() {
        return 2;
    }
    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if(!this.isMature(state) && player.getMainHandStack().getItem() == Items.BONE_MEAL) {
            this.applyGrowth(world, pos, state);
            if(!player.abilities.creativeMode) {
                player.getMainHandStack().decrement(1);
            }
            return ActionResult.SUCCESS;
        }
        return ActionResult.PASS;
    }
    @Override
    protected int getGrowthAmount(World world) {
        return (int) (Math.random() * 2) + 1;
     }
}