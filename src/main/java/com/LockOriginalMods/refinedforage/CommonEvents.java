package com.LockOriginalMods.refinedforage;

import com.LockOriginalMods.refinedforage.core.init.VillagerInit;

import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

@Mod.EventBusSubscriber(modid = RefinedForage.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class CommonEvents {
    @SubscribeEvent
    public static void commonSetup(FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {
            VillagerInit.fillTradeData();
            VillagerInit.registerPOI();
            VillagerInit.regPOI();

    });
  }
}