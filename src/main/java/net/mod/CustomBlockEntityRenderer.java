package net.mod;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.WorldRenderer;
import net.minecraft.client.render.block.entity.BlockEntityRenderDispatcher;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.minecraft.client.render.model.json.ModelTransformation;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.Quaternion;

public class CustomBlockEntityRenderer extends BlockEntityRenderer<CountertopEntity> {
    public CustomBlockEntityRenderer(BlockEntityRenderDispatcher dispatcher) {
        super(dispatcher);
    }
    @Override
    public void render(CountertopEntity entity, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay) {
        matrices.push();
        if(!entity.isEmpty()) {
            matrices.translate(0.5D, 0.99D, 0.3D);
            matrices.multiply(new Quaternion(90.0f, 0.0f, 0.0f, true));
            matrices.scale(1.5f, 1.5f, 1.5f);
            int lightAbove = WorldRenderer.getLightmapCoordinates(entity.getWorld(), entity.getPos().up());
            MinecraftClient.getInstance().getItemRenderer().renderItem(entity.getStack(0), ModelTransformation.Mode.GROUND, lightAbove, overlay, matrices, vertexConsumers);
        }
        matrices.pop();
    }
}