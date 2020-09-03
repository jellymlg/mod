package net.mod.utility;

import net.minecraft.block.entity.BlockEntity;
import net.mod.Main;

public class StakeEntity extends BlockEntity {
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
}
