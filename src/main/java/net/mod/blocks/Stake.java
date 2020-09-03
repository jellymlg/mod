package net.mod.blocks;

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
import net.minecraft.item.ItemConvertible;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.mod.Main;
import net.mod.utility.StakeEntity;

public class Stake extends CropBlock implements BlockEntityProvider {
    private static final VoxelShape OUTLINE = Block.createCuboidShape(6.0D, 0.0D, 6.0D, 10.0D, 16.0D, 10.0D);
    private StakeEntity entity;
    public Stake() {
        super(FabricBlockSettings.copy(Blocks.OAK_PLANKS).noCollision());
        BlockRenderLayerMap.INSTANCE.putBlock(this, RenderLayer.getCutout());
    }
    @Override
    public boolean hasRandomTicks(BlockState state) {
        return entity.hasPlant();
    }
    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return OUTLINE;
    }
    @Override
    public BlockEntity createBlockEntity(BlockView world) {
        return entity = new StakeEntity();
    }
    @Override
    protected ItemConvertible getSeedsItem() {
        if(entity.hasPlant()) {
            if(entity.getPlant() instanceof Tomato) {
                return ((Tomato) entity.getPlant()).getSeedsItem();
            }
        }
        return Main.STAKE;
    }
}