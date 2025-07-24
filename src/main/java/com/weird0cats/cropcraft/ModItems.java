package com.weird0cats.cropcraft;

import com.weird0cats.cropcraft.items.*;

import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ModItems
{
    @GameRegistry.ObjectHolder("cropcraft:lupaberry")
    public static LupaBerry lupaBerry;
    
    @GameRegistry.ObjectHolder("cropcraft:vitusberry")
    public static VitusBerry vitusBerry;
    
    @SideOnly(Side.CLIENT)
    public static void initModels()
    {
        lupaBerry.initModel();
        vitusBerry.initModel();
    }
}