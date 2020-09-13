package net.mod;

import java.util.function.Supplier;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.item.Item;
import net.minecraft.util.registry.Registry;
import net.minecraft.block.Material;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.item.BlockItem;
import net.mod.blockentities.CountertopEntity;
import net.mod.blockentities.StakeEntity;
import net.mod.blocks.Lube;
import net.mod.blocks.Barley;
import net.mod.blocks.Tomato;
import net.mod.blocks.Pepper;
import net.mod.blocks.Cucumber;
import net.mod.blocks.Countertop;
import net.mod.blocks.Stake;
import net.mod.items.Knife;
import net.mod.items.Scythe;

public class Stuff {
    public enum Blocks {
        LUBE(new Lube(FabricBlockSettings.of(Material.METAL).breakByHand(true).sounds(BlockSoundGroup.HONEY).velocityMultiplier(2))),
        BARLEY(new Barley()),
        TOMATO_PLANT(new Tomato()),
        PEPPER_PLANT(new Pepper()),
        CUCUMBER_PLANT(new Cucumber()),
        COUNTERTOP(new Countertop()),
        STAKE(new Stake());
        private Block block;
        private Blocks(Block block) {
            this.block = block;
        }
        public Block asBlock() {
            return this.block;
        }
    }
    public enum Items {
        LUBE_ITEM(new BlockItem(Blocks.LUBE.block, Main.Group)),
        SCYTHE(new Scythe(Main.Group)),
        IRON_KNIFE(new Knife(Main.Group, false)),
        BROKEN_IRON_KNIFE(new Knife(new Item.Settings(), true)),
        BARLEY_SEED(new BlockItem(Blocks.BARLEY.block, Main.Group)),
        WHEATSTICK(basicItem()),
        TOMATO_SEED(basicItem()),
        TOMATO_ITEM(basicItem()),
    	PEPPER_SEED(basicItem()),
        PEPPER_ITEM(basicItem()),
        CUCUMBER_SEED(basicItem()),
        CUCUMBER_ITEM(basicItem()),
        COUNTERTOP_ITEM(new BlockItem(Blocks.COUNTERTOP.block, Main.Group)),
        STAKE_ITEM(new BlockItem(Blocks.STAKE.block, Main.Group));
        private Item item;
        private Items(Item item) {
            this.item = item;
        }
        public Item asItem() {
            return this.item;
        }
    }
    public enum BlockEntities {
        COUNTERTOP_ENTITY(blockEntity(CountertopEntity::new, Blocks.COUNTERTOP.block)),
        STAKE_ENTITY(blockEntity(StakeEntity::new, Blocks.STAKE.block));
        private BlockEntityType<?> blockEntity;
        private BlockEntities(BlockEntityType<?> blockEntity) {
            this.blockEntity = blockEntity;
        }
        public BlockEntityType<?> asBlockEntity() {
            return this.blockEntity;
        }
    }
    private static boolean added = false;
    private static Item basicItem() {
        return new Item(Main.Group);
    }
    private static BlockEntityType<?> blockEntity(Supplier<? extends BlockEntity> supplier, Block block) {
        return BlockEntityType.Builder.create(supplier, block).build(null);
    }
    public static void addAll() {
        if(!added) {
            added = true;
            for(Stuff.Items itemEntry : Items.values()) {
                Registry.register(Registry.ITEM, Main.ID(itemEntry.name().toLowerCase()), itemEntry.asItem());
            }
            for(Stuff.Blocks blockEntry : Blocks.values()) {
                Registry.register(Registry.BLOCK, Main.ID(blockEntry.name().toLowerCase()), blockEntry.asBlock());
            }
            for(Stuff.BlockEntities blockEntityEntry : BlockEntities.values()) {
                Registry.register(Registry.BLOCK_ENTITY_TYPE, Main.ID(blockEntityEntry.name().toLowerCase()), blockEntityEntry.asBlockEntity());
            }
        }
    }
}