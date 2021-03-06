package com.LockOriginalMods.refinedforage.common.data.jei;

import com.LockOriginalMods.refinedforage.RefinedForage;
import com.LockOriginalMods.refinedforage.common.data.ContainerGrinder;
import com.LockOriginalMods.refinedforage.common.data.GrindRecipe;
import com.LockOriginalMods.refinedforage.common.data.ScreenGrinder;

import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.registration.IGuiHandlerRegistration;
import mezz.jei.api.registration.IRecipeCatalystRegistration;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import mezz.jei.api.registration.IRecipeTransferRegistration;

import static com.LockOriginalMods.refinedforage.ModRegistry.B_GRINDER;
import static com.LockOriginalMods.refinedforage.RefinedForage.MOD_ID;


@JeiPlugin
public class PluginJEI implements IModPlugin {

  private static final int PLAYER_INV_SIZE = 4 * 9;
  private static final ResourceLocation ID = new ResourceLocation(MOD_ID, "jei");

  @Override
  public ResourceLocation getPluginUid() {
    return ID;
  }

  @Override
  public void registerRecipeCatalysts(IRecipeCatalystRegistration registration) {
    registration.addRecipeCatalyst(new ItemStack(B_GRINDER.asItem()), RecipeCat.ID);
  }

  @Override
  public void registerCategories(IRecipeCategoryRegistration registry) {
    IGuiHelper guiHelper = registry.getJeiHelpers().getGuiHelper();
    registry.addRecipeCategories(new RecipeCat(guiHelper));
  }

  @Override
  public void registerRecipes(IRecipeRegistration registry) {
    registry.addRecipes(GrindRecipe.RECIPES, RecipeCat.ID);
  }

  @Override
  public void registerGuiHandlers(IGuiHandlerRegistration registry) {
    registry.addRecipeClickArea(ScreenGrinder.class,
        75, 20,
        40, 26, RecipeCat.ID);
  }

  @Override
  public void registerRecipeTransferHandlers(IRecipeTransferRegistration registry) {
    registry.addRecipeTransferHandler(ContainerGrinder.class, RecipeCat.ID,
        0, 1, //recipeSLotStart, recipeSlotCount
        1, PLAYER_INV_SIZE); // inventorySlotStart, inventorySlotCount
  }
}
