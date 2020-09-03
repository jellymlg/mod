package net.mod.utility;

import net.fabricmc.fabric.api.block.entity.BlockEntityClientSerializable;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.world.ServerWorld;
import net.mod.Main;
import net.mod.blocks.Stake;
import net.mod.blocks.Tomato;

public class StakeEntity extends BlockEntity implements BlockEntityClientSerializable {
    private static final String PLANT_TYPE = "plant_type";
    private static final String PLANT_AGE = "plant_age";
    private static final String PLANT_USES = "plant_uses";
    private Crop plant;
    public StakeEntity() {
        super(Main.STAKE_ENTITY);
    }
    public void setPlant(Crop plant) {
        this.plant = plant;
    }
    public Crop getPlant() {
        return plant;
    }
    public boolean hasPlant() {
        return plant != null;
    }
    @Override
    public void fromTag(BlockState state, CompoundTag tag) {
        super.fromTag(state, tag);
        plant = createPlant(tag.getString(PLANT_TYPE), tag.getInt(PLANT_AGE), tag.getInt(PLANT_USES));
    }
    @Override
    public CompoundTag toTag(CompoundTag tag) {
        if(hasPlant()) {
            tag.putString(PLANT_TYPE, getPlantTypeAsString());
            tag.putInt(PLANT_AGE, plant.getAge());
            tag.putInt(PLANT_USES, plant.getUses());
        }
        return super.toTag(tag);
    }
    @Override
    public CompoundTag toInitialChunkDataTag() {
        return toTag(super.toInitialChunkDataTag());
    }
    @Override
    public void fromClientTag(CompoundTag tag) {
        fromTag(null, tag);
    }
    @Override
    public CompoundTag toClientTag(CompoundTag tag) {
        return toTag(tag);
    }
    @Override
    public void markDirty() {
        super.markDirty();
        if(world instanceof ServerWorld) {
            sync();
        }
    }
    private String getPlantTypeAsString() {
        if(hasPlant()) {
            if(plant instanceof Tomato) {
                return Stake.Compatible.Tomato;
            }
        }
        return "";
    }
    private Crop createPlant(String type, int age, int uses) {
        if(type.equals(Stake.Compatible.Tomato)) {
            return new Tomato().setAge(age).setUses(uses);
        }
        return null;
    }
}
