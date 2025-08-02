package com.weird0cats.cropcraft.crafting;

import java.util.List;
import java.util.ArrayList;

import com.weird0cats.cropcraft.crafting.ICookingPotRecipe;

import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class Recipes
{
   public static List<ICookingPotRecipe> cookingPotRecipes = new ArrayList<ICookingPotRecipe>();

   public static void add(ICookingPotRecipe recipe)
   {
      cookingPotRecipes.add(recipe);
   }

   public static void init()
   {
      addCookingPotRecipes();
   }

   public static void addCookingPotRecipes()
   {
      cookingPotRecipes.add(new CookingPotRecipe(new ItemStack(Items.COOKED_BEEF), new ItemStack(Items.BEEF),null,null,null,null,null));
   }
}