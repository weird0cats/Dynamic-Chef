package com.weird0cats.cropcraft.items;

import com.weird0cats.cropcraft.CropCraft;

import net.minecraft.item.ItemFood;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;

import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class VitusBerry extends ItemFood
{
   public final float saturation;

   public VitusBerry(int healAmount, float saturation)
   {
      super(healAmount, saturation, false);
      setRegistryName("vitusberry");
      setTranslationKey(CropCraft.MODID + ".vitusberry");
      setCreativeTab(CropCraft.modTab);
      this.saturation=saturation;
   }

   @SideOnly(Side.CLIENT)
   public void initModel()
   {
      ModelLoader.setCustomModelResourceLocation(this, 0, new ModelResourceLocation(getRegistryName(), "inventory"));
   }
}