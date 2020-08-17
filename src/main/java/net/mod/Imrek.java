package net.mod;

import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;

public class Imrek extends Item {
    public Imrek(Settings settings) {
        super(settings);
    }
    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        user.playSound(Registry.SOUND_EVENT.get((int) (Math.random() * Registry.SOUND_EVENT.getIds().size())), 1.0f, 1.0f);
        MinecraftClient client = MinecraftClient.getInstance();
        client.gameRenderer.showFloatingItem(new ItemStack(this));
        client.particleManager.addEmitter(user, ParticleTypes.TOTEM_OF_UNDYING, 30);
        return TypedActionResult.pass(user.getStackInHand(hand));
    }
}