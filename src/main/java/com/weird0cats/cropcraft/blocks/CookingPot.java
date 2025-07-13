package com.weird0cats.cropcraft.blocks;

import com.weird0cats.cropcraft.CropCraft;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class CookingPot extends Block
{
    public CookingPot()
    {
        super(Material.ROCK);
        //setUnlocalizedName(CropCraft.MODID + ".cookingpot");
        setRegistryName("cookingpot");
    }
}