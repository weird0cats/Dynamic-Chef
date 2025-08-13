package com.weird0cats.cropcraft;

import com.weird0cats.cropcraft.items.Dough;
import com.weird0cats.cropcraft.items.LupaBerry;
import com.weird0cats.cropcraft.items.VitusBerry;

import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ModItems
{
   @GameRegistry.ObjectHolder("cropcraft:lupaberry")
   public static LupaBerry lupaBerry;

   @GameRegistry.ObjectHolder("cropcraft:vitusberry")
   public static VitusBerry vitusBerry;

   @GameRegistry.ObjectHolder("cropcraft:dough")
   public static Dough dough;

   @SideOnly(Side.CLIENT)
   public static void initModels()
   {
      lupaBerry.initModel();
      vitusBerry.initModel();
      dough.initModel();
   }
}