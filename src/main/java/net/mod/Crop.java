package net.mod;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.CropBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.registry.Registry;
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
        if(this.isMature(state) && player.getMainHandStack().getItem() == Registry.ITEM.get(Main.ID("scythe"))) {
            Block.dropStack(world, pos, new ItemStack(Items.WHEAT, 5));
            world.setBlockState(pos, this.withAge(0), 2);
        }
        if(!this.isMature(state) && player.getMainHandStack().getItem() == Items.BONE_MEAL) {
            this.applyGrowth(world, pos, state);
            if(!player.abilities.creativeMode) {
                player.getMainHandStack().decrement(1);
            }
        }
        return ActionResult.SUCCESS;
    }
}