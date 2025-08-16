package com.weird0cats.dynamicchef.compat.jei;

import java.util.ArrayList;
import java.util.List;

import com.weird0cats.dynamicchef.crafting.ICookingPotRecipe;
import com.weird0cats.dynamicchef.crafting.Recipes;

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
