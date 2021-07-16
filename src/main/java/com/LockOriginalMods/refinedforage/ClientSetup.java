package com.LockOriginalMods.refinedforage;


import com.LockOriginalMods.refinedforage.block.Generator.GeneratorScreen;
import com.LockOriginalMods.refinedforage.block.Generator.IncineratorContainer;

import com.LockOriginalMods.refinedforage.block.Generator.SlotlessGeneratorContainer;
import com.LockOriginalMods.refinedforage.common.blocks.generator.CoalGeneratorContainer;
import com.LockOriginalMods.refinedforage.common.blocks.generator.CoalGeneratorScreen;
import net.minecraft.client.gui.ScreenManager;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RenderTooltipEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


@Mod.EventBusSubscriber(modid = RefinedForage.MOD_ID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ClientSetup {
    private static final Logger LOGGER = LogManager.getLogger();

    public static void init(final FMLClientSetupEvent event) {

     ScreenManager.<IncineratorContainer, GeneratorScreen<IncineratorContainer>>register((ContainerType<IncineratorContainer>) Registration.INCINERATOR_GENERATOR.get("container").get(),
                (container, inv, name) -> new GeneratorScreen<>(container, inv, name, new ResourceLocation(RefinedForage.MOD_ID, "textures/gui/slot_generator_gui.png")));
        ScreenManager.<SlotlessGeneratorContainer, GeneratorScreen<SlotlessGeneratorContainer>>register((ContainerType<SlotlessGeneratorContainer>) Registration.PELTIER_GENERATOR.get("container").get(),
                (container, inv, name) -> new GeneratorScreen<>(container, inv, name, new ResourceLocation(RefinedForage.MOD_ID, "textures/gui/generator_gui.png")));

        ScreenManager.register(Registration.COAL_GENERATOR_CONTAINER.get(), CoalGeneratorScreen::new);

        event.enqueueWork(() -> {
        });
    }
    @SubscribeEvent
    public void onTooltipPre(RenderTooltipEvent.Pre event) {
        Item item = event.getStack().getItem();
        if (item.getRegistryName().getNamespace().equals(RefinedForage.MOD_ID)) {
            event.setMaxWidth(200);
        }

    }

}

