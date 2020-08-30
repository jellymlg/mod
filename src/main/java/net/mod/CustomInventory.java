package net.mod;

import net.minecraft.inventory.Inventories;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.util.collection.DefaultedList;

public interface CustomInventory extends Inventory {
    DefaultedList<ItemStack> getItems();
    @Override
    default int size() {
        return 1;
    }
    @Override
    default boolean isEmpty() {
        for(int i = 0; i < size(); i++) {
            if(!getStack(i).isEmpty()) {
                return false;
            }
        }
        return true;
    }
    @Override
    default ItemStack getStack(int slot) {
        return getItems().get(slot);
    }
    @Override
    default ItemStack removeStack(int slot, int amount) {
        ItemStack removed = Inventories.splitStack(getItems(), slot, amount);
        if(!removed.isEmpty()) {
            markDirty();
        }
        return removed;
    }
    @Override
    default ItemStack removeStack(int slot) {
        return Inventories.removeStack(getItems(), slot);
    }
    @Override
    default void setStack(int slot, ItemStack stack) {
        getItems().set(slot, stack);
        if(stack.getCount() > getMaxCountPerStack()) {
            stack.setCount(getMaxCountPerStack());
        }
    }
    @Override
    default void clear() {
        getItems().clear();
    }
}
