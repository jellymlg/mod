package net.mod.utility;

import net.fabricmc.fabric.api.block.entity.BlockEntityClientSerializable;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.world.ServerWorld;
import net.mod.Main;

public class StakeEntity extends BlockEntity implements BlockEntityClientSerializable {
    private static final String PLANT_TYPE = "plant_type";
    private static final String PLANT_AGE = "plant_age";
    private static final String PLANT_USES = "plant_uses";
    private StakeCompatible plant;
    private StakeCompatibleType type;
    private int age = 0;
    private int uses = 4;
    public StakeEntity() {
        super(Main.STAKE_ENTITY);
    }
    public void setPlant(StakeCompatible plant, StakeCompatibleType type) {
        this.plant = plant;
        this.type = type;
        markDirty();
    }
    public void setAge(int age) {
        if(hasPlant() && age >= 0 && age < getMaxAge()) {
            this.age = age;
        }
    }
    public int getMaxAge() {
        return hasPlant() ? 7 : 0;
    }
    public int getAge() {
        return age;
    }
    public boolean use() {
        return --uses < 0;
    }
    public int getUses() {
        return uses;
    }
    public void setUses(int uses) {
        this.uses = uses;
    }
    public boolean isMature() {
        return age == plant.getMaxAge();
    }
    public StakeCompatibleType getPlantType() {
        return type;
    }
    public boolean hasPlant() {
        return plant != null;
    }
    public BlockState getState() {
        return hasPlant() ? plant.withAge(age) : Blocks.AIR.getDefaultState();
    }
    @Override
    public void fromTag(BlockState state, CompoundTag tag) {
        super.fromTag(state, tag);
        if(!hasPlant() && !tag.getString(PLANT_TYPE).isEmpty()) {
            plant = getPlant(tag.getString(PLANT_TYPE));
            age = tag.getInt(PLANT_AGE);
            uses = tag.getInt(PLANT_USES);
            type = StakeCompatibleType.getFromString(tag.getString(PLANT_TYPE));
        }
    }
    @Override
    public CompoundTag toTag(CompoundTag tag) {
        if(hasPlant()) {
            tag.putString(PLANT_TYPE, StakeCompatibleType.asString(type));
            tag.putInt(PLANT_AGE, age);
            tag.putInt(PLANT_USES, uses);
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
    private StakeCompatible getPlant(String type) {
        switch(StakeCompatibleType.getFromString(type)) {
            case TOMATO:
                return Main.TOMATO;
            case PEPPER:
                return Main.PEPPER;
            default:
                return (StakeCompatible) Blocks.AIR;
        }
    }
}
