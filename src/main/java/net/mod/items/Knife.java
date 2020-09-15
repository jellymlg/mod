package net.mod.items;

import net.minecraft.block.BlockState;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.item.SwordItem;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.mod.Stuff;
import net.mod.blockentities.CountertopEntity;
import net.mod.utility.CustomToolMaterial;

public class Knife extends SwordItem {
    public Knife(Settings settings, boolean broken) {
        super(new CustomToolMaterial(0, 1, 2.0f, broken ? 0.0f : 2.0f, 0, null), 0, -2.0f, settings);
    }
    @Override
    public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        tryBreaking(attacker, stack, false);
        return true;
    }
    @Override
    public boolean postMine(ItemStack stack, World world, BlockState state, BlockPos pos, LivingEntity miner) {
        tryBreaking(miner, stack, true);
        return true;
    }
    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        if(context.getWorld().getBlockState(context.getBlockPos()).isOf(Stuff.Blocks.COUNTERTOP.asBlock()) && !((CountertopEntity) context.getWorld().getBlockEntity(context.getBlockPos())).isEmpty()) {
            return ActionResult.SUCCESS;
        }else {
            return ActionResult.PASS;
        }
    }
    private void tryBreaking(LivingEntity entity, ItemStack stack, boolean isBlock) {
        if(stack.getItem().equals(Stuff.Items.IRON_KNIFE.asItem()) && Math.random() * 100 < (isBlock ? 10.0D : 5.0D)) {
            PlayerEntity player = (PlayerEntity) entity;
            player.inventory.setStack(player.inventory.getSlotWithStack(stack), new ItemStack(Stuff.Items.BROKEN_IRON_KNIFE.asItem()));
            player.playSound(SoundEvents.ITEM_SHIELD_BREAK, 1.0f, 1.0f);
        }
    }
}