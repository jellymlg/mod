package net.mod;

import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class Scythe extends Item {
    public Scythe(Settings settings) {
        super(settings);
    }
    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity playerEntity, Hand hand) {
        HitResult hr = MinecraftClient.getInstance().getCameraEntity().rayTrace(20.0D, 0.0F, false);
        if(hr.getType() == HitResult.Type.BLOCK) {
            BlockPos pos = ((BlockHitResult) hr).getBlockPos();
            Main.send(pos.toShortString() + " --- " + world.getBlockState(pos).getBlock().getName().getString());
        }
        return new TypedActionResult<>(ActionResult.SUCCESS, playerEntity.getStackInHand(hand));
    }
}