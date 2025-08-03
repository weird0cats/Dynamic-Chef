package com.weird0cats.cropcraft;

import com.weird0cats.cropcraft.proxy.CommonProxy;
import com.weird0cats.cropcraft.CropCraftTab;
import com.weird0cats.cropcraft.crafting.Recipes;

import net.minecraft.init.Blocks;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.crafting.IRecipe;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.event.RegistryEvent;

import org.apache.logging.log4j.Logger;

@Mod(modid = CropCraft.MODID, name = CropCraft.NAME, version = CropCraft.VERSION, useMetadata = true)
public class CropCraft
{
   public static final String MODID = "cropcraft";
   public static final String NAME = "CropCraft";
   public static final String VERSION = "0.0.3";

   @SidedProxy(clientSide = "com.weird0cats.cropcraft.proxy.ClientProxy",serverSide = "com.weird0cats.cropcraft.proxy.ServerProxy")
   public static CommonProxy proxy;
    
   @Mod.Instance
   public static CropCraft instance;

   public static Logger logger;
   public static CreativeTabs modTab = new CropCraftTab();
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
   
   @SubscribeEvent
   public void initRecipes(RegistryEvent.Register<IRecipe> event)
   {
      Recipes.init();
   }
}

