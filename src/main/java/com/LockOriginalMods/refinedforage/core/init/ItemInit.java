package com.LockOriginalMods.refinedforage.core.init;

import com.LockOriginalMods.refinedforage.RefinedForage;
import com.LockOriginalMods.refinedforage.common.items.ArmorTier;
import com.LockOriginalMods.refinedforage.common.items.BatteryItem;

import com.LockOriginalMods.refinedforage.common.items.CopperBow;
import com.LockOriginalMods.refinedforage.common.items.ItemTier;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.*;

import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

import static com.LockOriginalMods.refinedforage.RefinedForage.REFINEDFORAGE_GROUP;

public class ItemInit {

    public static final List<Supplier<Item>> BLOCK_ENTRIES = new ArrayList<>();
    public static final Map<RegistryObject<Item>, Supplier<Item>> ENTRIES = new LinkedHashMap<>();

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, RefinedForage.MOD_ID);

    public static void init() {
        ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
    }

    public static final RegistryObject<Item> COPPER_INGOT = ITEMS.register("copper_ingot", ()
            -> new Item(new Item.Properties().tab(REFINEDFORAGE_GROUP)));

    public static final RegistryObject<BlockItem> FORESTWILDBERRIES_BUSH = ITEMS.register("forestwildberries_bush",
            () -> new BlockItem(BlockInit.FORESTWILDBERRIES_BUSH.get(), new Item.Properties().tab(REFINEDFORAGE_GROUP).food(FoodInit.WILDBERRIES)));

    public static final RegistryObject<Item> TRIDENT_STICK = ITEMS.register("trident_stick", ()
            -> new Item(new Item.Properties().tab(REFINEDFORAGE_GROUP)));

    public static final RegistryObject<Item> TRIDENT_TOP = ITEMS.register("trident_top", ()
            -> new Item(new Item.Properties().tab(REFINEDFORAGE_GROUP)));

    public static final RegistryObject<Item> GOLDEN_CHERRY = ITEMS.register("golden_cherry", ()
            -> new Item(new Item.Properties().tab(REFINEDFORAGE_GROUP).food(FoodInit.GOLDEN_CHERRY)));

    public static final RegistryObject<Item> CHERRY = ITEMS.register("cherry", ()
            -> new Item(new Item.Properties().tab(REFINEDFORAGE_GROUP).food(FoodInit.CHERRY)));

    public static final RegistryObject<BatteryItem> BATTERY = ITEMS.register("battery", BatteryItem::new);

    public static final RegistryObject<Item> SOLAR_CELL = ITEMS.register("solar_cell", ()
            -> new Item(new Item.Properties().tab(REFINEDFORAGE_GROUP)));

    public static final RegistryObject<Item> SOLARIDUSTRIAL_CELL = ITEMS.register("solarindustrial_cell", ()
            -> new Item(new Item.Properties().tab(REFINEDFORAGE_GROUP)));

    public static final RegistryObject<Item> SOLARADVANCED_CELL = ITEMS.register("solaradvanced_cell", ()
            -> new Item(new Item.Properties().tab(REFINEDFORAGE_GROUP)));

    public static final RegistryObject<Item> SOLARULTIMATE_CELL = ITEMS.register("solarultimate_cell", ()
            -> new Item(new Item.Properties().tab(REFINEDFORAGE_GROUP)));

    public static final RegistryObject<Item> SOLARQuantum_CELL = ITEMS.register("solarquantum_cell", ()
            -> new Item(new Item.Properties().tab(REFINEDFORAGE_GROUP)));

    public static final RegistryObject<Item> SOLARSpectral_CELL = ITEMS.register("solarspectral_cell", ()
            -> new Item(new Item.Properties().tab(REFINEDFORAGE_GROUP)));

    public static final RegistryObject<Item> COPPER_WIRE = ITEMS.register("copper_wire", ()
            -> new Item(new Item.Properties().tab(REFINEDFORAGE_GROUP)));

    public static final RegistryObject<Item> BRONZE_INGOT = ITEMS.register("bronze_ingot", ()
            -> new Item(new Item.Properties().tab(REFINEDFORAGE_GROUP)));

    public static final RegistryObject<Item> LEAD_INGOT = ITEMS.register("lead_ingot", ()
            -> new Item(new Item.Properties().tab(REFINEDFORAGE_GROUP)));

    public static final RegistryObject<Item> SILVER_INGOT = ITEMS.register("silver_ingot", ()
            -> new Item(new Item.Properties().tab(REFINEDFORAGE_GROUP)));

    public static final RegistryObject<Item> TIN_INGOT = ITEMS.register("tin_ingot", ()
            -> new Item(new Item.Properties().tab(REFINEDFORAGE_GROUP)));

    public static final RegistryObject<Item> STEEL_INGOT = ITEMS.register("steel_ingot", ()
            -> new Item(new Item.Properties().tab(REFINEDFORAGE_GROUP)));

    public static final RegistryObject<BlockItem> SLATE = ITEMS.register("slate",
            () -> new BlockItem(BlockInit.SLATE.get(), new Item.Properties().tab(REFINEDFORAGE_GROUP)));

    public static final RegistryObject<BlockItem> SLATE_BRICK = ITEMS.register("slate_brick",
            () -> new BlockItem(BlockInit.SLATE_BRICK.get(), new Item.Properties().tab(REFINEDFORAGE_GROUP)));

    public static final RegistryObject<BlockItem> SLATE_SILVER = ITEMS.register("slate_silver",
            () -> new BlockItem(BlockInit.SLATE_SILVER.get(), new Item.Properties().tab(REFINEDFORAGE_GROUP)));

    public static final RegistryObject<BlockItem> SLATE_TIN = ITEMS.register("slate_tin",
            () -> new BlockItem(BlockInit.SLATE_TIN.get(), new Item.Properties().tab(REFINEDFORAGE_GROUP)));

    public static final RegistryObject<BlockItem> SLATE_COPPER = ITEMS.register("slate_copper",
            () -> new BlockItem(BlockInit.SLATE_COPPER.get(), new Item.Properties().tab(REFINEDFORAGE_GROUP)));

    public static final RegistryObject<BlockItem> SLATE_LEAD = ITEMS.register("slate_lead",
            () -> new BlockItem(BlockInit.SLATE_LEAD.get(), new Item.Properties().tab(REFINEDFORAGE_GROUP)));

    public static final RegistryObject<BlockItem> COBBLED_SLATE = ITEMS.register("cobbled_slate",
            () -> new BlockItem(BlockInit.COBBLED_SLATE.get(), new Item.Properties().tab(REFINEDFORAGE_GROUP)));

    private static <T extends Item> RegistryObject<T> registerItem(Supplier<? extends T> item, String name) {
        return ITEMS.register(name, item);
    }

    public static final RegistryObject<Item> coal_grit = ITEMS.register("coal_grit", ()
            -> new Item(new Item.Properties().tab(REFINEDFORAGE_GROUP)));

    public static final RegistryObject<Item> copper_grit = ITEMS.register("copper_grit", ()
            -> new Item(new Item.Properties().tab(REFINEDFORAGE_GROUP)));

    public static final RegistryObject<Item> tin_grit = ITEMS.register("tin_grit", ()
            -> new Item(new Item.Properties().tab(REFINEDFORAGE_GROUP)));

    public static final RegistryObject<Item> steel_grit = ITEMS.register("steel_grit", ()
            -> new Item(new Item.Properties().tab(REFINEDFORAGE_GROUP)));

    public static final RegistryObject<Item> bronze_grit = ITEMS.register("bronze_grit", ()
            -> new Item(new Item.Properties().tab(REFINEDFORAGE_GROUP)));

    public static final RegistryObject<Item> iron_grit = ITEMS.register("iron_grit", ()
            -> new Item(new Item.Properties().tab(REFINEDFORAGE_GROUP)));

    public static final RegistryObject<Item> gold_grit = ITEMS.register("gold_grit", ()
            -> new Item(new Item.Properties().tab(REFINEDFORAGE_GROUP)));

    public static final RegistryObject<Item> electrical_circuit = ITEMS.register("electrical_circuit", ()
            -> new Item(new Item.Properties().tab(REFINEDFORAGE_GROUP)));

    public static final RegistryObject<Item> Advanced_electricalcircuit = ITEMS.register("advanced_electricalcircuit", ()
            -> new Item(new Item.Properties().tab(REFINEDFORAGE_GROUP)));

    //plate
    public static final RegistryObject<Item> STEEL_PLATE = ITEMS.register("steel_plate", ()
            -> new Item(new Item.Properties().tab(REFINEDFORAGE_GROUP)));

    public static final RegistryObject<Item> COPPER_PLATE = ITEMS.register("copper_plate", ()
            -> new Item(new Item.Properties().tab(REFINEDFORAGE_GROUP)));

    public static final RegistryObject<Item> BRONZE_PLATE = ITEMS.register("bronze_plate", ()
            -> new Item(new Item.Properties().tab(REFINEDFORAGE_GROUP)));

    public static final RegistryObject<Item> DIAMOND_PLATE = ITEMS.register("diamond_plate", ()
            -> new Item(new Item.Properties().tab(REFINEDFORAGE_GROUP)));

    public static final RegistryObject<Item> GOLD_PLATE = ITEMS.register("gold_plate", ()
            -> new Item(new Item.Properties().tab(REFINEDFORAGE_GROUP)));

    public static final RegistryObject<Item> IRON_PLATE = ITEMS.register("iron_plate", ()
            -> new Item(new Item.Properties().tab(REFINEDFORAGE_GROUP)));

    public static final RegistryObject<Item> LEAD_PLATE = ITEMS.register("lead_plate", ()
            -> new Item(new Item.Properties().tab(REFINEDFORAGE_GROUP)));

    public static final RegistryObject<Item> SILVER_PLATE = ITEMS.register("silver_plate", ()
            -> new Item(new Item.Properties().tab(REFINEDFORAGE_GROUP)));

    public static final RegistryObject<Item> TIN_PLATE = ITEMS.register("tin_plate", ()
            -> new Item(new Item.Properties().tab(REFINEDFORAGE_GROUP)));

    public static final RegistryObject<Item> COPPERWIRE_COIL = ITEMS.register("copperwire_coil", ()
            -> new Item(new Item.Properties().tab(REFINEDFORAGE_GROUP)));


    public static final RegistryObject<BlockItem> WOODWORKING_TABLE = ITEMS.register("woodworking_table",
            () -> new BlockItem(BlockInit.WOODWORKING_TABLE.get(), new Item.Properties().tab(REFINEDFORAGE_GROUP)));

    public static final RegistryObject<Item> BLUE_CRAFTING = ITEMS.register("blue_crafting", () -> new BlockItem(BlockInit.BLUE_CRAFTING.get(), new Item.Properties().tab(REFINEDFORAGE_GROUP)));


    //Armor
    public static final RegistryObject<Item> COPPER_HELMET = ITEMS.register("copper_helmet", ()
            -> new ArmorItem(ArmorTier.COPPER, EquipmentSlotType.HEAD, new Item.Properties().tab(REFINEDFORAGE_GROUP)));

    public static final RegistryObject<Item> COPPER_CHESTPLATE = ITEMS.register("copper_chestplate", ()
            -> new ArmorItem(ArmorTier.COPPER, EquipmentSlotType.CHEST, new Item.Properties().tab(REFINEDFORAGE_GROUP)));

    public static final RegistryObject<Item> COPPER_LEGGINGS = ITEMS.register("copper_leggings", ()
            -> new ArmorItem(ArmorTier.COPPER, EquipmentSlotType.LEGS, new Item.Properties().tab(REFINEDFORAGE_GROUP)));

    public static final RegistryObject<Item> COPPER_BOOTS = ITEMS.register("copper_boots", ()
            -> new ArmorItem(ArmorTier.COPPER, EquipmentSlotType.FEET, new Item.Properties().tab(REFINEDFORAGE_GROUP)));

    //Tools
    public static final RegistryObject<Item> COPPER_SWORD = ITEMS.register("copper_sword", ()
            -> new SwordItem(ItemTier.COPPER, 2, -1.4F, new Item.Properties().tab(REFINEDFORAGE_GROUP)));

    public static final RegistryObject<Item> COPPER_PICKAXE = ITEMS.register("copper_pickaxe", ()
            -> new PickaxeItem(ItemTier.COPPER, 1, -1.8F, new Item.Properties().tab(REFINEDFORAGE_GROUP)));

    public static final RegistryObject<Item> COPPER_HOE = ITEMS.register("copper_hoe", ()
            -> new HoeItem(ItemTier.COPPER, -1, -0.5F, new Item.Properties().tab(REFINEDFORAGE_GROUP)));

    public static final RegistryObject<Item> COPPER_AXE = ITEMS.register("copper_axe", ()
            -> new AxeItem(ItemTier.COPPER, 5.0F, -1.4F, new Item.Properties().tab(REFINEDFORAGE_GROUP)));

    public static final RegistryObject<Item> COPPER_SHOVEL = ITEMS.register("copper_shovel", ()
            -> new ShovelItem(ItemTier.COPPER, 1.5F, -3.0F, new Item.Properties().tab(REFINEDFORAGE_GROUP)));

    public static final RegistryObject<Item> fine_sands = ITEMS.register("fine_sands", ()
            -> new Item(new Item.Properties().tab(REFINEDFORAGE_GROUP)));

    public static final RegistryObject<Item> water_filter = ITEMS.register("water_filter", ()
            -> new Item(new Item.Properties().tab(REFINEDFORAGE_GROUP)));

    public static final RegistryObject<Item> spool = ITEMS.register("spool", ()
            -> new Item(new Item.Properties().tab(REFINEDFORAGE_GROUP)));

    public static final RegistryObject<Item> bronze_gear = ITEMS.register("bronze_gear", ()
            -> new Item(new Item.Properties().tab(REFINEDFORAGE_GROUP)));

    public static final RegistryObject<Item> copper_gear = ITEMS.register("copper_gear", ()
            -> new Item(new Item.Properties().tab(REFINEDFORAGE_GROUP)));

    public static final RegistryObject<Item> silver_gear = ITEMS.register("silver_gear", ()
            -> new Item(new Item.Properties().tab(REFINEDFORAGE_GROUP)));

    public static final RegistryObject<Item> tin_gear = ITEMS.register("tin_gear", ()
            -> new Item(new Item.Properties().tab(REFINEDFORAGE_GROUP)));

    public static final RegistryObject<Item> steel_gear = ITEMS.register("steel_gear", ()
            -> new Item(new Item.Properties().tab(REFINEDFORAGE_GROUP)));

    public static final RegistryObject<Item> iron_gear = ITEMS.register("iron_gear", ()
            -> new Item(new Item.Properties().tab(REFINEDFORAGE_GROUP)));

    public static final RegistryObject<Item> gold_gear = ITEMS.register("gold_gear", ()
            -> new Item(new Item.Properties().tab(REFINEDFORAGE_GROUP)));

    public static final RegistryObject<Item> lead_gear = ITEMS.register("lead_gear", ()
            -> new Item(new Item.Properties().tab(REFINEDFORAGE_GROUP)));

    public static final RegistryObject<Item> lead_dust = ITEMS.register("lead_dust", ()
            -> new Item(new Item.Properties().tab(REFINEDFORAGE_GROUP)));

    //nugget
    public static final RegistryObject<Item> bronze_nugget = ITEMS.register("bronze_nugget", ()
            -> new Item(new Item.Properties().tab(REFINEDFORAGE_GROUP)));

    public static final RegistryObject<Item> copper_nugget = ITEMS.register("copper_nugget", ()
            -> new Item(new Item.Properties().tab(REFINEDFORAGE_GROUP)));

    public static final RegistryObject<Item> lead_nugget = ITEMS.register("lead_nugget", ()
            -> new Item(new Item.Properties().tab(REFINEDFORAGE_GROUP)));

    public static final RegistryObject<Item> silver_nugget = ITEMS.register("silver_nugget", ()
            -> new Item(new Item.Properties().tab(REFINEDFORAGE_GROUP)));

    public static final RegistryObject<Item> tin_nugget = ITEMS.register("tin_nugget", ()
            -> new Item(new Item.Properties().tab(REFINEDFORAGE_GROUP)));

    public static final RegistryObject<Item> steel_nugget = ITEMS.register("steel_nugget", ()
            -> new Item(new Item.Properties().tab(REFINEDFORAGE_GROUP)));

    public static final RegistryObject<CopperBow> copper_bow = ITEMS.register("copper_bow",
            () -> new CopperBow(new Item.Properties().durability(900)
                    .tab(REFINEDFORAGE_GROUP)));

    public static final RegistryObject<BlockItem> mechanism_housing = ITEMS.register("mechanism_housing", ()
            -> new BlockItem(BlockInit.mechanism_housing.get(), new Item.Properties().tab(REFINEDFORAGE_GROUP)));
}





