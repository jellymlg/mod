package net.mod;

import net.minecraft.block.BlockState;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.item.Items;
import net.minecraft.item.SwordItem;
import net.minecraft.recipe.Ingredient;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class Knife extends SwordItem {
    public Knife(Settings settings, boolean broken) {
        super(new CustomToolMaterial(0, 1, 2.0f, broken ? 0.0f : 2.0f, 0, broken ? Ingredient.ofItems(Items.FLINT) : null), 0, -2.0f, settings);
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
        BlockState block = context.getWorld().getBlockState(context.getBlockPos());
        if(block.isOf(Main.COUNTERTOP) && !((CountertopEntity) context.getWorld().getBlockEntity(context.getBlockPos())).getItems().isEmpty()) {
            Main.send("clicc");
            return ActionResult.SUCCESS;
        }
        return ActionResult.PASS;
    }
    private void tryBreaking(LivingEntity entity, ItemStack stack, boolean isBlock) {
        if(stack.getItem().equals(Main.IRON_KNIFE) && Math.random() * 100 < (isBlock ? 10.0D : 5.0D)) {
            ((PlayerEntity) entity).inventory.removeOne(stack);
            ((PlayerEntity) entity).inventory.insertStack(new ItemStack(Main.BROKEN_IRON_KNIFE));
            entity.playSound(SoundEvents.ITEM_SHIELD_BREAK, 1.0f, 1.0f);
        }
    }
}