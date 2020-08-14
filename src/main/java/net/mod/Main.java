package net.mod;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ToolItem;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class Main implements ModInitializer {
	public static Item IMREK = new Item(new Item.Settings());
	public static final ItemGroup ITEMS = FabricItemGroupBuilder.create(ID("items")).icon(() -> new ItemStack(IMREK)).build();
	public static final Block XD = new Block(FabricBlockSettings.of(Material.METAL).breakByHand(true).sounds(BlockSoundGroup.BAMBOO).velocityMultiplier(3));
	public static final ToolItem SCYTHE = new Scythe(SomeToolMaterial.SOME, 3, -2.5F, new Item.Settings().maxDamage(100).group(ITEMS));
	@Override
	public void onInitialize() {
		IMREK = new Item(new Item.Settings().group(ITEMS));
		Registry.register(Registry.ITEM, ID("imrek"), IMREK);
		Registry.register(Registry.BLOCK, ID("xd"), XD);
		Registry.register(Registry.ITEM, ID("xd"), new BlockItem(XD, new Item.Settings().group(ITEMS)));
		Registry.register(Registry.ITEM, ID("scythe"), SCYTHE);
	}
	private static Identifier ID(String itemName) {
		return new Identifier("mod", itemName);
	}
}