package io.github.weird0cats.dynamicchef.crafting;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

public class BrickOvenRecipe implements IBrickOvenRecipe
{
   ItemStack output;
   List<Object> inputs;

   public BrickOvenRecipe(ItemStack output, Object... inputs)
   {
      this.output = output;
      this.inputs = Arrays.asList(inputs);
   }

   @Override
   public boolean matches(ItemStack[] inputStacks)
   {
      if (inputStacks.length <= 0 || this.inputs == null || this.inputs.size() <= 0)
         return false;

      List<Object> tempInputs = new ArrayList<>(this.inputs);

      for (ItemStack stack : inputStacks)
      {
         if (stack.isEmpty())
         {
            continue;
         }

         boolean matched = false;
         for (Object recipeInput : tempInputs)
         {
            if (recipeInput instanceof ItemStack)
            {
               ItemStack recipeStack = (ItemStack) recipeInput;
               if (OreDictionary.itemMatches(recipeStack, stack, false))
               {
                  tempInputs.remove(recipeInput);
                  matched = true;
                  break;
               }
            }
            else if (recipeInput instanceof String)
            {
               List<ItemStack> oreStacks = OreDictionary.getOres((String) recipeInput);
               for (ItemStack oreStack : oreStacks)
               {
                  if (OreDictionary.itemMatches(oreStack, stack, false))
                  {
                     tempInputs.remove(recipeInput);
                     matched = true;
                     break;
                  }
               }
               if (matched) break;
            }
         }
         if (!matched)
         {
            return false;
         }
      } 
      return tempInputs.isEmpty();
   }

   @Override
   public List<List<ItemStack>> getInputs()
   {
      List<List<ItemStack>> inputList = new ArrayList<>();
      for (Object input : this.inputs)
      {
         if (input instanceof ItemStack)
         {
            inputList.add(Arrays.asList(((ItemStack) input).copy()));
         }
         else if (input instanceof String)
         {
            inputList.add(OreDictionary.getOres((String) input));
         }
      }
      return inputList;
   }

   @Override
   public int[] getInputConsumption(ItemStack[] inputs)
   {
      return new int[]{1,1,1};
   }

   @Override
   public int getTime()
   {
      return 400;
   }

   @Override
   public ItemStack getResult()
   {
      return this.output.copy();
   }
}