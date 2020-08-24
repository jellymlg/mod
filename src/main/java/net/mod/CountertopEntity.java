package net.mod;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.inventory.Inventories;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.collection.DefaultedList;

public class CountertopEntity extends BlockEntity implements SingletInventory {
    private final DefaultedList<ItemStack> item = DefaultedList.ofSize(1, ItemStack.EMPTY);
    public CountertopEntity() {
        super(Main.COUNTERTOP_ENTITY);
    }
    @Override
    public DefaultedList<ItemStack> getItems() {
        return item;
    }
    @Override
    public void fromTag(BlockState state, CompoundTag tag) {
        super.fromTag(state, tag);
        Inventories.fromTag(tag, item);
    }
    @Override
    public CompoundTag toTag(CompoundTag tag) {
        Inventories.toTag(tag, item);
        return super.toTag(tag);
    }
    @Override
    public CompoundTag toInitialChunkDataTag() {
        return Inventories.toTag(super.toInitialChunkDataTag(), item);
    }
}