package net.mod;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendereregistry.v1.BlockEntityRendererRegistry;
import net.minecraft.block.entity.BlockEntityType;
import net.mod.utility.CountertopEntity;
import net.mod.utility.CountertopRenderer;
import net.mod.utility.StakeEntity;
import net.mod.utility.StakeRenderer;

public class MainClient implements ClientModInitializer {
    @SuppressWarnings("unchecked")
    @Override
    public void onInitializeClient() {
        BlockEntityRendererRegistry.INSTANCE.register((BlockEntityType<CountertopEntity>) Stuff.BlockEntities.COUNTERTOP_ENTITY.asBlockEntity(), CountertopRenderer::new);
        BlockEntityRendererRegistry.INSTANCE.register((BlockEntityType<StakeEntity>) Stuff.BlockEntities.STAKE_ENTITY.asBlockEntity(), StakeRenderer::new);
    }
}