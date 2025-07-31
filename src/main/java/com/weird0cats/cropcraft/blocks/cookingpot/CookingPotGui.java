package com.weird0cats.cropcraft.blocks.cookingpot;

import com.weird0cats.cropcraft.CropCraft;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.util.ResourceLocation;

public class CookingPotGui extends GuiContainer
{
   public static final int WIDTH = 180;
   public static final int HEIGHT = 152;

   private static final ResourceLocation background = new ResourceLocation(CropCraft.MODID, "textures/gui/cookingpot.png");

   public CookingPotGui(TileEntityCookingPot tileEntity, ContainerCookingPot container)
   {
      super(container);

      xSize = WIDTH;
      ySize = HEIGHT;
   }

   @Override
   protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY)
   {
      mc.getTextureManager().bindTexture(background);
      drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);
   }
}