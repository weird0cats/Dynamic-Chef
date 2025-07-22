package com.weird0cats.cropcraft;

import com.weird0cats.cropcraft.CropCraft;
import net.minecraft.creativetab.CreativeTabs;

import net.minecraft.item.ItemStack;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class CropCraftTab extends CreativeTabs
{
    public CropCraftTab()
    {
        super(CropCraft.MODID);
    }

    @SideOnly(Side.CLIENT)
    @Override
    public ItemStack createIcon()
    {
        return new ItemStack(ModItems.lupaBerry);
    }

}