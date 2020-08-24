package net.mod;

import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.BlockEntityProvider;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.BlockSoundGroup;
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
        super(FabricBlockSettings.copyOf(Blocks.GLASS).sounds(BlockSoundGroup.STONE).requiresTool().strength(1.5F, 6.0F));
        BlockRenderLayerMap.INSTANCE.putBlock(this, RenderLayer.getTranslucent());
    }
    @Override
    public BlockEntity createBlockEntity(BlockView world) {
        return new CountertopEntity();
    }
    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        CountertopEntity block = (CountertopEntity) world.getBlockEntity(pos);
        System.out.println("start: " + block.getStack(0));
        if(block.isEmpty()) {
            if(!player.getStackInHand(hand).isEmpty()) {
                block.setStack(0, player.getStackInHand(hand).copy());
                block.getStack(0).setCount(1);
                block.markDirty();
                if(!player.isCreative()) {
                    player.getStackInHand(hand).decrement(1);
                }
                player.playSound(SoundEvents.ITEM_ARMOR_EQUIP_GENERIC, 1.0f, 1.0f);System.out.println("finish: " + block.getStack(0));
                return ActionResult.SUCCESS;
            }
        }else {
            player.giveItemStack(block.getStack(0).copy());
            block.setStack(0, ItemStack.EMPTY);
            block.markDirty();System.out.println("finish: " + block.getStack(0));
            return ActionResult.SUCCESS;
        }System.out.println("finish: " + block.getStack(0));
        return ActionResult.PASS;
    }
    @Override
    public void onBreak(World world, BlockPos pos, BlockState state, PlayerEntity player) {
        CountertopEntity block = (CountertopEntity) world.getWorld().getBlockEntity(pos);
        if(!block.isEmpty()) {
            ItemScatterer.spawn(world.getWorld(), pos.getX(), pos.getY(), pos.getZ(), block.getStack(0));
        }
        super.onBreak(world, pos, state, player);
    }
}