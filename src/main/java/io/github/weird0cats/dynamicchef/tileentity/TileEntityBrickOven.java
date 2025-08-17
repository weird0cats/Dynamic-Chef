package io.github.weird0cats.dynamicchef.tileentity;

import static io.github.weird0cats.dynamicchef.blocks.BlockBrickOven.FACING;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import io.github.weird0cats.dynamicchef.ModBlocks;
import io.github.weird0cats.dynamicchef.crafting.IBrickOvenRecipe;
import io.github.weird0cats.dynamicchef.crafting.Recipes;
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
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.ItemStackHandler;

public class TileEntityBrickOven extends TileEntity implements ITickable
{
   public static boolean keepInventory;
   public static final int RESULT_SLOT = 0;
   public static final int FUEL_SLOT = 1; 
   public static final int INPUT_SLOTS_START = 2;
   public static int NUM_SLOTS = 8;

   protected ItemStackHandler itemStackHandler = new ItemStackHandler(NUM_SLOTS)
   {
      @Override
      protected void onContentsChanged(int slot)
      {
         TileEntityBrickOven.this.markDirty();
         TileEntityBrickOven.this.hasContentChanged = true;
      }
   };

   public String getName()
   {
      return "container.dynamicchef.brickoven";
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

   protected IBrickOvenRecipe currentRecipe;
   protected boolean hasContentChanged;

   public int ovenBurnTime;
   public int currentItemBurnTime;
   public int cookTime;
   public int totalCookTime;

   @Override
   public void update()
   {
      boolean check0 = this.isBurning();
      boolean check1;
      
      boolean isCooking = this.canCook();
      boolean hasRecipe = isCooking && this.hasValidRecipe();
      if (burnFuel(isCooking && hasRecipe) && isCooking && hasRecipe)
      {
         ++this.cookTime;
         if (this.cookTime >= this.currentRecipe.getTime())
         {
            this.cookTime = 0;
            if (!world.isRemote)
            {
               cook();
            }
         }
      }
      else if (this.cookTime > 0)
      {
         this.cookTime = MathHelper.clamp(this.cookTime - 2, 0, this.totalCookTime);
      }
      check1 = this.isBurning();
      if (check0 != this.isBurning()) {
         IBlockState iblockstate = world.getBlockState(pos);
         TileEntity tileentity = world.getTileEntity(pos);

         keepInventory = true;

         if (check1)
         {
            world.setBlockState(pos, ModBlocks.brickOvenLit.getDefaultState().withProperty(FACING, iblockstate.getValue(FACING)), 3);
            world.setBlockState(pos, ModBlocks.brickOvenLit.getDefaultState().withProperty(FACING, iblockstate.getValue(FACING)), 3);
         }
         else
         {
            world.setBlockState(pos, ModBlocks.brickOven.getDefaultState().withProperty(FACING, iblockstate.getValue(FACING)), 3);
            world.setBlockState(pos, ModBlocks.brickOven.getDefaultState().withProperty(FACING, iblockstate.getValue(FACING)), 3);
         }
         keepInventory = false;

         if (tileentity != null)
         {
            tileentity.validate();
            world.setTileEntity(pos, tileentity);
         }
      }
      if (check1)
      {
         this.markDirty();
      }
   }

   public boolean canCook()
   {
      return !(this.itemStackHandler.getStackInSlot(INPUT_SLOTS_START+0).isEmpty()
              && this.itemStackHandler.getStackInSlot(INPUT_SLOTS_START+1).isEmpty()
              && this.itemStackHandler.getStackInSlot(INPUT_SLOTS_START+2).isEmpty()
              && this.itemStackHandler.getStackInSlot(INPUT_SLOTS_START+3).isEmpty()
              && this.itemStackHandler.getStackInSlot(INPUT_SLOTS_START+4).isEmpty()
              && this.itemStackHandler.getStackInSlot(INPUT_SLOTS_START+5).isEmpty());
   }

   public boolean hasValidRecipe()
   {
      //burn check shortcut
      if (this.ovenBurnTime <=0 && this.itemStackHandler.getStackInSlot(FUEL_SLOT).isEmpty())
      {
         return false;
      }
      if (this.hasContentChanged)
      {
         this.refreshCurrentRecipe();
      }
      if (this.currentRecipe == null)
      {
         return false;
      }
      return this.itemStackHandler.insertItem(RESULT_SLOT, this.currentRecipe.getResult(), true).isEmpty();
   }

   protected void refreshCurrentRecipe()
   {
      if (this.hasContentChanged)
      {
         this.hasContentChanged = false;
         ItemStack[] inputs = new ItemStack[]{ this.itemStackHandler.getStackInSlot(INPUT_SLOTS_START + 0), this.itemStackHandler.getStackInSlot(INPUT_SLOTS_START + 1), this.itemStackHandler.getStackInSlot(INPUT_SLOTS_START + 2), this.itemStackHandler.getStackInSlot(INPUT_SLOTS_START + 3), this.itemStackHandler.getStackInSlot(INPUT_SLOTS_START + 3), this.itemStackHandler.getStackInSlot(INPUT_SLOTS_START + 4), this.itemStackHandler.getStackInSlot(INPUT_SLOTS_START + 5)};
         if (this.currentRecipe != null && this.currentRecipe.matches(inputs))
         {
            return;
         }
         this.cookTime = 0;
         this.currentRecipe = null;
         for (IBrickOvenRecipe recipe : Recipes.brickOvenRecipes)
         {
            if (recipe.matches(inputs))
            {
               this.currentRecipe = recipe;
               this.totalCookTime = recipe.getTime();
               return;
            }
         }
      }
   }

   private boolean burnFuel(boolean consumeNewFuel)
   {
      if (this.ovenBurnTime > 0)
      {
         --this.ovenBurnTime;
         return true;
      }
      ItemStack fuelStack = itemStackHandler.getStackInSlot(FUEL_SLOT);
      if (consumeNewFuel && !fuelStack.isEmpty())
      {
         this.ovenBurnTime = TileEntityFurnace.getItemBurnTime(fuelStack);
         this.currentItemBurnTime = this.ovenBurnTime;
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

   public boolean isBurning()
   {
      return this.ovenBurnTime > 0;
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

   protected void cook()
   {
      if (this.canCook())
      {
         ItemStack[] inputs = new ItemStack[]{ this.itemStackHandler.getStackInSlot(INPUT_SLOTS_START + 0), this.itemStackHandler.getStackInSlot(INPUT_SLOTS_START + 1), this.itemStackHandler.getStackInSlot(INPUT_SLOTS_START + 2), this.itemStackHandler.getStackInSlot(INPUT_SLOTS_START + 3), this.itemStackHandler.getStackInSlot(INPUT_SLOTS_START + 3), this.itemStackHandler.getStackInSlot(INPUT_SLOTS_START + 4), this.itemStackHandler.getStackInSlot(INPUT_SLOTS_START + 5)};
         IBrickOvenRecipe recipe = this.currentRecipe;
         if (recipe.matches(inputs))
         {
            itemStackHandler.insertItem(RESULT_SLOT, recipe.getResult(), false);
            itemStackHandler.extractItem(INPUT_SLOTS_START + 0, 1, false);
            itemStackHandler.extractItem(INPUT_SLOTS_START + 1, 1, false);
            itemStackHandler.extractItem(INPUT_SLOTS_START + 2, 1, false);
            itemStackHandler.extractItem(INPUT_SLOTS_START + 3, 1, false);
            itemStackHandler.extractItem(INPUT_SLOTS_START + 4, 1, false);
            itemStackHandler.extractItem(INPUT_SLOTS_START + 5, 1, false);

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
      this.ovenBurnTime = compound.getInteger("BurnTime");
		this.cookTime = compound.getInteger("CookTime");
		this.totalCookTime = compound.getInteger("CookTimeTotal");
		this.currentItemBurnTime = compound.getInteger("ItemBurnTime");
		this.hasContentChanged = true;
   }

   @Override
   public NBTTagCompound writeToNBT(NBTTagCompound compound)
   {
      compound = super.writeToNBT(compound);
      compound.setTag("items", itemStackHandler.serializeNBT());
      compound.setInteger("BurnTime", (short) this.ovenBurnTime);
		compound.setInteger("CookTime", (short) this.cookTime);
		compound.setInteger("CookTimeTotal", (short) this.totalCookTime);
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