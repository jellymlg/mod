package net.mod;

import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.HoeItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ToolMaterial;
import net.minecraft.network.MessageType;
import net.minecraft.text.LiteralText;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class Scythe extends HoeItem {
    public Scythe(ToolMaterial material, int attackDamage, float attackSpeed, Settings settings) {
        super(material, attackDamage, attackSpeed, settings);
    }
    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity playerEntity, Hand hand) {
        MinecraftClient instance = MinecraftClient.getInstance();
        String str = world.getBlockState(new BlockPos(instance.crosshairTarget.getPos())).getBlock().getName().getString();
        MinecraftClient.getInstance().getServer().getPlayerManager().broadcastChatMessage(new LiteralText(str), MessageType.CHAT, playerEntity.getUuid());
        return new TypedActionResult<>(ActionResult.SUCCESS, playerEntity.getStackInHand(hand));
    }
}