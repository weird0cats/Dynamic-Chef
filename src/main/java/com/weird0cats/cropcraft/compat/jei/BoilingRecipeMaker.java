package com.weird0cats.cropcraft.compat.jei;

import java.util.ArrayList;
import java.util.List;

import com.weird0cats.cropcraft.crafting.ICookingPotRecipe;
import com.weird0cats.cropcraft.crafting.Recipes;

import mezz.jei.api.IJeiHelpers;

public class BoilingRecipeMaker
{
   public static List<ICookingPotRecipe> getBoilingRecipes(IJeiHelpers helpers)
   {
      List<ICookingPotRecipe> recipes = new ArrayList<ICookingPotRecipe>();
      
      for (ICookingPotRecipe recipe : Recipes.cookingPotRecipes)
      {
         recipes.add(recipe);
      }

      return recipes;
   }
}
