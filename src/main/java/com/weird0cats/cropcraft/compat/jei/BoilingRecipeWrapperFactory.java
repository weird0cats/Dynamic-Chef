package com.weird0cats.cropcraft.compat.jei;

import com.weird0cats.cropcraft.crafting.ICookingPotRecipe;

import mezz.jei.api.recipe.IRecipeWrapper;
import mezz.jei.api.recipe.IRecipeWrapperFactory;

public class BoilingRecipeWrapperFactory implements IRecipeWrapperFactory<ICookingPotRecipe>
{
   @Override
   public IRecipeWrapper getRecipeWrapper(ICookingPotRecipe recipe)
   {
      return new BoilingRecipeWrapper(recipe);
   }
}
