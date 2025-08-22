package io.github.weird0cats.dynamicchef.compat.jei;

import io.github.weird0cats.dynamicchef.common.crafting.IBrickOvenRecipe;
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
