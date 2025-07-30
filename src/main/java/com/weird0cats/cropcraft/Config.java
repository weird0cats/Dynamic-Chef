package com.weird0cats.cropcraft;

import com.weird0cats.cropcraft.proxy.CommonProxy;

import net.minecraftforge.common.config.Configuration;
import org.apache.logging.log4j.Level;

public class Config
{
   public static final String CATEGORY_GENERAL="general";
   public static boolean addDucks = true;

   public static void readConfig() {
      Configuration cfg = CommonProxy.config;
      try
      {
         cfg.load();
         initGeneralConfig(cfg);

      }
      catch (Exception e1)
      {
         CropCraft.logger.log(Level.ERROR, "Error parsing configuration file!", e1);
      }
      finally
      {
         if (cfg.hasChanged())
         {
            cfg.save();
         }
      }
   }
   private static void initGeneralConfig(Configuration cfg)
   {
      cfg.addCustomCategoryComment(CATEGORY_GENERAL, "General configuration");
      addDucks=cfg.getBoolean("ducksSpawn", CATEGORY_GENERAL, addDucks, "Toggles if ducks can spawn in the game");
   }
}