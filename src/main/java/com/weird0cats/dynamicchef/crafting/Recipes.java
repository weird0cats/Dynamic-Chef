package com.weird0cats.dynamicchef.crafting;

import java.util.List;

import com.weird0cats.dynamicchef.ModItems;

import java.util.ArrayList;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class Recipes
{
   @SuppressWarnings("Convert2Diamond")
   public static List<ICookingPotRecipe> cookingPotRecipes = new ArrayList<ICookingPotRecipe>();
   
   @SuppressWarnings("Convert2Diamond")
   public static List<IBrickOvenRecipe> brickOvenRecipes = new ArrayList<IBrickOvenRecipe>();
   
   public static void add(ICookingPotRecipe recipe)
   {
      cookingPotRecipes.add(recipe);
   }

   public static void add(IBrickOvenRecipe recipe)
   {
      brickOvenRecipes.add(recipe);
   }
   public static void init()
   {
      addCookingPotRecipes();
      addBrickOvenRecipes();
      System.out.print("Recipes added successfully!\n");
   }

   public static void addCookingPotRecipes()
   {
      //test recipe, cooking beef
      cookingPotRecipes.add(new CookingPotRecipe(new ItemStack(Items.COOKED_BEEF), new ItemStack(Items.BEEF)));
   }

   public static void addBrickOvenRecipes()
   {
      brickOvenRecipes.add(new BrickOvenRecipe(new ItemStack(Items.BREAD), new ItemStack(ModItems.DOUGH)));
   }
}