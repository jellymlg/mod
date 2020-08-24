package net.mod;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.registry.Registry;

public class CountertopEntity extends BlockEntity {
    public ItemStack item = ItemStack.EMPTY;
    public CountertopEntity() {
        super(Main.COUNTERTOP_ENTITY);
    }
    @Override
    public CompoundTag toTag(CompoundTag tag) {
        super.toTag(tag);
        tag.putInt("item", Item.getRawId(item.getItem()));
        return tag;
    }
    @Override
    public void fromTag(BlockState state, CompoundTag tag) {
        super.fromTag(state, tag);
        item = new ItemStack(Registry.ITEM.get(tag.getInt("item")));
    }
}