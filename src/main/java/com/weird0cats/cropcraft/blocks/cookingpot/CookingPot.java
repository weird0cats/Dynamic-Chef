package com.weird0cats.cropcraft.blocks.cookingpot;

import com.weird0cats.cropcraft.CropCraft;

import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class CookingPot extends Block implements ITileEntityProvider
{
   public static final int GUI_ID = 1;

   public CookingPot()
   {
      super(Material.IRON);
      setTranslationKey(CropCraft.MODID + ".cookingpot"); //WORMK! :D
      setRegistryName("cookingpot");
      setCreativeTab(CropCraft.modTab);
      setHardness(1F);
   }

   @Override
   public IBlockState getStateFromMeta(int meta)
   {
      return getDefaultState();
   }

   @Override
   public int getMetaFromState(IBlockState state)
   {
      return 0;
   }

   @SideOnly(Side.CLIENT)
   public void initModel()
   {
      ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(this), 0 , new ModelResourceLocation(getRegistryName(), "inventory"));
   }

   @Override
   @SideOnly(Side.CLIENT)
   public boolean shouldSideBeRendered(IBlockState blockState, IBlockAccess worldIn, BlockPos pos, EnumFacing side)
   {
      return false;
   }

   @Override
   public boolean isBlockNormalCube(IBlockState blockState)
   {
      return false;
   }
    
   @Override
   public boolean isOpaqueCube(IBlockState blockState)
   {
      return false;
   }

   @Override
   public TileEntity createNewTileEntity(World worldIn, int meta)
   {
      return new TileEntityCookingPot();
   }

   @Override
   public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing side, float hitX, float hitY, float hitZ)
   {
      if (world.isRemote)
      {
         return true;
      }
      TileEntity te = world.getTileEntity(pos);
      if (!(te instanceof TileEntityCookingPot))
      {
         return false;
      }
      player.openGui(CropCraft.instance, GUI_ID, world, pos.getX(), pos.getY(), pos.getZ());
      return true;
   }

   @Override
   public void breakBlock(World worldIn, BlockPos pos, IBlockState state)
   {
      TileEntity tileentity = worldIn.getTileEntity(pos);
      if (tileentity instanceof TileEntityCookingPot)
      {
         ((TileEntityCookingPot) tileentity).breakBlock(worldIn, pos, state);
         tileentity.invalidate();
         worldIn.removeTileEntity(pos);
      }
      super.breakBlock(worldIn, pos, state);
   } 

}