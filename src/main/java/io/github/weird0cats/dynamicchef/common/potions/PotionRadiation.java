package io.github.weird0cats.dynamicchef.common.potions;

import java.util.ArrayList;
import java.util.List;

import io.github.weird0cats.dynamicchef.common.util.DynamicChefUtils;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.init.MobEffects;

public class PotionRadiation extends PotionBase
{
   public PotionRadiation()
   {
      super(true, 5882118, "radiation");
      setIconIndex(4, 0);
   }

   @Override
   public void performEffect(EntityLivingBase entity, int amp)
   {
      if (entity != null && !entity.world.isRemote)
      {
         if (amp > 1)
         {
            entity.addPotionEffect(new PotionEffect(MobEffects.NAUSEA, 400, amp, false, false));
         }
         if (amp > 2)
         {
            entity.addPotionEffect(new PotionEffect(MobEffects.BLINDNESS, 200, 0, false, false));
            entity.attackEntityFrom(DynamicChefUtils.RADIATION_DAMAGE, (float) (amp/2));
         }
      }
   }

	@Override
	public boolean isReady(int duration, int amplifier)
   {
		return (amplifier > 0) && ((duration % 100) == 0);
	}

   @Override
   public List<ItemStack> getCurativeItems()
   {
      return new ArrayList<>();
   }
}
