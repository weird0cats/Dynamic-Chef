package com.weird0cats.cropcraft.blocks;

import java.util.Random;

import com.weird0cats.cropcraft.CropCraft;
import com.weird0cats.cropcraft.ModBlocks;
import com.weird0cats.cropcraft.tileentity.TileEntityBrickOven;
import static com.weird0cats.cropcraft.tileentity.TileEntityBrickOven.keepInventory;

import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockBrickOven extends Block implements ITileEntityProvider
{
   public static final int GUI_ID = 2;
   
   public static final PropertyDirection FACING = PropertyDirection.create("facing", EnumFacing.Plane.HORIZONTAL);

   public BlockBrickOven(String name, boolean lit)
   {
      super(Material.ROCK);
      setTranslationKey(CropCraft.MODID + "." + name);
      setRegistryName(name);
      if (!lit)
      {
         setCreativeTab(CropCraft.modTab);
      }
      setHardness(1F);
   }

   @Override
   public IBlockState getStateForPlacement(World world, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer)
   {
      return this.getDefaultState().withProperty(FACING, placer.getHorizontalFacing().getOpposite());
   }
   
   @Override
   public IBlockState getStateFromMeta(int meta)
   {
      return this.getDefaultState().withProperty(FACING, EnumFacing.byIndex(5 - (meta & 3)));

   }

   @Override
   public int getMetaFromState(IBlockState state)
   {
      int meta = 0;

      meta |= 5 - ((EnumFacing) state.getValue(FACING)).getIndex();

      return meta;
   }

   private TileEntityBrickOven getTE(IBlockAccess world, BlockPos pos)
   {
      return (TileEntityBrickOven) world.getTileEntity(pos);
   }

   @Override
   public Item getItemDropped(IBlockState state, Random rand, int fortune)
   {
      return Item.getItemFromBlock(ModBlocks.brickOven);
   }
   
   @Override
   public ItemStack getItem(World worldIn, BlockPos pos, IBlockState state)
   {
      return new ItemStack(ModBlocks.brickOven);
   }
   @Override
   protected BlockStateContainer createBlockState()
   {
      return new BlockStateContainer(this, new IProperty[] { FACING });
   }

   @SideOnly(Side.CLIENT)
   public void initModel()
   {
      ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(this), 0 , new ModelResourceLocation(getRegistryName(), "inventory"));
   }

   @Override
   public TileEntity createNewTileEntity(World worldIn, int meta)
   {
      return new TileEntityBrickOven();
   }

   @Override
   public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing side, float hitX, float hitY, float hitZ)
   {
      if (world.isRemote)
      {
         return true;
      }
      TileEntity te = world.getTileEntity(pos);
      if (!(te instanceof TileEntityBrickOven))
      {
         return false;
      }
      player.openGui(CropCraft.instance, GUI_ID, world, pos.getX(), pos.getY(), pos.getZ());
      return true;
   }

   @Override
   public void breakBlock(World worldIn, BlockPos pos, IBlockState state)
   {
      if (!keepInventory)
      {  
         TileEntity te = worldIn.getTileEntity(pos);
         if (te instanceof TileEntityBrickOven)
         {
            ((TileEntityBrickOven) te).breakBlock(worldIn, pos, state);
            te.invalidate();
            worldIn.removeTileEntity(pos);
         }
      }
      super.breakBlock(worldIn, pos, state);
   }
   
   @SideOnly(Side.CLIENT)
   @SuppressWarnings("incomplete-switch")
   @Override
   public void randomDisplayTick(IBlockState stateIn, World worldIn, BlockPos pos, Random rand) {
      if (this.getTE(worldIn, pos).isBurning()) {
         EnumFacing enumfacing = (EnumFacing) stateIn.getValue(FACING);
         double d0 = (double) pos.getX() + 0.5D;
         double d1 = (double) pos.getY() + rand.nextDouble() * 6.0D / 16.0D;
         double d2 = (double) pos.getZ() + 0.5D;
         double d4 = rand.nextDouble() * 0.6D - 0.3D;

         if (rand.nextDouble() < 0.1D) {
            worldIn.playSound((double) pos.getX() + 0.5D, (double) pos.getY(), (double) pos.getZ() + 0.5D, SoundEvents.BLOCK_FURNACE_FIRE_CRACKLE, SoundCategory.BLOCKS, 1.0F, 1.0F, false);
         }

         switch (enumfacing) {
            case WEST:
               worldIn.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, d0 - 0.52D, d1, d2 + d4, 0.0D, 0.0D, 0.0D);
               worldIn.spawnParticle(EnumParticleTypes.FLAME, d0 - 0.52D, d1, d2 + d4, 0.0D, 0.0D, 0.0D);
               break;
            case EAST:
               worldIn.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, d0 + 0.52D, d1, d2 + d4, 0.0D, 0.0D, 0.0D);
               worldIn.spawnParticle(EnumParticleTypes.FLAME, d0 + 0.52D, d1, d2 + d4, 0.0D, 0.0D, 0.0D);
               break;
            case NORTH:
               worldIn.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, d0 + d4, d1, d2 - 0.52D, 0.0D, 0.0D, 0.0D);
               worldIn.spawnParticle(EnumParticleTypes.FLAME, d0 + d4, d1, d2 - 0.52D, 0.0D, 0.0D, 0.0D);
               break;
             case SOUTH:
               worldIn.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, d0 + d4, d1, d2 + 0.52D, 0.0D, 0.0D, 0.0D);
               worldIn.spawnParticle(EnumParticleTypes.FLAME, d0 + d4, d1, d2 + 0.52D, 0.0D, 0.0D, 0.0D);
         }
      }
   }
}