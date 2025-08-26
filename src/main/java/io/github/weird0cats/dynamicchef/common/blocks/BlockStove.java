package io.github.weird0cats.dynamicchef.common.blocks;

import static io.github.weird0cats.dynamicchef.common.tileentity.TileEntityBrickOven.keepInventory;

import java.util.Random;

import io.github.weird0cats.dynamicchef.DynamicChef;
import io.github.weird0cats.dynamicchef.common.tileentity.TileEntityStove;
import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockStove extends Block implements ITileEntityProvider
{
   public static final int GUI_ID = 3;

   public static final PropertyDirection FACING = PropertyDirection.create("facing", EnumFacing.Plane.HORIZONTAL);

   public BlockStove(String name, boolean lit)
   {
      super (Material.ROCK);
      setTranslationKey(DynamicChef.MODID + "." + name);
      setRegistryName(name);
      if (!lit)
      {
         setCreativeTab(DynamicChef.modTab);
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

   @Override
   public Item getItemDropped(IBlockState state, Random rand, int fortune)
   {
      return Item.getItemFromBlock(ModBlocks.stove);
   }

   @Override
   public ItemStack getItem(World worldIn, BlockPos pos, IBlockState state)
   {
      return new ItemStack(ModBlocks.stove);
   }

   @Override
   protected BlockStateContainer createBlockState()
   {
      return new BlockStateContainer(this, new IProperty[] {FACING});
   }

   @SideOnly(Side.CLIENT)
   public void initModel()
   {
      ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(this), 0, new ModelResourceLocation(getRegistryName(), "inventory"));
   }

   @Override
   public TileEntity createNewTileEntity(World worldIn, int meta)
   {
      return new TileEntityStove();
   }

   @Override
   public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing side, float hitX, float hitY, float hitZ)
   {
      if (world.isRemote)
      {
         return true;
      }
      TileEntity te = world.getTileEntity(pos);
      if (!(te instanceof TileEntityStove))
      {
         return false;
      }
      player.openGui(DynamicChef.instance, GUI_ID, world, pos.getX(), pos.getY(), pos.getZ());
      return true;
   }

   @Override
   public void breakBlock(World worldIn, BlockPos pos, IBlockState state)
   {
      if (!keepInventory)
      {
         TileEntity te = worldIn.getTileEntity(pos);
         if (te instanceof TileEntityStove)
         {
            ((TileEntityStove) te).breakBlock(worldIn, pos, state);
            te.invalidate();
            worldIn.removeTileEntity(pos);
         }
      }
      super.breakBlock(worldIn, pos, state);
   }
}
