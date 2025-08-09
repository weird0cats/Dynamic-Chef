package com.weird0cats.cropcraft.proxy;

import com.weird0cats.cropcraft.CropCraft;
import com.weird0cats.cropcraft.ModBlocks;
import com.weird0cats.cropcraft.ModItems;

import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.obj.OBJLoader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;

@Mod.EventBusSubscriber(Side.CLIENT)
public class ClientProxy extends CommonProxy
{
   @Override
   public void preInit(FMLPreInitializationEvent e)
   {
      super.preInit(e);

      OBJLoader.INSTANCE.addDomain(CropCraft.MODID);
   }

   @SubscribeEvent
   public static void registerModels(ModelRegistryEvent event)
   {
      ModBlocks.initModels();
      ModItems.initModels();
   }
}