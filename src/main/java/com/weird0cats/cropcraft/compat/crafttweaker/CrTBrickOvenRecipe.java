package com.weird0cats.cropcraft.compat.crafttweaker;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.Nonnull;

import com.weird0cats.cropcraft.crafting.IBrickOvenRecipe;

import crafttweaker.api.item.IIngredient;
import crafttweaker.api.item.IItemStack;
import crafttweaker.api.minecraft.CraftTweakerMC;
import net.minecraft.item.ItemStack;

public class CrTBrickOvenRecipe implements IBrickOvenRecipe
{
   private int time;
   private ItemStack output;
   private List<IIngredient> inputs;

   public CrTBrickOvenRecipe(@Nonnull ItemStack output, IIngredient[] inputs)
   {
      this(output, inputs, 400);
   }

   public CrTBrickOvenRecipe(@Nonnull ItemStack output, IIngredient[] inputs, int time)
   {
      this.output = output;
      this.inputs = new ArrayList<>();
      this.inputs.addAll(Arrays.asList(inputs));
      this.time = Math.max(0, time);
   }
   
   @Override
   public boolean matches(ItemStack[] inputs)
   {
      @SuppressWarnings("Convert2Diamond")
      List<IIngredient> tempInputs = new ArrayList<IIngredient>(this.inputs);
      for (ItemStack stack : inputs)
      {
         if (stack == null || stack.isEmpty())
         {
            continue;
         }
         boolean stackNotInput = true;
         IItemStack input = CraftTweakerMC.getIItemStack(stack);
         for (IIngredient ing : tempInputs)
         {
            if (ing==null)
            {
               tempInputs.remove(ing);
               continue;
            }
            if (ing.matchesExact(input))
            {
               stackNotInput = false;
               tempInputs.remove(ing);
               break;
            }
         }
         if (stackNotInput)
         {
            return false;
         }
      }
      return (tempInputs.isEmpty());
   }

   @Override
   public List<List<ItemStack>> getInputs()
   {
      List<List<ItemStack>> inputs = new ArrayList<>();
      for (IIngredient input : this.inputs)
      {
         if (input == null)
         {
            inputs.add(new ArrayList<>());
         }
         else
         {
            inputs.add(input.getItems().stream().map(CraftTweakerMC::getItemStack).collect(Collectors.toList()));
         }
      }
      return inputs;
   }

   @Override
   public int getTime()
   {
      return this.time;
   }

	@Override
	public int[] getInputConsumption(ItemStack[] inputs)
   {
		int[] consume = new int[inputs.length];
      @SuppressWarnings("Convert2Diamond")
		List<IIngredient> tempInputs = new ArrayList<IIngredient>(this.inputs);
		IItemStack current;
		for (int i=0; i<inputs.length; i++)
      {
			consume[i] = 0;
			if (inputs[i] != null)
         {
				current = CraftTweakerMC.getIItemStack(inputs[i]);
				foundAmount:
				for (IIngredient in : tempInputs)
            {
					for (IItemStack stack : in.getItems())
               {
						if (stack.matchesExact(current))
                  {
							consume[i] = stack.getAmount();
							break foundAmount;
						}
					}
				}
			}
		}
		return consume;
	}

   @Override
   public ItemStack getResult()
   {
      return this.output.copy();
   }
}