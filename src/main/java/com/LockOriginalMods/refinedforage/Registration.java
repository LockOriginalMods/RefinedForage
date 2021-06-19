package com.LockOriginalMods.refinedforage;

import com.LockOriginalMods.refinedforage.block.Generator.*;

import net.minecraft.block.Block;
import net.minecraft.entity.EntityType;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.common.extensions.IForgeContainerType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.network.IContainerFactory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public class Registration {
    private static final Logger LOGGER = LogManager.getLogger();

    static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, RefinedForage.MOD_ID);
    static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, RefinedForage.MOD_ID);
    static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(ForgeRegistries.ENTITIES, RefinedForage.MOD_ID);
    static final DeferredRegister<TileEntityType<?>> TILE_ENTITIES = DeferredRegister.create(ForgeRegistries.TILE_ENTITIES, RefinedForage.MOD_ID);
    static final DeferredRegister<ContainerType<?>> CONTAINERS = DeferredRegister.create(ForgeRegistries.CONTAINERS, RefinedForage.MOD_ID);

    public static void init() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        ITEMS.register(modEventBus);
        BLOCKS.register(modEventBus);
        ENTITIES.register(modEventBus);
        TILE_ENTITIES.register(modEventBus);
        CONTAINERS.register(modEventBus);




        LOGGER.debug("Hello from OddPower Registration");
    }

    /* Generators Todo add tooltip for stored energy for item blocks */
       public static final Map<String, RegistryObject<?>> INCINERATOR_GENERATOR = registerGenerator("incinerator_generator", IncineratorGenerator::new, IncineratorGeneratorTile::new,
            ((windowId, inv, data) -> new IncineratorContainer((ContainerType<?>) Registration.INCINERATOR_GENERATOR.get("container").get(), windowId, data.readBlockPos(), inv, (Block) Registration.INCINERATOR_GENERATOR.get("block").get())));
  public static final RegistryObject<UpgradeItem> CAPACITY_UPGRADE = ITEMS.register("capacity_upgrade",
            () -> new UpgradeItem(new Item.Properties().group(RefinedForage.REFINEDFORAGE_GROUP)));
    public static final RegistryObject<UpgradeItem> PRODUCTION_UPGRADE = ITEMS.register("production_upgrade",
            () -> new UpgradeItem(new Item.Properties().group(RefinedForage.REFINEDFORAGE_GROUP)));
    public static final Map<String, RegistryObject<?>> PELTIER_GENERATOR = registerGenerator("peltier_generator", PeltierGenerator::new, PeltierGeneratorTile::new,
            ((windowId, inv, data) -> new SlotlessGeneratorContainer((ContainerType<?>) Registration.PELTIER_GENERATOR.get("container").get(), windowId, data.readBlockPos(), inv, (Block) Registration.PELTIER_GENERATOR.get("block").get())));

    public static <B extends Block, T extends TileEntity, C extends Container> Map<String, RegistryObject<?>> registerGenerator(String name, Supplier<B> blockSupplier, Supplier<T> tileSupplier, IContainerFactory<C> containerSupplier) {
        Map<String, RegistryObject<?>> map = new HashMap<>();
        map.put("block", BLOCKS.register(name, blockSupplier));
        map.put("tile", TILE_ENTITIES.register(name+"_tile", () -> TileEntityType.Builder.create(tileSupplier, (Block) map.get("block").get()).build(null)));
        map.put("blockItem", ITEMS.register(name, () -> new BlockItem((Block) map.get("block").get(), new Item.Properties().group(RefinedForage.REFINEDFORAGE_GROUP))));
        map.put("container", CONTAINERS.register(name, () -> IForgeContainerType.create(containerSupplier)));
        return map;
    }
}