package net.mod;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.WorldRenderer;
import net.minecraft.client.render.block.entity.BlockEntityRenderDispatcher;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.minecraft.client.render.model.json.ModelTransformation;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.Direction;

public class CustomBlockEntityRenderer extends BlockEntityRenderer<CountertopEntity> {
    private Direction q;
    private static final double Y_OFFSET = 0.95;
    public CustomBlockEntityRenderer(BlockEntityRenderDispatcher dispatcher) {
        super(dispatcher);
    }
    @Override
    public void render(CountertopEntity entity, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay) {
        matrices.push();
        if(!entity.isEmpty()) {
            q = entity.getCachedState().get(Properties.HORIZONTAL_FACING);
            directionalTranslation(matrices, q);
            matrices.multiply(q.getOpposite().getRotationQuaternion());
            matrices.scale(1.5f, 1.5f, 1.5f);
            int lightAbove = WorldRenderer.getLightmapCoordinates(entity.getWorld(), entity.getPos().up());
            MinecraftClient.getInstance().getItemRenderer().renderItem(entity.getStack(0), ModelTransformation.Mode.GROUND, lightAbove, overlay, matrices, vertexConsumers);
        }
        matrices.pop();
    }
    private void directionalTranslation(MatrixStack matrix, Direction dir) {
        switch(dir) {
            case NORTH:
                matrix.translate(0.5D, Y_OFFSET, 0.3D);
                break;
            case EAST:
                matrix.translate(0.7D, Y_OFFSET, 0.5D);
                break;
            case SOUTH:
                matrix.translate(0.5D, Y_OFFSET, 0.7D);
                break;
            case WEST:
                matrix.translate(0.3D, Y_OFFSET, 0.5D);
                break;
            case DOWN:break;
            case UP:break;
        }
    }
}