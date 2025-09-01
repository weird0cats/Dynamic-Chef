package io.github.weird0cats.dynamicchef.client.gui;

import io.github.weird0cats.dynamicchef.DynamicChef;
import io.github.weird0cats.dynamicchef.common.tileentity.ContainerCookingPot;
import io.github.weird0cats.dynamicchef.common.tileentity.TileEntityCookingPot;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;

public class CookingPotGui extends GuiContainer
{
   public static final int WIDTH = 176;
   public static final int HEIGHT = 166;

   private static final ResourceLocation background = new ResourceLocation(DynamicChef.MODID, "textures/gui/cooking_pot.png");

   private final TileEntityCookingPot te;
   private final InventoryPlayer playerInv;

   public CookingPotGui(ContainerCookingPot container, InventoryPlayer playerInv)
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

      int l = this.getCookProgressScaled(23);
		this.drawTexturedModalRect(guiLeft + 90, guiTop + 35, 176, 0, l, 17);
   }
   
   @Override
	protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY)
   {
      
      this.fontRenderer.drawString(this.te.getDisplayName().getUnformattedText(), 8, 6, 4210752);   
      this.fontRenderer.drawString(this.playerInv.getDisplayName().getUnformattedText(), 8, this.ySize - 96 + 2, 4210752);
   }
   
   private int getCookProgressScaled(int pixels) {
		int i = this.te.cookTime;
		int j = this.te.totalCookTime;
		return j != 0 && i != 0 ? (i * pixels / j) + 1 : 0;
	}

}