package com.weird0cats.cropcraft;

import com.weird0cats.cropcraft.blocks.*;
import com.weird0cats.cropcraft.blocks.cookingpot.*;

import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ModBlocks
{
   @GameRegistry.ObjectHolder("cropcraft:cookingpot")
   public static CookingPot cookingPot;

   @SideOnly(Side.CLIENT)
   public static void initModels()
   {
      cookingPot.initModel();
   }
}