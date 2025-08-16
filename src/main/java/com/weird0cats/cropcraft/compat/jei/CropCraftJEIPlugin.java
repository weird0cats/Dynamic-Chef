package com.weird0cats.cropcraft.compat.jei;

import com.weird0cats.cropcraft.ModBlocks;
import com.weird0cats.cropcraft.crafting.IBrickOvenRecipe;
import com.weird0cats.cropcraft.crafting.ICookingPotRecipe;

import mezz.jei.api.IGuiHelper;
import mezz.jei.api.IJeiHelpers;
import mezz.jei.api.IJeiRuntime;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.IModRegistry;
import mezz.jei.api.IRecipeRegistry;
import mezz.jei.api.JEIPlugin;
import net.minecraft.item.ItemStack;

@JEIPlugin
public class CropCraftJEIPlugin implements IModPlugin
{
   public static IRecipeRegistry recipeRegistry;

   @Override
   public void register(IModRegistry reg)
   {
      IJeiHelpers helper = reg.getJeiHelpers();
      IGuiHelper guiHelper = helper.getGuiHelper();
      
      reg.addRecipeCategories(new BoilingRecipeCategory(guiHelper));
      reg.addRecipes(BoilingRecipeMaker.getBoilingRecipes(helper), "cropcraft.boiling");
      reg.handleRecipes(ICookingPotRecipe.class, new BoilingRecipeWrapperFactory(), "cropcraft.boiling");
      
      reg.addRecipeCategories(new BakingRecipeCategory(guiHelper));
      reg.addRecipes(BakingRecipeMaker.getBakingRecipes(helper), "cropcraft.baking");
      reg.handleRecipes(IBrickOvenRecipe.class, new BakingRecipeWrapperFactory(), "cropcraft.baking");
      
      reg.addRecipeCategoryCraftingItem(new ItemStack(ModBlocks.cookingPot), "cropcraft.boiling");
      reg.addRecipeCategoryCraftingItem(new ItemStack(ModBlocks.brickOven), "cropcraft.baking");
   }

   @Override
   public void onRuntimeAvailable(IJeiRuntime iJeiRuntime)
   {
      recipeRegistry = iJeiRuntime.getRecipeRegistry();
   }
}
