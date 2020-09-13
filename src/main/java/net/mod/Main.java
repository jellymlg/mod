package net.mod;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.client.MinecraftClient;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.network.MessageType;
import net.minecraft.network.packet.s2c.play.GameMessageS2CPacket;
import net.minecraft.text.LiteralText;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.registry.Registry;
import net.mod.blocks.Countertop;
import net.mod.items.Imrek;

public class Main implements ModInitializer {
	public static Item IMREK = new Item(new Item.Settings());
	public static final ItemGroup Group = FabricItemGroupBuilder.create(ID("items")).icon(() -> new ItemStack(IMREK)).build();
	public static final Item.Settings _1Settings = new Item.Settings().group(Group).maxCount(1);
	public static final Item.Settings _16Settings = new Item.Settings().group(Group).maxCount(16);
	public static final Item.Settings _64Settings = new Item.Settings().group(Group).maxCount(64);
	@Override
	public void onInitialize() {
		IMREK = new Imrek(_1Settings);
		Registry.register(Registry.ITEM, ID("imrek"), IMREK);
		Stuff.addAll();
		Countertop.addRecipe(Stuff.Items.IRON_KNIFE.asItem(), Items.PUMPKIN, new ItemStack[] {new ItemStack(Items.CARVED_PUMPKIN, 1), new ItemStack(Items.PUMPKIN_SEEDS, 3)});
		Countertop.addRecipe(Stuff.Items.IRON_KNIFE.asItem(), Items.MELON, new ItemStack[] {new ItemStack(Items.MELON_SLICE, 9)});
	}
	public static Identifier ID(String name) {
		return new Identifier("mod", name);
	}
	public static void send(String msg) {
		MinecraftClient.getInstance().getServer().getPlayerManager().sendToAll(new GameMessageS2CPacket(new LiteralText(msg), MessageType.CHAT, MathHelper.randomUuid()));
	}
	/*public static Block getBlockFromHit(World world, HitResult hit) {
		if(hit.getType() == HitResult.Type.BLOCK) {
            return world.getBlockState(((BlockHitResult) hit).getBlockPos()).getBlock();
        }else {
			return Blocks.AIR;
		}
	}
	public static HitResult rayTrace(double maxDistance) {
		return MinecraftClient.getInstance().getCameraEntity().rayTrace(maxDistance, 0.0F, false);
	}*/
}