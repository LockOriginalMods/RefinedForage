package com.LockOriginalMods.refinedforage;

import com.LockOriginalMods.refinedforage.core.init.BlockInit;
import com.LockOriginalMods.refinedforage.core.init.FeatureInit;
import com.LockOriginalMods.refinedforage.core.init.ItemInit;
import com.LockOriginalMods.refinedforage.core.init.VillagerInit;
import com.LockOriginalMods.refinedforage.network.ClientProxy;
import com.LockOriginalMods.refinedforage.network.CommonProxy;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.EntityType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.network.NetworkRegistry;
import net.minecraftforge.fml.network.simple.SimpleChannel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Random;
import java.util.function.Supplier;


@Mod("refinedforage")
public class RefinedForage
{

    public static final String MOD_ID = "refinedforage";
    public static final String NAME = "refinedforage";
    public static final Logger LOGGER = LogManager.getLogger(RefinedForage.class);
    private static final String PROTOCOL_VERSION = "1";
    public static CommonProxy PROXY;
    public static final SimpleChannel PACKET_HANDLER = NetworkRegistry.newSimpleChannel(new ResourceLocation("refinedforage", "refinedforage"),
            () -> PROTOCOL_VERSION, PROTOCOL_VERSION::equals, PROTOCOL_VERSION::equals);
    public RefinedforageModElements elements;
    public static final ItemGroup REFINEDFORAGE_GROUP = new RefinedForageGroup("refinedforagetab");

    public RefinedForage() {

        elements = new RefinedforageModElements();
        FMLJavaModLoadingContext.get().getModEventBus().register(this);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::init);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::clientLoad);
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        bus.addListener(this::setup);
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        MinecraftForge.EVENT_BUS.register(this);
        MinecraftForge.EVENT_BUS.addListener(EventPriority.HIGH, FeatureInit::addOres);
        ItemInit.init();
        BlockInit.init();
        Registration.init();
        PROXY = DistExecutor.safeRunForDist(() -> ClientProxy::new, () -> CommonProxy::new);
        ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, Config.CLIENT_CONFIG);
        ModLoadingContext.get().registerConfig(ModConfig.Type.SERVER, Config.SERVER_CONFIG);
        VillagerInit.VILLAGER_PROFESSIONS.register(bus);
        VillagerInit.POINT_OF_INTEREST_TYPES.register(bus);
        final ModLoadingContext modLoadingContext = ModLoadingContext.get();

        FMLJavaModLoadingContext.get().getModEventBus().addListener(ClientSetup::init);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);

    }

    private void setup(final FMLCommonSetupEvent event)
    {
        RenderTypeLookup.setRenderLayer(BlockInit.FORESTWILDBERRIES_BUSH.get(), RenderType.cutout());
    }
    private void init(FMLCommonSetupEvent event) {
        elements.getElements().forEach(element -> element.init(event));
    }

    public void clientLoad(FMLClientSetupEvent event) {
        elements.getElements().forEach(element -> element.clientLoad(event));
    }

    @SubscribeEvent
    public void registerBlocks(RegistryEvent.Register<Block> event) {
        event.getRegistry().registerAll(elements.getBlocks().stream().map(Supplier::get).toArray(Block[]::new));
    }

    @SubscribeEvent
    public void registerItems(RegistryEvent.Register<Item> event) {
        event.getRegistry().registerAll(elements.getItems().stream().map(Supplier::get).toArray(Item[]::new));
    }

    @SubscribeEvent
    public void registerEntities(RegistryEvent.Register<EntityType<?>> event) {
        event.getRegistry().registerAll(elements.getEntities().stream().map(Supplier::get).toArray(EntityType[]::new));
    }

    @SubscribeEvent
    public void registerEnchantments(RegistryEvent.Register<Enchantment> event) {
        event.getRegistry().registerAll(elements.getEnchantments().stream().map(Supplier::get).toArray(Enchantment[]::new));
    }

    @SubscribeEvent
    public void registerSounds(RegistryEvent.Register<net.minecraft.util.SoundEvent> event) {
        elements.registerSounds(event);
    }
    private static class RefinedforageModFMLBusEvents {
        private final RefinedForage parent;
        RefinedforageModFMLBusEvents(RefinedForage parent) {
            this.parent = parent;
        }

        @SubscribeEvent
        public void serverLoad(FMLServerStartingEvent event) {
            this.parent.elements.getElements().forEach(element -> element.serverLoad(event));
        }
    }
    public static ResourceLocation getId(String path) {
        return new ResourceLocation(MOD_ID, path);
    }

    public static class RefinedForageGroup extends ItemGroup {

        public RefinedForageGroup(String label) {
            super(label);
        }

        @Override
        public ItemStack makeIcon() {
            return ItemInit.COBBLED_SLATE.get().getDefaultInstance();
        }


    }

}
