package net.mod;

import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.network.MessageType;
import net.minecraft.text.LiteralText;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class Scythe extends Item {
    public Scythe(Settings settings) {
        super(settings);
    }
    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity playerEntity, Hand hand) {
        String str = world.getBlockState(new BlockPos(MinecraftClient.getInstance().crosshairTarget.getPos())).getBlock().getName().getString();
        MinecraftClient.getInstance().getServer().getPlayerManager().broadcastChatMessage(new LiteralText(str), MessageType.CHAT, playerEntity.getUuid());
        return new TypedActionResult<>(ActionResult.SUCCESS, playerEntity.getStackInHand(hand));
    }
}