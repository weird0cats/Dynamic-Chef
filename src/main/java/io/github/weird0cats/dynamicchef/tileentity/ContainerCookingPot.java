package io.github.weird0cats.dynamicchef.tileentity;

import javax.annotation.Nonnull;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;


public class ContainerCookingPot extends Container 
{
   private final TileEntityCookingPot te;

   public ContainerCookingPot(IInventory playerInventory, TileEntityCookingPot te)
   {
      this.te=te;
      addOwnSlots();
      addPlayerSlots(playerInventory);
   }

   public TileEntityCookingPot getTile()
   {
      return te;
   }

   private void addPlayerSlots(IInventory playerInventory)
   {
      for (int row=0; row < 3; ++row)
      {
         for (int col=0; col< 9; ++col)
         {
            int x = 8 + col * 18;
            int y = row * 18 + 84;
            this.addSlotToContainer(new Slot(playerInventory, col + row * 9 + 9, x, y));
         }
      }
      
      for (int row = 0; row < 9; ++row)
      {
			int x = 8 + row * 18;
			int y = 142;
			this.addSlotToContainer(new Slot(playerInventory, row, x, y));
		}
   }

   private void addOwnSlots()
   {
      IItemHandler itemHandler = this.te.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null);
      addSlotToContainer(new SlotItemHandler(itemHandler, TileEntityCookingPot.RESULT_SLOT, 123, 35)
      {
         @Override
         public boolean isItemValid(@Nonnull ItemStack stack)
         {
            return false;
         }
      });
      addSlotToContainer(new SlotItemHandler(itemHandler, TileEntityCookingPot.FUEL_SLOT, 70, 62)
      {
         @Override
			public boolean isItemValid(@Nonnull ItemStack stack)
         {
				return super.isItemValid(stack) && TileEntityFurnace.isItemFuel(stack);
			}
      });
      addSlotToContainer(new SlotItemHandler(itemHandler, TileEntityCookingPot.INPUT_SLOTS_START+0, 11, 22));
      addSlotToContainer(new SlotItemHandler(itemHandler, TileEntityCookingPot.INPUT_SLOTS_START+1, 30, 22));
      addSlotToContainer(new SlotItemHandler(itemHandler, TileEntityCookingPot.INPUT_SLOTS_START+2, 49, 22));
      addSlotToContainer(new SlotItemHandler(itemHandler, TileEntityCookingPot.INPUT_SLOTS_START+3, 11, 41));
      addSlotToContainer(new SlotItemHandler(itemHandler, TileEntityCookingPot.INPUT_SLOTS_START+4, 30, 41));
      addSlotToContainer(new SlotItemHandler(itemHandler, TileEntityCookingPot.INPUT_SLOTS_START+5, 49, 41));
   }

   @Override
   public boolean canInteractWith(EntityPlayer playerIn)
   {
      return true;
   }

   @Override
   public ItemStack transferStackInSlot(EntityPlayer playerIn, int index)
   {
      ItemStack itemstack = ItemStack.EMPTY;
      Slot slot = this.inventorySlots.get(index);

      if (slot != null && slot.getHasStack())
      {
         ItemStack itemstack1 = slot.getStack();
         itemstack = itemstack1.copy();

         if (index < TileEntityCookingPot.NUM_SLOTS)
         {
            if (!this.mergeItemStack(itemstack1, TileEntityCookingPot.NUM_SLOTS, this.inventorySlots.size(), true))
            {
               return ItemStack.EMPTY;
            }
         } else if (!this.mergeItemStack(itemstack1, 0, TileEntityCookingPot.NUM_SLOTS, false))
         {
            return ItemStack.EMPTY;
         }

         if (itemstack1.isEmpty())
         {
            slot.putStack(ItemStack.EMPTY);
         } else {
            slot.onSlotChanged();
         }
      }

      return itemstack;
   }
}