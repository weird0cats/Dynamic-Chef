package com.weird0cats.cropcraft.compat.crafttweaker;

import com.weird0cats.cropcraft.crafting.IBrickOvenRecipe;
import com.weird0cats.cropcraft.crafting.Recipes;

import crafttweaker.CraftTweakerAPI;
import crafttweaker.IAction;
import crafttweaker.api.item.IIngredient;
import crafttweaker.api.item.IItemStack;
import crafttweaker.api.minecraft.CraftTweakerMC;
import stanhebben.zenscript.annotations.NotNull;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;

@ZenClass("mods.cropcraft.BrickOven")
public class BrickOven
{
   @ZenMethod
   public static void addRecipe(@NotNull IItemStack output, IIngredient[] inputs)
   {
      if (inputs.length > 6)
		{
			throw new IllegalArgumentException("Brick Oven recipe has at most 6 inputs");
		}
		IBrickOvenRecipe recipe = new CrTBrickOvenRecipe(CraftTweakerMC.getItemStack(output), inputs);
		CraftTweakerAPI.apply(new Add(recipe));
   }

	@ZenMethod
   public static void addRecipe(@NotNull IItemStack output, @NotNull IIngredient[] inputs, int time)
   {
      if (inputs.length > 6)
		{
			throw new IllegalArgumentException("Brick Oven recipe has at most 6 inputs");
		}
		if (time < 0)
		{
			throw new IllegalArgumentException("Cook time must be positive");
		}
		IBrickOvenRecipe recipe = new CrTBrickOvenRecipe(CraftTweakerMC.getItemStack(output), inputs, time);
		CraftTweakerAPI.apply(new Add(recipe));
   }


	private static class Add implements IAction
	{
		private final IBrickOvenRecipe recipe;

		public Add(IBrickOvenRecipe recipe)
		{
			this.recipe = recipe;
		}

		@Override
		public void apply()
		{
			Recipes.brickOvenRecipes.add(recipe);
		}

		@Override
		public String describe()
		{
			return "Adding Baking Recipe for Item" + recipe.getResult().getDisplayName();
		}
	}
}