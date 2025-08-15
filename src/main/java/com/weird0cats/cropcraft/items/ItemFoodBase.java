package com.weird0cats.cropcraft.items;

import com.weird0cats.cropcraft.CropCraft;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFood;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemFoodBase extends ItemFood
{
   public ItemFoodBase(String name, int healAmount, float saturation, boolean wolfFood)
   {
      super(healAmount, saturation, wolfFood);
      setRegistryName(name);
      setTranslationKey(CropCraft.MODID + "." + name);
      setCreativeTab(CropCraft.modTab);
		GameRegistry.findRegistry(Item.class).register(this);
   }

   @SideOnly(Side.CLIENT)
   public void initModel()
   {
      ModelLoader.setCustomModelResourceLocation(this, 0, new ModelResourceLocation(getRegistryName(), "inventory"));
   }
}