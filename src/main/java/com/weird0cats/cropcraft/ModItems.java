package com.weird0cats.cropcraft;

import com.weird0cats.cropcraft.items.ItemFoodBase;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ModItems
{
   public static ItemFoodBase TOMATO_BERRY;
   public static ItemFoodBase GRAPE_BERRY;
   public static ItemFoodBase DOUGH;

   public static void init()
   {
      TOMATO_BERRY = new ItemFoodBase("lupaberry", 2, 0.4f, false);
      GRAPE_BERRY = new ItemFoodBase("vitusberry", 2, 0.4f, false);
      DOUGH = new ItemFoodBase("dough", 1, 0.2f, false);
   }
   
   @SideOnly(Side.CLIENT)
   public static void initModels()
   {
      TOMATO_BERRY.initModel();
      GRAPE_BERRY.initModel();
      DOUGH.initModel();
   }
}