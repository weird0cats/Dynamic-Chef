package com.weird0cats.cropcraft.blocks.cookingpot;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.items.CapabilityItemHandler;

public class TileEntityCookingPot extends TileEntity
{
   public static final int RESULT_SLOT = 0;
   public static final int FUEL_SLOT = 1;
   public static final int INPUT_SLOTS_START = 2;
   public static int NUM_SLOTS = 8;

   protected ItemStackHandler itemStackHandler = new ItemStackHandler(NUM_SLOTS)
   {
      @Override
      protected void onContentsChanged(int slot)
      {
         TileEntityCookingPot.this.markDirty();
      }
   };

   public String getName()
   {
      return "container.cropcraft.cookingpot";
   }

   protected int getInternalSize()
   {
      return NUM_SLOTS;
   }


   public void breakBlock(World worldIn, BlockPos pos, IBlockState state)
   {
      this.invalidate();
      if (itemStackHandler != null && !world.isRemote)
      {
         //this.fillWithLoot((EntityPlayer)null);
         for (int i=0; i < itemStackHandler.getSlots(); i++)
         {
            if (itemStackHandler.getStackInSlot(i) != null)
            {
               Block.spawnAsEntity(world, pos, itemStackHandler.getStackInSlot(i));
            }
         }
      }
   }
   
   public boolean canInteractWith(EntityPlayer playerIn)
   {
      return !isInvalid() && playerIn.getDistanceSq(pos.add(0.5D, 0.5D, 0.5D)) <= 64D;
   }
   
   @Override
   public void readFromNBT(NBTTagCompound compound) {
      super.readFromNBT(compound);
      if (compound.hasKey("items")) {
         itemStackHandler.deserializeNBT((NBTTagCompound) compound.getTag("items"));
      }
   }

   @Override
   public NBTTagCompound writeToNBT(NBTTagCompound compound) {
      super.writeToNBT(compound);
      compound.setTag("items", itemStackHandler.serializeNBT());
      return compound;
   }

   @Override
   public boolean hasCapability(Capability<?> capability, EnumFacing facing)
   {
      if (capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY)
      {
         return true;
      }
      return super.hasCapability(capability, facing);
   }
   
   @Override
   public <T> T getCapability(Capability<T> capability, EnumFacing facing)
   {
      if (capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY)
      {
         return CapabilityItemHandler.ITEM_HANDLER_CAPABILITY.cast(itemStackHandler);
      }
      return super.getCapability(capability, facing);
   }
}