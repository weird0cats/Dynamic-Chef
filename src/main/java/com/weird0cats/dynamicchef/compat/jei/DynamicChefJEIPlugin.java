package com.weird0cats.dynamicchef.compat.jei;

import com.weird0cats.dynamicchef.ModBlocks;
import com.weird0cats.dynamicchef.crafting.IBrickOvenRecipe;
import com.weird0cats.dynamicchef.crafting.ICookingPotRecipe;

import mezz.jei.api.IGuiHelper;
import mezz.jei.api.IJeiHelpers;
import mezz.jei.api.IJeiRuntime;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.IModRegistry;
import mezz.jei.api.IRecipeRegistry;
import mezz.jei.api.JEIPlugin;
import net.minecraft.item.ItemStack;

@JEIPlugin
public class DynamicChefJEIPlugin implements IModPlugin
{
   public static IRecipeRegistry recipeRegistry;

   @Override
   public void register(IModRegistry reg)
   {
      IJeiHelpers helper = reg.getJeiHelpers();
      IGuiHelper guiHelper = helper.getGuiHelper();
      
      reg.addRecipeCategories(new BoilingRecipeCategory(guiHelper));
      reg.addRecipes(BoilingRecipeMaker.getBoilingRecipes(helper), "dynamicchef.boiling");
      reg.handleRecipes(ICookingPotRecipe.class, new BoilingRecipeWrapperFactory(), "dynamicchef.boiling");
      
      reg.addRecipeCategories(new BakingRecipeCategory(guiHelper));
      reg.addRecipes(BakingRecipeMaker.getBakingRecipes(helper), "dynamicchef.baking");
      reg.handleRecipes(IBrickOvenRecipe.class, new BakingRecipeWrapperFactory(), "dynamicchef.baking");
      
      reg.addRecipeCategoryCraftingItem(new ItemStack(ModBlocks.cookingPot), "dynamicchef.boiling");
      reg.addRecipeCategoryCraftingItem(new ItemStack(ModBlocks.brickOven), "dynamicchef.baking");
   }

   @Override
   public void onRuntimeAvailable(IJeiRuntime iJeiRuntime)
   {
      recipeRegistry = iJeiRuntime.getRecipeRegistry();
   }
}
