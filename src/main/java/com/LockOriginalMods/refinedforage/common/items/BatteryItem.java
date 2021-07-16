package com.LockOriginalMods.refinedforage.common.items;

import com.LockOriginalMods.refinedforage.RefinedForage;
import com.LockOriginalMods.refinedforage.procedures.EnergyStoringItem;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.Rarity;



import net.minecraft.item.Item.Properties;

public class BatteryItem extends EnergyStoringItem {
    private static final int MAX_ENERGY = 500_000;
    private static final int MAX_TRANSFER = 500;

    public BatteryItem() {
        super(new Properties().tab(RefinedForage.REFINEDFORAGE_GROUP).stacksTo(1).rarity(Rarity.UNCOMMON), MAX_ENERGY, MAX_TRANSFER);
    }
}