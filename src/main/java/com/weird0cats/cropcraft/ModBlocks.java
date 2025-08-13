package com.weird0cats.cropcraft;

import com.weird0cats.cropcraft.blocks.BlockCookingPot;
import com.weird0cats.cropcraft.blocks.BlockBrickOven;

import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ModBlocks
{
   @GameRegistry.ObjectHolder("cropcraft:cookingpot")
   public static BlockCookingPot cookingPot;

   @GameRegistry.ObjectHolder("cropcraft:brickoven")
   public static BlockBrickOven brickOven;

   @SideOnly(Side.CLIENT)
   public static void initModels()
   {
      cookingPot.initModel();
      brickOven.initModel();
   }
}