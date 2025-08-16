package com.weird0cats.cropcraft.compat.jei;

import java.util.ArrayList;
import java.util.List;

import com.weird0cats.cropcraft.crafting.IBrickOvenRecipe;
import com.weird0cats.cropcraft.crafting.Recipes;

import mezz.jei.api.IJeiHelpers;

public class BakingRecipeMaker
{
   public static List<IBrickOvenRecipe> getBakingRecipes(IJeiHelpers helpers)
   {
      List<IBrickOvenRecipe> recipes = new ArrayList<IBrickOvenRecipe>();
      
      for (IBrickOvenRecipe recipe : Recipes.brickOvenRecipes)
      {
         recipes.add(recipe);
      }

      return recipes;
   }
}
