package io.github.weird0cats.dynamicchef.common.tileentity;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import static io.github.weird0cats.dynamicchef.common.blocks.BlockStove.FACING;
import io.github.weird0cats.dynamicchef.common.blocks.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.ItemStackHandler;

public class TileEntityStove extends TileEntity implements ITickable
{
   public static boolean keepInventory;
   public static final int FUEL_SLOT = 0;
   public static final int NUM_SLOTS = 1;

   protected ItemStackHandler itemStackHandler = new ItemStackHandler(NUM_SLOTS)
   {
      @Override
      protected void onContentsChanged(int slot)
      {
         TileEntityStove.this.markDirty();
      }
   };

   public String getName()
   {
      return "container.dynamicchef.stove";
   }

   @Override
   public ITextComponent getDisplayName()
   {
      return (ITextComponent) new TextComponentTranslation(this.getName(), new Object[0]);
   }

   protected int getInternalSize()
   {
      return NUM_SLOTS;
   }

   protected boolean hasContentChanged;

   public int stoveBurnTime;
   public int currentItemBurnTime;

   @Override
   public void update()
   {
      boolean check0 = this.isBurning();
      boolean check1;
      if (burnFuel())
      {
         
      }
      
      check1 = this.isBurning();
      if (check0 != this.isBurning())
      {
         IBlockState iblockstate = world.getBlockState(pos);
         TileEntity tileentity = world.getTileEntity(pos);

         keepInventory = true;

         if (check1)
         {
            world.setBlockState(pos, ModBlocks.stoveLit.getDefaultState().withProperty(FACING, iblockstate.getValue(FACING)), 3);
            world.setBlockState(pos, ModBlocks.stoveLit.getDefaultState().withProperty(FACING, iblockstate.getValue(FACING)), 3);
         }
         else
         {
            world.setBlockState(pos, ModBlocks.stove.getDefaultState().withProperty(FACING, iblockstate.getValue(FACING)), 3);
            world.setBlockState(pos, ModBlocks.stove.getDefaultState().withProperty(FACING, iblockstate.getValue(FACING)), 3);
         }

         keepInventory = false;
         if (tileentity != null)
         {
            tileentity.validate();
            world.setTileEntity(pos, tileentity);
         }
      }
   }

   public boolean isBurning()
   {
      return this.stoveBurnTime > 0;
   }

   private boolean burnFuel()
   {
      if (this.stoveBurnTime > 0)
      {
         --this.stoveBurnTime;
         return true;
      }
      ItemStack fuelStack = itemStackHandler.getStackInSlot(FUEL_SLOT);
      if (!fuelStack.isEmpty())
      {
         this.stoveBurnTime = TileEntityFurnace.getItemBurnTime(fuelStack);
         this.currentItemBurnTime = this.stoveBurnTime;
         if (!world.isRemote)
         {
            Item fuelItem = fuelStack.getItem();
            fuelStack.shrink(1);
            if (fuelStack.isEmpty())
            {
               itemStackHandler.setStackInSlot(FUEL_SLOT, fuelItem.getContainerItem(fuelStack));
            }
         }
         return true;
      }
      return false;
   }

   public void breakBlock(World worldIn, BlockPos pos, IBlockState state)
   {
      this.invalidate();
      if (itemStackHandler != null && !world.isRemote)
      {
         for (int i=0; i < itemStackHandler.getSlots(); i++)
         {
            Block.spawnAsEntity(world, pos, itemStackHandler.getStackInSlot(i));
         }
      }
   }
   
   public boolean canInteractWith(EntityPlayer playerIn)
   {
      return !isInvalid() && playerIn.getDistanceSq(pos.add(0.5D, 0.5D, 0.5D)) <= 64D;
   }
   
   @Override
   public void readFromNBT(NBTTagCompound compound)
   { 
      super.readFromNBT(compound);
      if (compound.hasKey("items"))
      {
         itemStackHandler.deserializeNBT((NBTTagCompound) compound.getTag("items"));
      }
		this.currentItemBurnTime = compound.getInteger("ItemBurnTime");
		this.hasContentChanged = true;
   }

   @Override
   public NBTTagCompound writeToNBT(NBTTagCompound compound)
   {
      compound = super.writeToNBT(compound);
      compound.setTag("items", itemStackHandler.serializeNBT());
		compound.setInteger("ItemBurnTime", this.currentItemBurnTime);
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
   @Nullable
   public <T> T getCapability(@Nonnull Capability<T> capability, @Nullable EnumFacing facing)
   {
      if (capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY)
      {
         return CapabilityItemHandler.ITEM_HANDLER_CAPABILITY.cast(itemStackHandler);
      }
      return super.getCapability(capability, facing);
   }
}
