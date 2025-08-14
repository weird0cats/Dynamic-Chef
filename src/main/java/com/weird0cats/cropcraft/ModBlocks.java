package com.weird0cats.cropcraft;

import com.weird0cats.cropcraft.blocks.BlockBrickOven;
import com.weird0cats.cropcraft.blocks.BlockCookingPot;

import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ModBlocks
{
   @GameRegistry.ObjectHolder("cropcraft:cookingpot")
   public static BlockCookingPot cookingPot;

   @GameRegistry.ObjectHolder("cropcraft:brickoven")
   public static BlockBrickOven brickOven;

   @GameRegistry.ObjectHolder("cropcraft:brickovenlit")
   public static BlockBrickOven brickOvenLit;

   @SideOnly(Side.CLIENT)
   public static void initModels()
   {
      cookingPot.initModel();
      brickOven.initModel();
      brickOvenLit.initModel();
   }
}