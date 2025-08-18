package io.github.weird0cats.dynamicchef;

import org.apache.logging.log4j.Logger;

import io.github.weird0cats.dynamicchef.compat.crafttweaker.CraftTweakerHelper;
import io.github.weird0cats.dynamicchef.proxy.CommonProxy;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = DynamicChef.MODID, name = DynamicChef.NAME, version = DynamicChef.VERSION, useMetadata = true)
public class DynamicChef
{
   public static final String MODID = "dynamicchef";
   public static final String NAME = "Dynamic Chef";
   public static final String VERSION = "0.1.0";

   @SidedProxy(clientSide = "io.github.weird0cats.dynamicchef.proxy.ClientProxy",serverSide = "io.github.weird0cats.dynamicchef.proxy.ServerProxy")
   public static CommonProxy proxy;
    
   @Mod.Instance
   public static DynamicChef instance;

   public static Logger logger;
   public static CreativeTabs modTab = new DynamicChefTab();
   
   @Mod.EventHandler
   public void preInit(FMLPreInitializationEvent event)
   {
      logger = event.getModLog();
      proxy.preInit(event);

      if (Loader.isModLoaded("crafttweaker")) {
			CraftTweakerHelper.preInit();
		}
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

