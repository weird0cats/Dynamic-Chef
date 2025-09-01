package io.github.weird0cats.dynamicchef.common.blocks;

import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ModBlocks
{

   @GameRegistry.ObjectHolder("dynamicchef:cooking_pot")
   public static BlockCookingPot cookingPot;

   @GameRegistry.ObjectHolder("dynamicchef:brick_oven")
   public static BlockBrickOven brickOven;

   @GameRegistry.ObjectHolder("dynamicchef:brick_oven_lit")
   public static BlockBrickOven brickOvenLit;

   @GameRegistry.ObjectHolder("dynamicchef:brick_stove")
   public static BlockStove stove;

   @GameRegistry.ObjectHolder("dynamicchef:brick_stove_lit")
   public static BlockStove stoveLit;
   
   @SideOnly(Side.CLIENT)
   public static void initModels()
   {
      cookingPot.initModel();
      brickOven.initModel();
      brickOvenLit.initModel();
      stove.initModel();
      stoveLit.initModel();
   }
}