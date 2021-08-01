package com.LockOriginalMods.refinedforage;

import com.LockOriginalMods.refinedforage.common.data.*;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.registry.Registry;
import net.minecraftforge.common.extensions.IForgeContainerType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.ObjectHolder;

import static com.LockOriginalMods.refinedforage.RefinedForage.MOD_ID;
import static com.LockOriginalMods.refinedforage.RefinedForage.REFINEDFORAGE_GROUP;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModRegistry {
    public static final ItemGroup GROUP = new ItemGroup(MOD_ID) {

        @Override
        public ItemStack makeIcon() {
            return new ItemStack(ModRegistry.B_HANDLE);
        }
    };

    @ObjectHolder(MOD_ID + ":handle")
    public static Block B_HANDLE;
    @ObjectHolder(MOD_ID + ":grinder")
    public static Block B_GRINDER;
    @ObjectHolder(MOD_ID + ":grinder")
    public static ContainerType<ContainerGrinder> CTR_GRINDER;
    @ObjectHolder(MOD_ID + ":grinder")
    public static TileEntityType<TileGrinder> T_GRINDER;

    @SubscribeEvent
    public static void onBlocksRegistry(final RegistryEvent.Register<Block> event) {
        IForgeRegistry<Block> r = event.getRegistry();
        r.register(new BlockGrinder(AbstractBlock.Properties.of(Material.STONE).strength(0.9F)).setRegistryName("grinder"));
        r.register(new BlockHandle(AbstractBlock.Properties.of(Material.WOOD).strength(0.4F)).setRegistryName("handle"));
    }

    @SubscribeEvent
    public static void onTileEntityRegistry(final RegistryEvent.Register<TileEntityType<?>> event) {
        IForgeRegistry<TileEntityType<?>> r = event.getRegistry();
        r.register(TileEntityType.Builder.of(TileGrinder::new, B_GRINDER).build(null).setRegistryName("grinder"));
    }

    @SubscribeEvent
    public static void onContainerRegistry(final RegistryEvent.Register<ContainerType<?>> event) {
        IForgeRegistry<ContainerType<?>> r = event.getRegistry();
        r.register(IForgeContainerType.create((windowId, inv, data) -> {
            return new ContainerGrinder(windowId, inv.player.level, data.readBlockPos(), inv, inv.player);
        }).setRegistryName("grinder"));
    }

    @SubscribeEvent
    public static void onItemsRegistry(final RegistryEvent.Register<Item> event) {
        IForgeRegistry<Item> r = event.getRegistry();
        r.register(new BlockItem(B_GRINDER, new Item.Properties().tab(GROUP)).setRegistryName("grinder"));
        r.register(new BlockItem(B_HANDLE, new Item.Properties().tab(GROUP)).setRegistryName("handle"));

    }

    public static void registerRecipeSerializers(RegistryEvent.Register<IRecipeSerializer<?>> event) {
        Registry.register(Registry.RECIPE_TYPE, ModRecipeType.GRIND.toString(), ModRecipeType.GRIND);
        event.getRegistry().register(GrindRecipe.SERIALIZER);
    }
}
