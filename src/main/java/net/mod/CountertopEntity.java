package net.mod;

import net.fabricmc.fabric.api.block.entity.BlockEntityClientSerializable;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.Inventories;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.collection.DefaultedList;

public class CountertopEntity extends BlockEntity implements CustomInventory, BlockEntityClientSerializable {
    private final String CLICK_PROGRESS = "click_progress";
    private int clicks = 0;
    private final DefaultedList<ItemStack> item = DefaultedList.ofSize(size(), ItemStack.EMPTY);
    public CountertopEntity() {
        super(Main.COUNTERTOP_ENTITY);
    }
    @Override
    public int size() {
        return 1;
    }
    @Override
    public DefaultedList<ItemStack> getItems() {
        return item;
    }
    @Override
    public void fromTag(BlockState state, CompoundTag tag) {
        super.fromTag(state, tag);
        Inventories.fromTag(tag, item);
        clicks = tag.getInt(CLICK_PROGRESS);
    }
    @Override
    public CompoundTag toTag(CompoundTag tag) {
        Inventories.toTag(tag, item);
        tag.putInt(CLICK_PROGRESS, clicks);
        return super.toTag(tag);
    }
    @Override
    public CompoundTag toInitialChunkDataTag() {
        CompoundTag tag = Inventories.toTag(super.toInitialChunkDataTag(), item);
        tag.putInt(CLICK_PROGRESS, clicks);
        return tag;
    }
    public Item getItem() {
        return item.get(0).getItem();
    }
    public void setItem(Item item) {
        setStack(0, (item == null) ? ItemStack.EMPTY : new ItemStack(item));
        markDirty();
    }
    @Override
    public boolean canPlayerUse(PlayerEntity player) {
        return false;
    }
    @Override
    public int getMaxCountPerStack() {
        return 1;
    }
    public boolean click() {
        return ++clicks == 3;
    }
    public void reset() {
        clicks = 0;
        setItem(null);
        markDirty();
    }
    @Override
    public void fromClientTag(CompoundTag tag) {
        fromTag(null, tag);
    }
    @Override
    public CompoundTag toClientTag(CompoundTag tag) {
        return toTag(tag);
    }
}