package io.github.weird0cats.dynamicchef.crafting;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import net.minecraft.item.ItemStack;


public class CookingPotRecipe implements ICookingPotRecipe
{
   ItemStack output;

   List<ItemStack> inputs;

   @SuppressWarnings("Convert2Diamond")
   public CookingPotRecipe(ItemStack output, ItemStack... inputs)
   {
      this.output = output;
      this.inputs = Arrays.asList(inputs);
      
   }
   
   //checks if items match a recipe?
   @Override
   public boolean matches(ItemStack[] inputs)
   {
      if (inputs.length <= 0)
      {
         return false;
      }

      if (this.inputs == null || this.inputs.size() <= 0)
      {
         return false;
      }

      @SuppressWarnings("Convert2Diamond")
      List<ItemStack> tempInputs = new ArrayList<ItemStack>(this.inputs);
       for (ItemStack input : inputs) {
           ItemStack stack = input.copy();
           if (stack.isEmpty())
           {
               continue;
           }
           boolean stackNotInput = true;
           for (ItemStack temp : tempInputs)
           {
               if (temp.getItem().equals(stack.getItem()))
               {
                  if (temp.getMetadata() == stack.getMetadata())
                  {
                     if (temp.hasTagCompound() && stack.hasTagCompound())
                     {
                        if (temp.getTagCompound().equals(stack.getTagCompound()))
                        {
                        tempInputs.remove(stack);
                        stackNotInput = false;
                        break;
                     }
                  } else if (!temp.hasTagCompound())
                  {
                     tempInputs.remove(temp);
                     stackNotInput = false;
                     break;
                  }
               }
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
      List<List<ItemStack>> inputList = new ArrayList<>();
      for (ItemStack stack : this.inputs)
      {
         inputList.add(Arrays.asList(stack.copy()));
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