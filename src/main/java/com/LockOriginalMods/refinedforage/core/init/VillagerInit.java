package com.LockOriginalMods.refinedforage.core.init;

import com.LockOriginalMods.refinedforage.RefinedForage;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import net.minecraft.entity.Entity;
import net.minecraft.entity.merchant.villager.VillagerProfession;
import net.minecraft.entity.merchant.villager.VillagerTrades;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.MerchantOffer;
import net.minecraft.util.SoundEvents;
import net.minecraft.village.PointOfInterestType;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraft.world.storage.MapDecoration;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import javax.annotation.Nullable;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Random;

public class VillagerInit {
    public static final DeferredRegister<PointOfInterestType> POINT_OF_INTEREST_TYPES = DeferredRegister.
            create(ForgeRegistries.POI_TYPES, RefinedForage.MOD_ID);
    public static final DeferredRegister<VillagerProfession> VILLAGER_PROFESSIONS = DeferredRegister.
            create(ForgeRegistries.PROFESSIONS, RefinedForage.MOD_ID);


    public static final RegistryObject<PointOfInterestType> BOTANIST_POI = POINT_OF_INTEREST_TYPES.register("botanist",
            () -> new PointOfInterestType("botanist", PointOfInterestType.getAllStates(BlockInit.WOODWORKING_TABLE.get()), 1, 1));

    public static final RegistryObject<VillagerProfession> BOTANIST_PROF = VILLAGER_PROFESSIONS.register("botanist",
            () -> new VillagerProfession("botanist", BOTANIST_POI.get(), ImmutableSet.of(), ImmutableSet.of(), SoundEvents.ENTITY_VILLAGER_WORK_FARMER));

    public static void registerPOI(){
        try {
            ObfuscationReflectionHelper.findMethod(PointOfInterestType.class, "registerBlockStates",PointOfInterestType.class).invoke(null, BOTANIST_POI.get());
        }catch (IllegalAccessException | InvocationTargetException e){
            e.printStackTrace();
        }
    }

    public static final RegistryObject<PointOfInterestType> ENGINEER_POI = POINT_OF_INTEREST_TYPES.register("engineer",
            () -> new PointOfInterestType("engineer", PointOfInterestType.getAllStates(BlockInit.BLUE_CRAFTING.get()), 1, 1));

    public static final RegistryObject<VillagerProfession> ENGINEER_PROF = VILLAGER_PROFESSIONS.register("engineer",
            () -> new VillagerProfession("engineer", ENGINEER_POI.get(), ImmutableSet.of(), ImmutableSet.of(), SoundEvents.ENTITY_VILLAGER_WORK_FARMER));

    public static void regPOI(){
        try {
            ObfuscationReflectionHelper.findMethod(PointOfInterestType.class, "registerBlockStates",PointOfInterestType.class).invoke(null, ENGINEER_POI.get());
        }catch (IllegalAccessException | InvocationTargetException e){
            e.printStackTrace();
        }
    }

    public static void fillTradeData() {
        // OCEANOGRAPHER TRADES
        VillagerTrades.ITrade[] Level1 = new VillagerTrades.ITrade[]{
                new VillagerTrades.ItemsForEmeraldsTrade(ItemInit.CHERRY.get(), 9, 3, 10, 2),
                new VillagerTrades.ItemsForEmeraldsTrade(ItemInit.FORESTWILDBERRIES_BUSH.get(), 15, 1, 3,  16)
        };
        VillagerTrades.ITrade[] Level2 = new VillagerTrades.ITrade[]{
                new VillagerTrades.ItemsForEmeraldsTrade(ItemInit.TRIDENT_TOP.get(), 14, 1, 1,  10),
                new VillagerTrades.ItemsForEmeraldsTrade(ItemInit.TRIDENT_STICK.get(),4, 1, 1,  16)
        };
        VillagerTrades.ITrade[] Level3 = new VillagerTrades.ITrade[]{
                new VillagerTrades.ItemsForEmeraldsTrade(Items.APPLE, 3, 1, 19, 20),
                new VillagerTrades.ItemsForEmeraldsTrade(Items.GOLDEN_APPLE, 15, 1, 8,  20),
        };
        VillagerTrades.ITrade[] Level4 = new VillagerTrades.ITrade[]{
                new VillagerTrades.ItemsForEmeraldsTrade(Items.NAUTILUS_SHELL, 3, 4, 8, 12),
                new VillagerTrades.ItemsForEmeraldsTrade(Items.HEART_OF_THE_SEA, 8, 1, 3,  12),
        };
        VillagerTrades.ITrade[] Level5 = new VillagerTrades.ITrade[]{
                new VillagerTrades.ItemsForEmeraldsTrade(Items.TRIDENT, 32, 1, 1,2)
        };
        VillagerTrades.VILLAGER_DEFAULT_TRADES.put(BOTANIST_PROF.get(), toIntMap(ImmutableMap.of(1, Level1, 2, Level2, 3, Level3, 4, Level4, 5, Level5)));

        //engineer
        VillagerTrades.ITrade[] engineerLevel1 = new VillagerTrades.ITrade[]{
                new VillagerTrades.ItemsForEmeraldsTrade(ItemInit.COPPER_PLATE.get(), 6, 2, 100, 9),

        };
                VillagerTrades.ITrade[]engineerLevel2 = new VillagerTrades.ITrade[]{
                new VillagerTrades.ItemsForEmeraldsTrade(ItemInit.IRON_PLATE.get(), 6, 2, 100, 9),
                new VillagerTrades.ItemsForEmeraldsTrade(ItemInit.GOLD_PLATE.get(), 6, 2, 100, 9),
                        new VillagerTrades.ItemsForEmeraldsTrade(ItemInit.TIN_PLATE.get(), 6, 2, 100, 9),
                };
        VillagerTrades.ITrade[] engineerLevel3 = new VillagerTrades.ITrade[]{
                new VillagerTrades.ItemsForEmeraldsTrade(ItemInit.BRONZE_PLATE.get(), 6, 2, 100, 9),
                new VillagerTrades.ItemsForEmeraldsTrade(ItemInit.DIAMOND_PLATE.get(), 6, 2, 100, 9),
        };
        VillagerTrades.ITrade[] engineerLevel4 = new VillagerTrades.ITrade[]{
        new VillagerTrades.ItemsForEmeraldsTrade(ItemInit.SILVER_PLATE.get(), 6, 2, 100, 9),
                new VillagerTrades.ItemsForEmeraldsTrade(ItemInit.LEAD_PLATE.get(), 6, 2, 100, 9),
                        };
                        VillagerTrades.ITrade[] engineerLevel5 = new VillagerTrades.ITrade[]{


                new VillagerTrades.ItemsForEmeraldsTrade(ItemInit.STEEL_PLATE.get(), 6, 2, 100, 9)
        };
           VillagerTrades.VILLAGER_DEFAULT_TRADES.put(ENGINEER_PROF.get(), toIntMap(ImmutableMap.of(1, engineerLevel1, 2, engineerLevel2, 3, engineerLevel3, 4, engineerLevel4, 5, engineerLevel5)));
    }
        private static Int2ObjectMap<VillagerTrades.ITrade[]> toIntMap(ImmutableMap<Integer, VillagerTrades.ITrade[]> p_221238_0_) {
            return new Int2ObjectOpenHashMap<>(p_221238_0_);
        }

}
