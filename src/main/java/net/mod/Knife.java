package net.mod;

import net.fabricmc.fabric.api.object.builder.v1.client.model.FabricModelPredicateProviderRegistry;
import net.minecraft.block.BlockState;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.item.SwordItem;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class Knife extends SwordItem {
    private static final String BROKEN = "broken";
    public Knife(Settings settings) {
        super(new CustomToolMaterial(0, 1, 2.0F, 2.0F, 0, null), 0, -2.0f, settings);
        FabricModelPredicateProviderRegistry.register(this, new Identifier(BROKEN), (itemStack, clientWorld, livingEntity) -> {
            return itemStack.getTag().getBoolean(BROKEN) ? 1.0f : 0.0f;
        });
    }
    @Override
    public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        CompoundTag tag = stack.getTag();
        tag.putBoolean(BROKEN, Math.random() * 100 < 5.0);
        stack.setTag(tag);
        Main.send("" + stack.getTag().getBoolean(BROKEN));
        return true;
    }
    @Override
    public boolean postMine(ItemStack stack, World world, BlockState state, BlockPos pos, LivingEntity miner) {
        CompoundTag tag = stack.getTag();
        tag.putBoolean(BROKEN, Math.random() * 100 < 10.0);
        stack.setTag(tag);
        Main.send("" + stack.getTag().getBoolean(BROKEN));
        return false;
    }
    @Override
    public ActionResult useOnEntity(ItemStack stack, PlayerEntity user, LivingEntity entity, Hand hand) {
        return stack.getTag().getBoolean(BROKEN) ? ActionResult.FAIL : ActionResult.SUCCESS;
    }
    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        return context.getStack().getTag().getBoolean(BROKEN) ? ActionResult.FAIL : ActionResult.SUCCESS;
    }
}