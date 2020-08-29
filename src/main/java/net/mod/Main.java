package net.mod;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.Material;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.client.MinecraftClient;
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
	public static final Lube LUBE = new Lube(FabricBlockSettings.of(Material.METAL).breakByHand(true).sounds(BlockSoundGroup.HONEY).velocityMultiplier(2));
	public static final Scythe SCYTHE = new Scythe(new Item.Settings().maxDamage(20).group(ITEMS));
	public static final Barley BARLEY = new Barley();
	public static final Item BARLEY_SEED = new BlockItem(BARLEY, new Item.Settings().group(ITEMS));
	public static final Item WHEATSTICK = new Item(new Item.Settings().group(ITEMS));
	public static final Countertop COUNTERTOP = new Countertop();
	public static final Item COUNTERTOP_ITEM = new BlockItem(COUNTERTOP, new Item.Settings().group(ITEMS));
	public static final Knife IRON_KNIFE = new Knife(new Item.Settings().group(ITEMS), false);
	public static final Knife BROKEN_IRON_KNIFE = new Knife(new Item.Settings(), true);
	public static BlockEntityType<CountertopEntity> COUNTERTOP_ENTITY;
	@Override
	public void onInitialize() {
		IMREK = new Imrek(new Item.Settings().group(ITEMS));
		Registry.register(Registry.ITEM, ID("imrek"), IMREK);
		Registry.register(Registry.BLOCK, ID("lube"), LUBE);
		Registry.register(Registry.ITEM, ID("lube"), new BlockItem(LUBE, new Item.Settings().group(ITEMS)));
		Registry.register(Registry.ITEM, ID("scythe"), SCYTHE);
		Registry.register(Registry.BLOCK, ID("barley"), BARLEY);
		Registry.register(Registry.ITEM, ID("barley_seed"), BARLEY_SEED);
		Registry.register(Registry.ITEM, ID("wheatstick"), WHEATSTICK);
		Registry.register(Registry.ITEM, ID("iron_knife"), IRON_KNIFE);
		Registry.register(Registry.ITEM, ID("broken_iron_knife"), BROKEN_IRON_KNIFE);
		Registry.register(Registry.BLOCK, ID("countertop"), COUNTERTOP);
		Registry.register(Registry.ITEM, ID("countertop_item"), COUNTERTOP_ITEM);
		COUNTERTOP_ENTITY = Registry.register(Registry.BLOCK_ENTITY_TYPE, ID("countertop_entity"), BlockEntityType.Builder.create(CountertopEntity::new, COUNTERTOP).build(null));
	}
	public static Identifier ID(String name) {
		return new Identifier("mod", name);
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