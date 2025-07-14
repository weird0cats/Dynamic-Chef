package com.weird0cats.cropcraft.items;

import com.weird0cats.cropcraft.CropCraft;

import net.minecraft.item.Item;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;

import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class LupaBerry extends Item
{
    public LupaBerry()
    {
        setRegistryName("lupaberry");
        setTranslationKey(CropCraft.MODID + ".lupaberry");
    }

    @SideOnly(Side.CLIENT)
    public void initModel()
    {
        ModelLoader.setCustomModelResourceLocation(this, 0, new ModelResourceLocation(getRegistryName(), "inventory"));
    }
}