package io.github.weird0cats.dynamicchef.compat.jei;

import java.util.ArrayList;
import java.util.List;

import io.github.weird0cats.dynamicchef.crafting.IBrickOvenRecipe;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.ingredients.VanillaTypes;
import mezz.jei.api.recipe.BlankRecipeWrapper;
import net.minecraft.item.ItemStack;

public class BakingRecipeWrapper extends BlankRecipeWrapper
{
   public IBrickOvenRecipe recipe;
   
   BakingRecipeWrapper(IBrickOvenRecipe recipe)
   {
      this.recipe = recipe;
   }

   @Override
   public void getIngredients(IIngredients ingredients)
   {
      List<List<ItemStack>> inputs = new ArrayList<>();

      inputs.addAll(recipe.getInputs());
      ingredients.setInputLists(VanillaTypes.ITEM, inputs);
      ingredients.setOutput(VanillaTypes.ITEM, recipe.getResult());
   }
}
