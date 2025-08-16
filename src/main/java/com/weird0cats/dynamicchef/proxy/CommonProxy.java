package com.weird0cats.dynamicchef.proxy;

import java.io.File;

import com.weird0cats.dynamicchef.Config;
import com.weird0cats.dynamicchef.DynamicChef;
import com.weird0cats.dynamicchef.ModBlocks;
import com.weird0cats.dynamicchef.ModItems;
import com.weird0cats.dynamicchef.blocks.BlockBrickOven;
import com.weird0cats.dynamicchef.blocks.BlockCookingPot;
import com.weird0cats.dynamicchef.crafting.Recipes;
import com.weird0cats.dynamicchef.tileentity.TileEntityBrickOven;
import com.weird0cats.dynamicchef.tileentity.TileEntityCookingPot;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.crafting.IRecipe;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;

@Mod.EventBusSubscriber
public class CommonProxy {
   public static Configuration config;

   public void preInit(FMLPreInitializationEvent e)
   {
      File directory = e.getModConfigurationDirectory();
      config = new Configuration(new File(directory.getPath(), "dynamicchef.cfg"));
      Config.readConfig();
   }

   public void init(FMLInitializationEvent e)
   {
      NetworkRegistry.INSTANCE.registerGuiHandler(DynamicChef.instance, new GuiProxy());
   }

   public void postInit(FMLPostInitializationEvent e)
   {
      if (config.hasChanged())
      {
         config.save();
      }
   }

   @SubscribeEvent
   public static void registerBlocks(RegistryEvent.Register<Block> event)
   {
      event.getRegistry().register(new BlockCookingPot());
      event.getRegistry().register(new BlockBrickOven("brickoven", false));
      event.getRegistry().register(new BlockBrickOven("brickovenlit", true));
      GameRegistry.registerTileEntity(TileEntityCookingPot.class, DynamicChef.MODID + ":cookingpotblock");
      GameRegistry.registerTileEntity(TileEntityBrickOven.class, DynamicChef.MODID + ":brickovenblock");

   }

   @SubscribeEvent
   public static void registerItems(RegistryEvent.Register<Item> event)
   {
      ModItems.init();
      event.getRegistry().register(new ItemBlock(ModBlocks.cookingPot).setRegistryName(ModBlocks.cookingPot.getRegistryName()));
      event.getRegistry().register(new ItemBlock(ModBlocks.brickOven).setRegistryName(ModBlocks.brickOven.getRegistryName()));
      event.getRegistry().register(new ItemBlock(ModBlocks.brickOvenLit.setLightLevel(0.875f)).setRegistryName(ModBlocks.brickOvenLit.getRegistryName()));
   }

   @SubscribeEvent
   public static void initRecipe(RegistryEvent.Register<IRecipe> event)
   {
      Recipes.init();
   }
}