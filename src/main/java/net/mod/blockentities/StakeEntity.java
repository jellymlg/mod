package net.mod.blockentities;

import net.fabricmc.fabric.api.block.entity.BlockEntityClientSerializable;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
//import net.minecraft.block.CropBlock;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.item.Item;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.world.ServerWorld;
import net.mod.Stuff;
import net.mod.utility.StakeCompatible;
import net.mod.utility.StakeCompatibleType;

public class StakeEntity extends BlockEntity implements BlockEntityClientSerializable {
    private static final String PLANT_TYPE = "plant_type";
    private static final String PLANT_AGE = "plant_age";
    private static final String PLANT_USES = "plant_uses";
    private StakeCompatible plant;
    private StakeCompatibleType type;
    private int age = 0;
    private int uses = 4;
    public StakeEntity() {
        super(Stuff.BlockEntities.STAKE_ENTITY.asBlockEntity());
    }
    public void setPlant(StakeCompatibleType type) {
        this.plant = type.asBlock();
        this.type = type;
        this.age = 0;
        this.uses = 4;
        markDirty();
    }
    public void setAge(int age) {
        if(hasPlant() && age >= 0 && age <= getMaxAge()) {
            this.age = age;
            markDirty();
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
        return hasPlant() ? age == plant.getMaxAge() : true;
    }
    public StakeCompatibleType getPlantType() {
        return type;
    }
    public boolean hasPlant() {
        return plant != null;
    }
    public BlockState getState() {
        //return hasPlant() ? ((CropBlock) Blocks.WHEAT).withAge(age) : Blocks.AIR.getDefaultState();
        return hasPlant() ? plant.withAge(age) : Blocks.AIR.getDefaultState();
    }
    @Override
    public void fromTag(BlockState state, CompoundTag tag) {
        super.fromTag(state, tag);
        if(!tag.getString(PLANT_TYPE).isEmpty()) {
            plant = StakeCompatibleType.getFromString(tag.getString(PLANT_TYPE)).asBlock();
            age = tag.getInt(PLANT_AGE);
            uses = tag.getInt(PLANT_USES);
            type = StakeCompatibleType.getFromString(tag.getString(PLANT_TYPE));
        }
    }
    @Override
    public CompoundTag toTag(CompoundTag tag) {
        if(hasPlant()) {
            tag.putString(PLANT_TYPE, type.asString());
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
    public Item getLoot() {
        return plant.getLootItem();
    }
}
