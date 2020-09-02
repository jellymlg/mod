package net.mod.utility;

import net.minecraft.item.ToolMaterial;
import net.minecraft.recipe.Ingredient;

public class CustomToolMaterial implements ToolMaterial{
   private final int miningLevel;
   private final int itemDurability;
   private final float miningSpeed;
   private final float attackDamage;
   private final int enchantability;
   private final Ingredient repairIngredient;
   public CustomToolMaterial(int miningLevel, int itemDurability, float miningSpeed, float attackDamage, int enchantibility, Ingredient repairIngredient) {
      this.miningLevel = miningLevel;
      this.itemDurability = itemDurability;
      this.miningSpeed = miningSpeed;
      this.attackDamage = attackDamage - 1;
      this.enchantability = enchantibility;
      this.repairIngredient = repairIngredient;
   }
   public int getDurability() {
      return this.itemDurability;
   }
   public float getMiningSpeedMultiplier() {
      return this.miningSpeed;
   }
   public float getAttackDamage() {
      return this.attackDamage;
   }
   public int getMiningLevel() {
      return this.miningLevel;
   }
   public int getEnchantability() {
      return this.enchantability;
   }
   public Ingredient getRepairIngredient() {
      return repairIngredient;
   }
}