package io.github.weird0cats.dynamicchef.common.potions;

import net.minecraft.potion.Potion;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class PotionsRegistry
{
   public static final Potion RADIATION_POTION = new PotionRadiation();

   public static void init()
   {
      GameRegistry.findRegistry(Potion.class).register(RADIATION_POTION);
   }
}