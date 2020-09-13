package net.mod.items;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.item.Items;
import net.minecraft.item.SwordItem;
import net.minecraft.recipe.Ingredient;
import net.minecraft.util.ActionResult;
import net.mod.utility.CustomToolMaterial;
import net.mod.Stuff;
import net.mod.utility.Crop;

public class Scythe extends SwordItem {
    public Scythe(Settings settings) {
        super(new CustomToolMaterial(0, 30, 1.0f, 0.0f, 0, Ingredient.ofItems(Items.IRON_INGOT)), 0, -3.0f, settings);
    }
    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        BlockState state = context.getWorld().getBlockState(context.getBlockPos());
        if(state.getBlock().is(Stuff.Blocks.BARLEY.asBlock()) && ((Crop) state.getBlock()).isMature(state)) {
            Block.dropStack(context.getWorld(), context.getBlockPos(), new ItemStack(Stuff.Items.WHEATSTICK.asItem(), 2));
            Block.dropStack(context.getWorld(), context.getBlockPos(), new ItemStack(Stuff.Items.BARLEY_SEED.asItem(), 1));
            context.getWorld().setBlockState(context.getBlockPos(), ((Crop) state.getBlock()).withAge(0), 2);
            super.postHit(context.getStack(), null, context.getPlayer());
            return ActionResult.SUCCESS;
        }
        return ActionResult.PASS;
    }
}