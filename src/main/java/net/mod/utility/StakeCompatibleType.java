package net.mod.utility;

import java.util.HashMap;
import net.minecraft.item.Item;
import net.mod.Stuff;

public enum StakeCompatibleType {
    TOMATO("tomato", Stuff.Items.TOMATO_SEED.asItem(), (StakeCompatible) Stuff.Blocks.TOMATO_PLANT.asBlock()),
    PEPPER("pepper", Stuff.Items.PEPPER_SEED.asItem(), (StakeCompatible) Stuff.Blocks.PEPPER_PLANT.asBlock()),
    CUCUMBER("cucumber", Stuff.Items.CUCUMBER_SEED.asItem(), (StakeCompatible) Stuff.Blocks.CUCUMBER_PLANT.asBlock());
    private final String type;
    private final Item seed;
    private final StakeCompatible block;
    private static final HashMap<String,StakeCompatibleType> list = new HashMap<String,StakeCompatibleType>(0);
    private StakeCompatibleType(String type, Item seed, StakeCompatible block) {
        this.type = type;
        this.seed = seed;
        this.block = block;
    }
    public String asString() {
        return this.type;
    }
    public Item asSeed() {
        return this.seed;
    }
    public StakeCompatible asBlock() {
        return this.block;
    }
    public static StakeCompatibleType getFromString(String type) {
        return list.get(type);
    }
    public static StakeCompatibleType getTypeOfSeed(Item seed) {
        for(StakeCompatibleType entry : list.values()) {
            if(entry.seed.equals(seed)) {
                return entry;
            }
        }
        return null;
    }
    static {
        for(StakeCompatibleType entry : StakeCompatibleType.values()) {
            list.put(entry.type, entry);
        }
    }
}