package io.github.weird0cats.dynamicchef.compat.crafttweaker;

import crafttweaker.CraftTweakerAPI;

public class CraftTweakerHelper
{
   public static void preInit()
   {
      CraftTweakerAPI.registerClass(CookingPot.class);
      CraftTweakerAPI.registerClass(BrickOven.class);
   }
}