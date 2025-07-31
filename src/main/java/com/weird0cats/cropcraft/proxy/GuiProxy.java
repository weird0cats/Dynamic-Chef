package com.weird0cats.cropcraft.proxy;

import com.weird0cats.cropcraft.blocks.cookingpot.ContainerCookingPot;
import com.weird0cats.cropcraft.blocks.cookingpot.CookingPotGui;
import com.weird0cats.cropcraft.blocks.cookingpot.TileEntityCookingPot;

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
      return null;
   }

   @Override
   public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
      BlockPos pos = new BlockPos(x, y, z);
      TileEntity te = world.getTileEntity(pos);
      if (te instanceof TileEntityCookingPot) {
         TileEntityCookingPot containerTileEntity = (TileEntityCookingPot) te;
         return new CookingPotGui(containerTileEntity, new ContainerCookingPot(player.inventory, containerTileEntity));
      }
      return null;
   }
}