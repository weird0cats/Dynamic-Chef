package io.github.weird0cats.dynamicchef.compat.jei;

import io.github.weird0cats.dynamicchef.tileentity.TileEntityBrickOven;
import mezz.jei.api.IGuiHelper;
import mezz.jei.api.gui.IDrawable;
import mezz.jei.api.gui.IDrawableAnimated;
import mezz.jei.api.gui.IDrawableStatic;
import mezz.jei.api.gui.IGuiItemStackGroup;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.ingredients.VanillaTypes;
import mezz.jei.api.recipe.BlankRecipeCategory;
import mezz.jei.api.recipe.IRecipeWrapper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.ResourceLocation;

@SuppressWarnings({ "rawtypes", "deprecation" })
public class BakingRecipeCategory extends BlankRecipeCategory
{
   private final IDrawable background;
   private final String name;
   
   public static ResourceLocation texture = new ResourceLocation("dynamicchef:textures/gui/jei_cooking.png");

   protected IDrawableAnimated flame;
   protected IDrawableAnimated arrow;

   public BakingRecipeCategory(IGuiHelper helper)
   {
      this.background = helper.createDrawable(texture, 0, 0, 138, 63);
      this.name = I18n.format("dynamicchef.jei.recipe.baking");

      IDrawableStatic flameDrawable = helper.createDrawable(texture, 139, 0, 14, 14);
      flame = helper.createAnimatedDrawable(flameDrawable, 300, IDrawableAnimated.StartDirection.TOP, true);

      IDrawableStatic arrowDrawable = helper.createDrawable(texture, 139, 14, 23, 16);
      this.arrow = helper.createAnimatedDrawable(arrowDrawable, 300, IDrawableAnimated.StartDirection.LEFT, false);
   }

   @Override
   public void drawExtras(Minecraft minecraft)
   {
      flame.draw(minecraft, 62, 28);
      arrow.draw(minecraft, 82, 18);
   }

   @Override
   public IDrawable getBackground()
   {
      return this.background;
   }

   @Override
   public String getTitle()
   {
      return this.name;
   }

   @Override
   public String getUid()
   {
      return "dynamicchef.baking";
   }

   @Override
   public void setRecipe(IRecipeLayout layout, IRecipeWrapper wrapper, IIngredients ingredients)
   {
      IGuiItemStackGroup stacks = layout.getItemStacks();

      stacks.init(TileEntityBrickOven.INPUT_SLOTS_START + 0, true, 2, 4);
      stacks.init(TileEntityBrickOven.INPUT_SLOTS_START + 1, true, 21, 4);
      stacks.init(TileEntityBrickOven.INPUT_SLOTS_START + 2, true, 40, 4);
      stacks.init(TileEntityBrickOven.INPUT_SLOTS_START + 3, true, 2, 23);
      stacks.init(TileEntityBrickOven.INPUT_SLOTS_START + 4, true, 21, 23);
      stacks.init(TileEntityBrickOven.INPUT_SLOTS_START + 5, true, 40, 23);
      if (!ingredients.getInputs(VanillaTypes.ITEM).isEmpty())
      {
         stacks.set(TileEntityBrickOven.INPUT_SLOTS_START + 0, ingredients.getInputs(VanillaTypes.ITEM).get(0));
      }
      if (ingredients.getInputs(VanillaTypes.ITEM).size() > 1)
      {
         stacks.set(TileEntityBrickOven.INPUT_SLOTS_START + 1, ingredients.getInputs(VanillaTypes.ITEM).get(1));
      }
      if (ingredients.getInputs(VanillaTypes.ITEM).size() > 2)
      {
         stacks.set(TileEntityBrickOven.INPUT_SLOTS_START + 2, ingredients.getInputs(VanillaTypes.ITEM).get(2));
      }
      if (ingredients.getInputs(VanillaTypes.ITEM).size() > 3)
      {
         stacks.set(TileEntityBrickOven.INPUT_SLOTS_START + 3, ingredients.getInputs(VanillaTypes.ITEM).get(3));
      }
      if (ingredients.getInputs(VanillaTypes.ITEM).size() > 4)
      {
         stacks.set(TileEntityBrickOven.INPUT_SLOTS_START + 4, ingredients.getInputs(VanillaTypes.ITEM).get(4));
      }
      if (ingredients.getInputs(VanillaTypes.ITEM).size() > 5)
      {
         stacks.set(TileEntityBrickOven.INPUT_SLOTS_START + 5, ingredients.getInputs(VanillaTypes.ITEM).get(5));
      }
      
      stacks.init(TileEntityBrickOven.RESULT_SLOT, false, 114, 17);
      if (!ingredients.getInputs(VanillaTypes.ITEM).isEmpty())
      {
         stacks.set(TileEntityBrickOven.RESULT_SLOT, ingredients.getOutputs(VanillaTypes.ITEM).get(0));
      } 
   }

   @Override
   public String getModName()
   {
      return "Dynamic Chef";
   }
}
