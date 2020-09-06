package net.mod.blocks;

import java.util.Random;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.BlockEntityProvider;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.CropBlock;
import net.minecraft.block.ShapeContext;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.ItemScatterer;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.mod.Main;
import net.mod.utility.StakeCompatibleType;
import net.mod.utility.StakeEntity;

public class Stake extends CropBlock implements BlockEntityProvider {
    private static final VoxelShape OUTLINE = Block.createCuboidShape(6.0D, 0.0D, 6.0D, 10.0D, 16.0D, 10.0D);
    public Stake() {
        super(FabricBlockSettings.copy(Blocks.WHEAT).sounds(BlockSoundGroup.WOOD));
        BlockRenderLayerMap.INSTANCE.putBlock(this, RenderLayer.getCutout());
    }
    @Override
    public BlockEntity createBlockEntity(BlockView world) {
        return new StakeEntity();
    }
    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return OUTLINE;
    }
    @Override
    public void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        if(world.getBaseLightLevel(pos, 0) >= 9 && getAge(state) < getMaxAge() && random.nextInt((int)(25.0F / getAvailableMoisture(this, world, pos)) + 1) == 0) {
            ((StakeEntity) world.getBlockEntity(pos)).setAge(((StakeEntity) world.getBlockEntity(pos)).getAge() + 1);
        }
    }
    @Override
    public void applyGrowth(World world, BlockPos pos, BlockState state) {
        StakeEntity entity = (StakeEntity) world.getBlockEntity(pos);
        entity.setAge(Math.min(entity.getAge() + getGrowthAmount(world), entity.getMaxAge()));
    }
    @Override
    protected ItemConvertible getSeedsItem() {
        return Main.STAKE;
    }
    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        StakeEntity entity = (StakeEntity) world.getBlockEntity(pos);
        if(player.getMainHandStack().isEmpty()) {
            if(entity.hasPlant() && entity.isMature()) {
                entity.setAge(4);
                if(entity.use()) {
                    world.breakBlock(pos, false, player);
                }
                ItemScatterer.spawn(world, pos.getX(), pos.getY(), pos.getZ(), new ItemStack(Main.BROKEN_IRON_KNIFE));
                return ActionResult.SUCCESS;
            }
            return ActionResult.PASS;
        }else {
            if(player.getMainHandStack().getItem().equals(Main.TOMATO_SEED) && !entity.hasPlant()) {
                entity.setPlant(Main.TOMATO, StakeCompatibleType.TOMATO);
                if(!player.isCreative()) {
                    player.getMainHandStack().decrement(1);
                }
                return ActionResult.SUCCESS;
            }
        }
        return ActionResult.PASS;
    }
    @Override
    public boolean isFertilizable(BlockView world, BlockPos pos, BlockState state, boolean isClient) {
        return !((StakeEntity) world.getBlockEntity(pos)).isMature();
    }
}