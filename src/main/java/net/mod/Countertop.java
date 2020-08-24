package net.mod;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.BlockEntityProvider;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.ItemScatterer;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;

public class Countertop extends Block implements BlockEntityProvider {
    public Countertop() {
        super(FabricBlockSettings.copyOf(Blocks.STONE));
    }
    @Override
    public BlockEntity createBlockEntity(BlockView world) {
        return new CountertopEntity();
    }
    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        CountertopEntity block = (CountertopEntity) world.getBlockEntity(pos);
        System.out.println("start: " + block.item);
        if(block.item.isEmpty()) {
            if(!player.getStackInHand(hand).isEmpty()) {
                block.item = player.getStackInHand(hand).copy();
                block.item.setCount(1);
                block.markDirty();
                player.getStackInHand(hand).decrement(1);
                player.playSound(SoundEvents.ITEM_ARMOR_EQUIP_GENERIC, 1.0f, 1.0f);System.out.println("finish: " + block.item);
                return ActionResult.SUCCESS;
            }
        }else {
            player.giveItemStack(block.item.copy());
            block.item = ItemStack.EMPTY;
            block.markDirty();System.out.println("finish: " + block.item);
            return ActionResult.SUCCESS;
        }System.out.println("finish: " + block.item);
        return ActionResult.PASS;
    }
    @Override
    public void onBreak(World world, BlockPos pos, BlockState state, PlayerEntity player) {
        CountertopEntity block = (CountertopEntity) world.getWorld().getBlockEntity(pos);
        if(!block.item.isEmpty()) {
            ItemScatterer.spawn(world.getWorld(), pos.getX(), pos.getY(), pos.getZ(), block.item);
        }
        super.onBreak(world, pos, state, player);
    }
}