package io.github.weird0cats.dynamicchef;

import io.github.weird0cats.dynamicchef.blocks.BlockBrickOven;
import io.github.weird0cats.dynamicchef.blocks.BlockCookingPot;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ModBlocks
{
   @GameRegistry.ObjectHolder("dynamicchef:cookingpot")
   public static BlockCookingPot cookingPot;

   @GameRegistry.ObjectHolder("dynamicchef:brickoven")
   public static BlockBrickOven brickOven;

   @GameRegistry.ObjectHolder("dynamicchef:brickovenlit")
   public static BlockBrickOven brickOvenLit;

   @SideOnly(Side.CLIENT)
   public static void initModels()
   {
      cookingPot.initModel();
      brickOven.initModel();
      brickOvenLit.initModel();
   }
}