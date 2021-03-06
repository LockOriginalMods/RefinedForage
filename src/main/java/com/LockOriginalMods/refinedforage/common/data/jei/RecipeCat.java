package com.LockOriginalMods.refinedforage.common.data.jei;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.LockOriginalMods.refinedforage.common.data.GrindRecipe;
import com.LockOriginalMods.refinedforage.common.data.ModRecipeType;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TranslationTextComponent;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.gui.ingredient.IGuiItemStackGroup;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.category.IRecipeCategory;

import static com.LockOriginalMods.refinedforage.ModRegistry.B_GRINDER;
import static com.LockOriginalMods.refinedforage.RefinedForage.MOD_ID;

public class RecipeCat implements IRecipeCategory<GrindRecipe> {

  public static final ResourceLocation ID = new ResourceLocation(ModRecipeType.GRIND.toString());
  private IDrawable gui;
  private IDrawable icon;

  public RecipeCat(IGuiHelper helper) {
    gui = helper.drawableBuilder(new ResourceLocation(MOD_ID, "textures/gui/jei.png"), 0, 0, 169, 69).setTextureSize(169, 69).build();
    icon = helper.drawableBuilder(new ResourceLocation(MOD_ID, "textures/block/grinder_top.png"), 0, 0, 16, 16).setTextureSize(16, 16).build();
  }

  @Override
  public ResourceLocation getUid() {
    return ID;
  }

  @Override
  public IDrawable getIcon() {
    return icon;
  }

  @Override
  public IDrawable getBackground() {
    return gui;
  }

  @Override
  public Class<? extends GrindRecipe> getRecipeClass() {
    return GrindRecipe.class;
  }

  @Override
  public String getTitle() {
    return lang(B_GRINDER.getDescriptionId());
  }

  public static String lang(String message) {
    TranslationTextComponent t = new TranslationTextComponent(message);
    return t.getString();
  }

  @Override
  public void setIngredients(GrindRecipe recipe, IIngredients ingredients) {
    List<List<ItemStack>> in = new ArrayList<>();
    List<ItemStack> stuff = new ArrayList<>();
    Collections.addAll(stuff, recipe.input.getItems());
    in.add(stuff);
    ingredients.setInputLists(VanillaTypes.ITEM, in);
    ingredients.setOutput(VanillaTypes.ITEM, recipe.getResultItem());
  }

  @Override
  public void setRecipe(IRecipeLayout recipeLayout, GrindRecipe recipe, IIngredients ingredients) {
    IGuiItemStackGroup guiItemStacks = recipeLayout.getItemStacks();
    guiItemStacks.init(0, true, 3, 18);
    //
    List<List<ItemStack>> inputs = ingredients.getInputs(VanillaTypes.ITEM);
    List<ItemStack> input = inputs.get(0);
    if (input != null && input.isEmpty() == false) {
      guiItemStacks.set(0, input);
    }
    guiItemStacks.init(1, false, 107, 18);
    guiItemStacks.set(1, recipe.getResultItem());
  }
}
