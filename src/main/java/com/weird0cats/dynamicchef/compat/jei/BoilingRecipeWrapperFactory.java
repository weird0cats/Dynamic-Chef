package com.weird0cats.dynamicchef.compat.jei;

import com.weird0cats.dynamicchef.crafting.ICookingPotRecipe;

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
