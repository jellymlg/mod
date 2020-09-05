package net.mod.utility;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.entity.BlockEntityRenderDispatcher;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;

public class StakeRenderer extends BlockEntityRenderer<StakeEntity> {
    public StakeRenderer(BlockEntityRenderDispatcher dispatcher) {
        super(dispatcher);
    }
    @Override
    public void render(StakeEntity entity, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay) {
        if(entity.hasPlant()) {
            matrices.push();
            MinecraftClient.getInstance().getBlockRenderManager().renderBlock(entity.getState(), entity.getPos(), entity.getWorld(), matrices, vertexConsumers.getBuffer(RenderLayer.getCutout()), false, entity.getWorld().getRandom());
            matrices.pop();
        }
    }
}