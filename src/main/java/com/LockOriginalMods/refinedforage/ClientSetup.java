package com.LockOriginalMods.refinedforage;

import com.LockOriginalMods.refinedforage.block.Generator.GeneratorScreen;
import com.LockOriginalMods.refinedforage.block.Generator.IncineratorContainer;

import com.LockOriginalMods.refinedforage.block.Generator.SlotlessGeneratorContainer;
import net.minecraft.block.Block;
import net.minecraft.client.gui.ScreenManager;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.entity.EntityType;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod.EventBusSubscriber(modid = RefinedForage.MOD_ID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ClientSetup {
    private static final Logger LOGGER = LogManager.getLogger();

    public static void init(final FMLClientSetupEvent clientSetupEvent) {
     ScreenManager.<IncineratorContainer, GeneratorScreen<IncineratorContainer>>registerFactory((ContainerType<IncineratorContainer>) Registration.INCINERATOR_GENERATOR.get("container").get(),
                (container, inv, name) -> new GeneratorScreen<>(container, inv, name, new ResourceLocation(RefinedForage.MOD_ID, "textures/gui/slot_generator_gui.png")));
        ScreenManager.<SlotlessGeneratorContainer, GeneratorScreen<SlotlessGeneratorContainer>>registerFactory((ContainerType<SlotlessGeneratorContainer>) Registration.PELTIER_GENERATOR.get("container").get(),
                (container, inv, name) -> new GeneratorScreen<>(container, inv, name, new ResourceLocation(RefinedForage.MOD_ID, "textures/gui/generator_gui.png")));

    }
}