package io.github.weird0cats.dynamicchef;

import io.github.weird0cats.dynamicchef.common.blocks.ModBlocks;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class DynamicChefTab extends CreativeTabs
{
   public DynamicChefTab()
   {
      super(DynamicChef.MODID);
   }

   @SideOnly(Side.CLIENT)
   @Override
   public ItemStack createIcon()
   {
      return new ItemStack(ModBlocks.cookingPot);
   }
}