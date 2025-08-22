package io.github.weird0cats.dynamicchef.proxy;

import java.io.File;

import io.github.weird0cats.dynamicchef.Config;
import io.github.weird0cats.dynamicchef.DynamicChef;
import io.github.weird0cats.dynamicchef.common.blocks.BlockBrickOven;
import io.github.weird0cats.dynamicchef.common.blocks.BlockCookingPot;
import io.github.weird0cats.dynamicchef.common.blocks.ModBlocks;
import io.github.weird0cats.dynamicchef.common.crafting.Recipes;
import io.github.weird0cats.dynamicchef.common.items.ModItems;
import io.github.weird0cats.dynamicchef.common.potions.PotionsRegistry;
import io.github.weird0cats.dynamicchef.common.tileentity.TileEntityBrickOven;
import io.github.weird0cats.dynamicchef.common.tileentity.TileEntityCookingPot;
import io.github.weird0cats.dynamicchef.common.util.DynamicChefUtils;
import net.minecraft.block.Block;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.passive.EntityParrot;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.potion.PotionEffect;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent.EntityInteract;
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
      PotionsRegistry.init();
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

   @SuppressWarnings("deprecation")
   @SubscribeEvent
   public static void registerBlocks(RegistryEvent.Register<Block> event)
   {
      event.getRegistry().register(new BlockCookingPot());
      event.getRegistry().register(new BlockBrickOven("brick_oven", false));
      event.getRegistry().register(new BlockBrickOven("brick_oven_lit", true));
      GameRegistry.registerTileEntity(TileEntityCookingPot.class, DynamicChef.MODID + ":cooking_pot_block");
      GameRegistry.registerTileEntity(TileEntityBrickOven.class, DynamicChef.MODID + ":brick_oven_block");

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

   @SubscribeEvent
   public static void onEntityInteract(EntityInteract event)
   {
      if (event.getTarget() instanceof EntityParrot)
      {
         ItemStack equippedItem = event.getEntityPlayer().getHeldItem(event.getHand());
         EntityAnimal targetAnimal = (EntityAnimal) event.getTarget();

         if (equippedItem.getItem() == ModItems.RADCOOKIE)
         {
				EntityPlayer player = event.getEntityPlayer();
            if (!player.capabilities.isCreativeMode)
            {
               equippedItem.shrink(1);
            }
            targetAnimal.addPotionEffect(new PotionEffect(PotionsRegistry.RADIATION_POTION, 4000, 6, false, true));
            targetAnimal.attackEntityFrom(DynamicChefUtils.RADIATION_DAMAGE, 10.0F);
         } 
      }
   }
}