package com.weird0cats.cropcraft;

import com.weird0cats.cropcraft.proxy.CommonProxy;

import net.minecraft.init.Blocks;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import org.apache.logging.log4j.Logger;

@Mod(modid = CropCraft.MODID, name = CropCraft.NAME, version = CropCraft.VERSION, useMetadata = true)
public class CropCraft
{
    public static final String MODID = "cropcraft";
    public static final String NAME = "CropCraft";
    public static final String VERSION = "0.0.3";

    @SidedProxy(clientSide = "weird0cats.cropcraft.proxy.ClientProxy",serverSide = "weird0cats.cropcraft.proxy.ServerProxy")
    public static CommonProxy proxy;
    
    @Mod.Instance
    public static CropCraft instance;

    public static Logger logger;
    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        logger = event.getModLog();
        proxy.preInit(event);
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent e)
    {
        proxy.init(e);
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent e)
    {
        proxy.postInit(e);
    }
}
