package com.weird0cats.cropcraft.crafting;

import java.util.List;
import java.util.ArrayList;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class Recipes
{
   @SuppressWarnings("Convert2Diamond")
   public static List<ICookingPotRecipe> cookingPotRecipes = new ArrayList<ICookingPotRecipe>();

   public static void add(ICookingPotRecipe recipe)
   {
      cookingPotRecipes.add(recipe);
   }

   public static void init()
   {
      addCookingPotRecipes();
      System.out.print("Recipes added successfully!\n");
   }

   public static void addCookingPotRecipes()
   {
      //test recipe, cooking beef
      cookingPotRecipes.add(new CookingPotRecipe(new ItemStack(Items.COOKED_BEEF), new ItemStack(Items.BEEF)));
   }
}