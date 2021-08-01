package com.LockOriginalMods.refinedforage.common.data;

import net.minecraft.item.crafting.IRecipeType;

import static com.LockOriginalMods.refinedforage.RefinedForage.MOD_ID;

public class ModRecipeType<RECIPE_TYPE extends GrindRecipe> implements IRecipeType<RECIPE_TYPE> {

  public static final ModRecipeType<GrindRecipe> GRIND = create("grinder");

  private static <RECIPE_TYPE extends GrindRecipe> ModRecipeType<RECIPE_TYPE> create(String name) {
    ModRecipeType<RECIPE_TYPE> type = new ModRecipeType<>(name);
    return type;
  }

  private String registryName;

  private ModRecipeType(String name) {
    this.registryName = name;
  }

  @Override
  public String toString() {
    return MOD_ID + ":" + registryName;
  }
}
