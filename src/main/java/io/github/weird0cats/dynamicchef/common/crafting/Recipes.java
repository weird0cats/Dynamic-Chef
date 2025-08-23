package io.github.weird0cats.dynamicchef.common.crafting;

import java.util.List;

import io.github.weird0cats.dynamicchef.common.items.ModItems;

import java.util.ArrayList;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

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
      cookingPotRecipes.add(new CookingPotRecipe(new ItemStack(ModItems.BEEF_STEW), new ItemStack(Items.BEEF), new ItemStack(Items.CARROT), new ItemStack(Items.POTATO)));
   }

   public static void addBrickOvenRecipes()
   {
      brickOvenRecipes.add(new BrickOvenRecipe(new ItemStack(Items.BREAD), new ItemStack(ModItems.DOUGH)));
      if (OreDictionary.doesOreNameExist("oreUranium"))
      {
         brickOvenRecipes.add(new BrickOvenRecipe(new ItemStack(ModItems.RADCOOKIE, 8), new ItemStack(ModItems.DOUGH), "oreUranium"));
      }
   }
}