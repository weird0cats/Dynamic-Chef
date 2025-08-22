package io.github.weird0cats.dynamicchef.common.items;

import java.util.List;

import javax.annotation.Nullable;

import io.github.weird0cats.dynamicchef.common.potions.PotionsRegistry;
import io.github.weird0cats.dynamicchef.common.util.DynamicChefUtils;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.translation.I18n;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SuppressWarnings("deprecation")
public class ModItems
{
   public static ItemFoodBase TOMATO_BERRY;
   public static ItemFoodBase GRAPE_BERRY;
   public static ItemFoodBase DOUGH;
   public static ItemFoodBase RADCOOKIE;

   public static void init()
   {
      TOMATO_BERRY = new ItemFoodBase("lupa_berry", 2, 0.4f, false);
      GRAPE_BERRY = new ItemFoodBase("vitus_berry", 2, 0.4f, false);
      DOUGH = new ItemFoodBase("dough", 1, 0.2f, false);
      RADCOOKIE = new ItemFoodBase("uranium_cookie", 0, 0.0f, false)
      {
         @Override
         public void initFood()
         {
            setAlwaysEdible();
         }

         @Override
         protected void onFoodEaten(ItemStack stack, World worldIn, EntityPlayer player)
         {
            if (!worldIn.isRemote)
            {
               player.addPotionEffect(new PotionEffect(PotionsRegistry.RADIATION_POTION, 1200, 0));
            }
         }

         @Override
         @SideOnly(Side.CLIENT)
         public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn)
         {
            tooltip.add(TextFormatting.GREEN + "" + TextFormatting.ITALIC + I18n.translateToLocalFormatted("tooltip.dynamicchef.uranium_cookie"));
         }
      };
   }
   
   @SideOnly(Side.CLIENT)
   public static void initModels()
   {
      TOMATO_BERRY.initModel();
      GRAPE_BERRY.initModel();
      DOUGH.initModel();
      RADCOOKIE.initModel();
   }
}