package com.weird0cats.cropcraft.compat.crafttweaker;

import crafttweaker.CraftTweakerAPI;

public class CraftTweakerHelper
{
   public static void preInit()
   {
      CraftTweakerAPI.registerClass(CookingPot.class);
      CraftTweakerAPI.registerClass(BrickOven.class);
   }
}