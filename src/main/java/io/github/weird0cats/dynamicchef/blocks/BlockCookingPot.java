package io.github.weird0cats.dynamicchef.blocks;

import io.github.weird0cats.dynamicchef.DynamicChef;
import io.github.weird0cats.dynamicchef.tileentity.TileEntityCookingPot;
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
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockCookingPot extends Block implements ITileEntityProvider
{
   public static final int GUI_ID = 1;
   
   public static final PropertyDirection FACING = PropertyDirection.create("facing", EnumFacing.Plane.HORIZONTAL);
   
   protected static final AxisAlignedBB POT_AABB = new AxisAlignedBB(0.125D, 0.0D, 0.125D, 0.875D, 0.55D, 0.875D);

   public BlockCookingPot()
   {
      super(Material.IRON);
      setTranslationKey(DynamicChef.MODID + ".cookingpot"); //WORMK! :D
      setRegistryName("cookingpot");
      setCreativeTab(DynamicChef.modTab);
      setHardness(1F);
   }

   @Override
   public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos)
   {
      return POT_AABB;
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
   @SideOnly(Side.CLIENT)
   public boolean shouldSideBeRendered(IBlockState blockState, IBlockAccess worldIn, BlockPos pos, EnumFacing side)
   {
      return false;
   }

   @Override
   public boolean isFullCube(IBlockState blockState)
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
      player.openGui(DynamicChef.instance, GUI_ID, world, pos.getX(), pos.getY(), pos.getZ());
      return true;
   }

   @Override
   public void breakBlock(World worldIn, BlockPos pos, IBlockState state)
   {
      TileEntity te = worldIn.getTileEntity(pos);
      if (te instanceof TileEntityCookingPot)
      {
         ((TileEntityCookingPot) te).breakBlock(worldIn, pos, state);
         te.invalidate();
         worldIn.removeTileEntity(pos);
      }
      super.breakBlock(worldIn, pos, state);
   } 
}