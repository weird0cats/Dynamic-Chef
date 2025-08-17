package io.github.weird0cats.dynamicchef.compat.jei;

import java.util.ArrayList;
import java.util.List;

import io.github.weird0cats.dynamicchef.crafting.IBrickOvenRecipe;
import io.github.weird0cats.dynamicchef.crafting.Recipes;
import mezz.jei.api.IJeiHelpers;

public class BakingRecipeMaker
{
   public static List<IBrickOvenRecipe> getBakingRecipes(IJeiHelpers helpers)
   {
      @SuppressWarnings("Convert2Diamond")
      List<IBrickOvenRecipe> recipes = new ArrayList<IBrickOvenRecipe>();
      
      for (IBrickOvenRecipe recipe : Recipes.brickOvenRecipes)
      {
         recipes.add(recipe);
      }

      return recipes;
   }
}
