package net.mod;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.Material;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.network.MessageType;
import net.minecraft.network.packet.s2c.play.GameMessageS2CPacket;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.text.LiteralText;
import net.minecraft.util.Identifier;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;

public class Main implements ModInitializer {
	public static Item IMREK = new Item(new Item.Settings());
	public static final ItemGroup ITEMS = FabricItemGroupBuilder.create(ID("items")).icon(() -> new ItemStack(IMREK)).build();
	public static final Block LUBE = new Lube(FabricBlockSettings.of(Material.METAL).breakByHand(true).sounds(BlockSoundGroup.HONEY).velocityMultiplier(2));
	public static final Item SCYTHE = new Scythe(new Item.Settings().maxDamage(20).group(ITEMS));
	public static final Block CROP = new Crop(FabricBlockSettings.copy(Blocks.WHEAT));
	public static Item WHEATSTICK = new Item(new Item.Settings().group(ITEMS));
	public static final Item CROPLOOT = new BlockItem(CROP, new Item.Settings().group(ITEMS));
	@Override
	public void onInitialize() {
		IMREK = new Imrek(new Item.Settings().group(ITEMS));
		Registry.register(Registry.ITEM, ID("imrek"), IMREK);
		Registry.register(Registry.BLOCK, ID("lube"), LUBE);
		Registry.register(Registry.ITEM, ID("lube"), new BlockItem(LUBE, new Item.Settings().group(ITEMS)));
		Registry.register(Registry.ITEM, ID("scythe"), SCYTHE);
		Registry.register(Registry.BLOCK, ID("cropblock"), CROP);
		Registry.register(Registry.ITEM, ID("crop"), CROPLOOT);
		Registry.register(Registry.ITEM, ID("wheatstick"), WHEATSTICK);
		BlockRenderLayerMap.INSTANCE.putBlock(CROP, RenderLayer.getCutout());
	}
	public static Identifier ID(String itemName) {
		return new Identifier("mod", itemName);
	}
	public static void send(String msg) {
		MinecraftClient.getInstance().getServer().getPlayerManager().sendToAll(new GameMessageS2CPacket(new LiteralText(msg), MessageType.CHAT, MathHelper.randomUuid()));
	}
	public static Block getBlockFromHit(World world, HitResult hit) {
		if(hit.getType() == HitResult.Type.BLOCK) {
            return world.getBlockState(((BlockHitResult) hit).getBlockPos()).getBlock();
        }else {
			return Blocks.AIR;
		}
	}
	public static HitResult rayTrace(double maxDistance) {
		return MinecraftClient.getInstance().getCameraEntity().rayTrace(maxDistance, 0.0F, false);
	}
}