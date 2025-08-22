package io.github.weird0cats.dynamicchef.compat.jei;

import io.github.weird0cats.dynamicchef.common.crafting.ICookingPotRecipe;
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
