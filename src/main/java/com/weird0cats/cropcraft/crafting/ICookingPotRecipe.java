package com.weird0cats.cropcraft.crafting;

import java.util.List;

import net.minecraft.item.ItemStack;

public interface ICookingPotRecipe
{
   public boolean matches(ItemStack[] inputs);
   public List<List<ItemStack>> getInputs();
   public int getTime();
   public int[] getInputConsumption(ItemStack[] inputs);
   public ItemStack getResult();
}