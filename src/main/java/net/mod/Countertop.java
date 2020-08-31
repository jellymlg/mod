package net.mod;

import java.util.HashMap;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.BlockEntityProvider;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.HorizontalFacingBlock;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.StateManager.Builder;
import net.minecraft.state.property.Properties;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.ItemScatterer;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;

public class Countertop extends HorizontalFacingBlock implements BlockEntityProvider {
    private static final HashMap<Item,ItemStack[]> RECIPES = new HashMap<Item,ItemStack[]>(0);
    public Countertop() {
        super(FabricBlockSettings.copyOf(Blocks.GLASS).sounds(BlockSoundGroup.STONE).requiresTool().strength(1.5f, 6.0f));
        BlockRenderLayerMap.INSTANCE.putBlock(this, RenderLayer.getTranslucent());
        setDefaultState(stateManager.getDefaultState().with(Properties.HORIZONTAL_FACING, Direction.NORTH));
    }
    @Override
    public BlockEntity createBlockEntity(BlockView world) {
        return new CountertopEntity();
    }
    @Override
    protected void appendProperties(Builder<Block, BlockState> builder) {
        builder.add(Properties.HORIZONTAL_FACING);
    }
    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        return getDefaultState().with(Properties.HORIZONTAL_FACING, ctx.getPlayerFacing().getOpposite());
    }
    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        CountertopEntity block = (CountertopEntity) world.getBlockEntity(pos);
        ItemStack held = player.getStackInHand(hand);
        if(block.isEmpty()) {
            if(!held.isEmpty() && !held.getItem().equals(Main.COUNTERTOP_ITEM)) {
                block.setItem(held.getItem());
                if(!player.isCreative()) {
                    held.decrement(1);
                }
                player.playSound(SoundEvents.ITEM_ARMOR_EQUIP_GENERIC, 1.0f, 1.0f);
                return ActionResult.SUCCESS;
            }
        }else if(RECIPES.containsKey(block.getItem()) && held.getItem().equals(Main.IRON_KNIFE)) {
            if(block.click()) {
                ItemEntity entity;
                for(ItemStack stack : RECIPES.get(block.getItem())) {
                    entity = new ItemEntity(world, pos.getX() + 0.5d, pos.up().getY(), pos.getZ() + 0.5d, stack.copy());
                    entity.setVelocity(0.0d, 0.0d, 0.0d);
                    world.spawnEntity(entity);
                }
                block.reset();
            }
            return ActionResult.SUCCESS;
        }else {
            if(!player.isCreative()) {
                player.giveItemStack(new ItemStack(block.getItem()));
            }
            block.reset();
            return ActionResult.SUCCESS;
        }
        return ActionResult.PASS;
    }
    @Override
    public void onBreak(World world, BlockPos pos, BlockState state, PlayerEntity player) {
        CountertopEntity block = (CountertopEntity) world.getWorld().getBlockEntity(pos);
        if(!block.isEmpty()) {
            ItemScatterer.spawn(world.getWorld(), pos, block.getItems());
        }
        super.onBreak(world, pos, state, player);
    }
    public static void addRecipe(Item in, ItemStack[] out) {
        RECIPES.putIfAbsent(in, out);
    }
}