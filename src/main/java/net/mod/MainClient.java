package net.mod;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendereregistry.v1.BlockEntityRendererRegistry;
import net.mod.utility.CustomBlockEntityRenderer;

public class MainClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        BlockEntityRendererRegistry.INSTANCE.register(Main.COUNTERTOP_ENTITY, CustomBlockEntityRenderer::new);
    }
}