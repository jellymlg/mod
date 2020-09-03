package net.mod;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendereregistry.v1.BlockEntityRendererRegistry;
import net.mod.utility.CountertopRenderer;
import net.mod.utility.StakeRenderer;

public class MainClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        BlockEntityRendererRegistry.INSTANCE.register(Main.COUNTERTOP_ENTITY, CountertopRenderer::new);
        BlockEntityRendererRegistry.INSTANCE.register(Main.STAKE_ENTITY, StakeRenderer::new);
    }
}