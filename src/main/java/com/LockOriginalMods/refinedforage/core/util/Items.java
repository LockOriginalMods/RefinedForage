package com.LockOriginalMods.refinedforage.core.util;

import com.LockOriginalMods.refinedforage.RefinedForage;
import com.LockOriginalMods.refinedforage.Registration;
import net.minecraft.data.DataGenerator;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

public class Items extends ItemModelProvider {

    public Items(DataGenerator generator, ExistingFileHelper existingFileHelper) {
        super(generator, RefinedForage.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        withExistingParent(Registration.COAL_GENERATOR.get().getRegistryName().getPath(), new ResourceLocation(RefinedForage.MOD_ID, "block/coal_generator"));
    }
}
