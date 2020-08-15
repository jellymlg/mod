package net.mod;

import net.minecraft.block.BlockState;
import net.minecraft.block.CropBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;

public class Crop extends CropBlock {
    public Crop(Settings settings) {
        super(settings);
    }
    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if(player.getActiveItem().getItem() == Registry.ITEM.get(Main.ID("scythe"))) {
            //this.dropStacks(state, world, pos, blockEntity, entity, stack);
        }
        return ActionResult.SUCCESS;
    }
}