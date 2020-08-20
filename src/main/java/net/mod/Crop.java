package net.mod;

import java.util.Comparator;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.CropBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.Items;
import net.minecraft.state.StateManager.Builder;
import net.minecraft.state.property.IntProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;

public class Crop extends CropBlock {
    public static final IntProperty AGE = Properties.AGE_7;
    public Crop(Settings settings) {
        super(settings);
    }
    @Override
    public int getMaxAge() {
        return AGE.getValues().stream().max(Comparator.naturalOrder()).get().intValue();
    }
    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if(!this.isMature(state) && player.getMainHandStack().getItem() == Items.BONE_MEAL) {
            this.applyGrowth(world, pos, state);
            if(!player.abilities.creativeMode) {
                player.getMainHandStack().decrement(1);
            }
            return ActionResult.SUCCESS;
        }
        return ActionResult.PASS;
    }
    @Override
    protected int getGrowthAmount(World world) {
        return (int) (Math.random() * getMaxAge()) + 1;
    }
    @Override
    protected ItemConvertible getSeedsItem() {
        return Main.CROPLOOT;
    }
    @Override
    protected void appendProperties(Builder<Block, BlockState> builder) {
        builder.add(AGE);
    }
    @Override
    public IntProperty getAgeProperty() {
        return AGE;
    }
    @Override
    public boolean isTranslucent(BlockState state, BlockView world, BlockPos pos) {
        return true;
    }
}