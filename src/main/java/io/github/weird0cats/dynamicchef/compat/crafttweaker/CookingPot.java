package io.github.weird0cats.dynamicchef.compat.crafttweaker;

import crafttweaker.CraftTweakerAPI;
import crafttweaker.IAction;
import crafttweaker.api.item.IIngredient;
import crafttweaker.api.item.IItemStack;
import crafttweaker.api.minecraft.CraftTweakerMC;
import io.github.weird0cats.dynamicchef.crafting.ICookingPotRecipe;
import io.github.weird0cats.dynamicchef.crafting.Recipes;
import stanhebben.zenscript.annotations.NotNull;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;

@ZenClass("mods.dynamicchef.CookingPot")
public class CookingPot
{
   @ZenMethod
   public static void addRecipe(@NotNull IItemStack output, IIngredient[] inputs)
   {
      if (inputs.length > 6)
      {
         throw new IllegalArgumentException("Cooking Pot recipe has at most 6 inputs");
      }
      ICookingPotRecipe recipe = new CrTCookingPotRecipe(CraftTweakerMC.getItemStack(output), inputs);
      CraftTweakerAPI.apply(new Add(recipe));
   }

   @ZenMethod
   public static void addRecipe(@NotNull IItemStack output, @NotNull IIngredient[] inputs, int time)
   {
      if (inputs.length > 6)
      {
         throw new IllegalArgumentException("Cooking Pot recipe has at most 6 inputs");
      }
      if (time < 0)
      {
         throw new IllegalArgumentException("Cook time must be positive");
      }
      ICookingPotRecipe recipe = new CrTCookingPotRecipe(CraftTweakerMC.getItemStack(output), inputs, time);
      CraftTweakerAPI.apply(new Add(recipe));
   }


   private static class Add implements IAction
   {
      private final ICookingPotRecipe recipe;

      public Add(ICookingPotRecipe recipe)
      {
         this.recipe = recipe;
      }

      @Override
      public void apply()
      {
         Recipes.cookingPotRecipes.add(recipe);
      }

      @Override
      public String describe()
      {
         return "Adding Boiling Recipe for Item" + recipe.getResult().getDisplayName();
      }
   }
}