package net.mod;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;

public class Scythe extends Item {
    public Scythe(Settings settings) {
        super(settings);
    }
    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        ItemStack tool = context.getStack();
        BlockState state = context.getWorld().getBlockState(context.getBlockPos());
        if(state.getBlock().is(Main.CROP) && ((Crop) state.getBlock()).isMature(state)) {Main.send("bruh");
            Block.dropStack(context.getWorld(), context.getBlockPos(), new ItemStack(Main.WHEATSTICK, 2));
            Block.dropStack(context.getWorld(), context.getBlockPos(), new ItemStack(Main.CROPLOOT, 1));
            context.getWorld().setBlockState(context.getBlockPos(), ((Crop) state.getBlock()).withAge(0), 2);
            if(!context.getPlayer().isCreative()) {
                tool.setDamage(tool.getDamage() + 1);
            }
            if(tool.getDamage() > tool.getMaxDamage()) {
                context.getPlayer().inventory.removeOne(tool);
                context.getPlayer().playSound(SoundEvents.ITEM_SHIELD_BREAK, 1.0f, 1.0f);
            }
            return ActionResult.SUCCESS;
        }
        return ActionResult.PASS;
    }
}