package com.weird0cats.cropcraft.compat.jei;

import com.weird0cats.cropcraft.crafting.IBrickOvenRecipe;

import mezz.jei.api.recipe.IRecipeWrapper;
import mezz.jei.api.recipe.IRecipeWrapperFactory;

public class BakingRecipeWrapperFactory implements IRecipeWrapperFactory<IBrickOvenRecipe>
{
   @Override
   public IRecipeWrapper getRecipeWrapper(IBrickOvenRecipe recipe)
   {
      return new BakingRecipeWrapper(recipe);
   }
}
