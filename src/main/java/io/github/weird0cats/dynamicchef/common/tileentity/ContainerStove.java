package io.github.weird0cats.dynamicchef.common.tileentity;

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

public class ContainerStove extends Container
{
   private final TileEntityStove te;

   public ContainerStove(IInventory playerInventory, TileEntityStove te)
   {
      this.te=te;
      addOwnSlots();
      addPlayerSlots(playerInventory);
   }

   public TileEntityStove getTile()
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
      addSlotToContainer(new SlotItemHandler(itemHandler, TileEntityStove.FUEL_SLOT, 35, 35)
      {
         @Override
         public boolean isItemValid(@Nonnull ItemStack stack)
         {
            return super.isItemValid(stack) && TileEntityFurnace.isItemFuel(stack);
         }
      });
   }

   @Override
   public boolean canInteractWith(EntityPlayer playerIn)
   {
      return true;
   }

   @Override
   public ItemStack transferStackInSlot(EntityPlayer playerIn, int index)
   {
      ItemStack itemStack = ItemStack.EMPTY;
      Slot slot = this.inventorySlots.get(index);

      if (slot != null && slot.getHasStack())
      {
         ItemStack itemStack1 = slot.getStack();
         itemStack = itemStack1.copy();

         if (index < TileEntityStove.NUM_SLOTS)
         {
            if (!this.mergeItemStack(itemStack1, TileEntityStove.NUM_SLOTS, this.inventorySlots.size(), true))
            {
               return ItemStack.EMPTY;
            }
         }
         else if (!this.mergeItemStack(itemStack1, 0, TileEntityStove.NUM_SLOTS, false))
         {
            return ItemStack.EMPTY;
         }

         if (itemStack1.isEmpty())
         {
            slot.putStack(ItemStack.EMPTY);
         }
         else
         {
            slot.onSlotChanged();
         }
      }

      return itemStack;
   }
}