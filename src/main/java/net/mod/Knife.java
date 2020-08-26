package net.mod;

import net.minecraft.item.SwordItem;

public class Knife extends SwordItem {
    public Knife(Settings settings) {
        super(new CustomToolMaterial(0, 50, 2.0F, 2.0F, 0, null), 0, -2.0f, settings);
    }
}