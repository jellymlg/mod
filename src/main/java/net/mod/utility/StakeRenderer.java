package net.mod.utility;

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
    }
}