package com.weird0cats.dynamicchef.proxy;

import com.weird0cats.dynamicchef.client.gui.BrickOvenGui;
import com.weird0cats.dynamicchef.client.gui.CookingPotGui;
import com.weird0cats.dynamicchef.tileentity.ContainerBrickOven;
import com.weird0cats.dynamicchef.tileentity.ContainerCookingPot;
import com.weird0cats.dynamicchef.tileentity.TileEntityBrickOven;
import com.weird0cats.dynamicchef.tileentity.TileEntityCookingPot;

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