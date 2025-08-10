package com.weird0cats.cropcraft.items;

import com.weird0cats.cropcraft.CropCraft;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.ItemFood;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class Dough extends ItemFood
{
   public final float saturation;

   public Dough(int healAmount, float saturation)
   {
      super(healAmount, saturation, false);
      setRegistryName("dough");
      setTranslationKey(CropCraft.MODID + ".dough");
      setCreativeTab(CropCraft.modTab);
      this.saturation=saturation;
   }

   @SideOnly(Side.CLIENT)
   public void initModel()
   {
      ModelLoader.setCustomModelResourceLocation(this, 0, new ModelResourceLocation(getRegistryName(), "inventory"));
   }
}