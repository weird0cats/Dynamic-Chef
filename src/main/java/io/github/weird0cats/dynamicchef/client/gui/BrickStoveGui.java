package io.github.weird0cats.dynamicchef.client.gui;

import io.github.weird0cats.dynamicchef.DynamicChef;
import io.github.weird0cats.dynamicchef.common.tileentity.ContainerStove;
import io.github.weird0cats.dynamicchef.common.tileentity.TileEntityStove;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;

public class BrickStoveGui extends GuiContainer
{
   public static final int WIDTH = 176;
   public static final int HEIGHT = 166;

   public static final ResourceLocation background = new ResourceLocation(DynamicChef.MODID, "textures/gui/brick_stove.png");

   private final TileEntityStove te;
   private final InventoryPlayer playerInv;

   public BrickStoveGui(ContainerStove container, InventoryPlayer playerInv)
   {
      super(container);

      this.playerInv = playerInv;
      this.te = container.getTile();
      xSize = WIDTH;
      ySize = HEIGHT;
   }

   @Override
   public void drawScreen(int mouseX, int mouseY, float partialTicks)
   {
      this.drawDefaultBackground();
      super.drawScreen(mouseX, mouseY, partialTicks);
      this.renderHoveredToolTip(mouseX, mouseY);
   }

   @Override
   protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY)
   {
      mc.getTextureManager().bindTexture(background);
      drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);

      if (te.isBurning())
      {
         int k = this.getBurnLeftScaled(13);
         this.drawTexturedModalRect(guiLeft + 83, guiTop + 50 + 12 - k, 176, 12 - k, 14, k + 1);
      }
   }

   @Override
	protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY)
   {
      
      this.fontRenderer.drawString(this.te.getDisplayName().getUnformattedText(), 8, 6, 4210752);   
      this.fontRenderer.drawString(this.playerInv.getDisplayName().getUnformattedText(), 8, this.ySize - 96 + 2, 4210752);
   }
   
   private int getBurnLeftScaled(int pixels) {
		int i = this.te.currentItemBurnTime;
		if (i == 0)
      {
			i = 200;
		}
		return this.te.stoveBurnTime * pixels / i;
	}
}
