package io.github.weird0cats.dynamicchef.proxy;

import io.github.weird0cats.dynamicchef.client.gui.BrickOvenGui;
import io.github.weird0cats.dynamicchef.client.gui.CookingPotGui;
import io.github.weird0cats.dynamicchef.common.tileentity.ContainerBrickOven;
import io.github.weird0cats.dynamicchef.common.tileentity.ContainerCookingPot;
import io.github.weird0cats.dynamicchef.common.tileentity.TileEntityBrickOven;
import io.github.weird0cats.dynamicchef.common.tileentity.TileEntityCookingPot;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

public class GuiProxy implements IGuiHandler
{
   @Override
   public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
   {
      BlockPos pos = new BlockPos(x, y, z);
      TileEntity te = world.getTileEntity(pos);
      if (te instanceof TileEntityCookingPot)
      {
         return new ContainerCookingPot(player.inventory, (TileEntityCookingPot) te);
      }
      if (te instanceof TileEntityBrickOven)
      {
         return new ContainerBrickOven(player.inventory, (TileEntityBrickOven) te);
      }
      return null;
   }

   @Override
   public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
      BlockPos pos = new BlockPos(x, y, z);
      TileEntity te = world.getTileEntity(pos);
      if (te instanceof TileEntityCookingPot)
      {
         TileEntityCookingPot containerTileEntity = (TileEntityCookingPot) te;
         return new CookingPotGui(new ContainerCookingPot(player.inventory, containerTileEntity), player.inventory);
      }
      if (te instanceof TileEntityBrickOven)
      {
         TileEntityBrickOven containerTileEntity = (TileEntityBrickOven) te;
         return new BrickOvenGui(new ContainerBrickOven(player.inventory, containerTileEntity), player.inventory);
      }
      return null;
   }
}