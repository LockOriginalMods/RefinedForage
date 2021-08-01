package com.LockOriginalMods.refinedforage.core.init;

import com.LockOriginalMods.refinedforage.RefinedForage;

import com.LockOriginalMods.refinedforage.block.Generator.CraftingBlock;
import com.LockOriginalMods.refinedforage.common.blocks.WildberriesBush;
import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.common.extensions.IForgeContainerType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Supplier;
import java.util.function.ToIntFunction;

import static com.LockOriginalMods.refinedforage.RefinedForage.MOD_ID;
import static com.LockOriginalMods.refinedforage.RefinedForage.REFINEDFORAGE_GROUP;


public class BlockInit {

    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, MOD_ID);

    public static void init() {
        BLOCKS.register(FMLJavaModLoadingContext.get().getModEventBus());
    }
    public static final Map<RegistryObject<Block>, Supplier<Block>> ENTRIES = new LinkedHashMap<>();

    public static final RegistryObject<Block> FORESTWILDBERRIES_BUSH = BLOCKS.register("forestwildberries_bush", ()
            -> new WildberriesBush(AbstractBlock.Properties.copy(Blocks.SWEET_BERRY_BUSH)));

    public static final RegistryObject<Block> SLATE = BLOCKS.register("slate", ()
            -> new Block(AbstractBlock.Properties.of(Material.STONE, MaterialColor.COLOR_GRAY).strength(1.5f, 6.0f)
            .harvestTool(ToolType.PICKAXE)
            .harvestLevel(0)
            .sound(SoundType.STONE)));

    public static final RegistryObject<Block> SLATE_BRICK = BLOCKS.register("slate_brick", ()
            -> new Block(AbstractBlock.Properties.of(Material.STONE, MaterialColor.COLOR_GRAY).strength(1.5f, 6.0f)
            .harvestTool(ToolType.PICKAXE)
            .harvestLevel(0)
            .sound(SoundType.STONE)));

    public static final RegistryObject<Block> SLATE_COPPER = BLOCKS.register("slate_copper", ()
            -> new Block(AbstractBlock.Properties.copy(Blocks.IRON_ORE)));

    public static final RegistryObject<Block> SLATE_TIN = BLOCKS.register("slate_tin", ()
            -> new Block(AbstractBlock.Properties.copy(Blocks.IRON_ORE)));

    public static final RegistryObject<Block> SLATE_SILVER = BLOCKS.register("slate_silver", ()
            -> new Block(AbstractBlock.Properties.copy(Blocks.IRON_ORE)));

    public static final RegistryObject<Block> SLATE_LEAD = BLOCKS.register("slate_lead", ()
            -> new Block(AbstractBlock.Properties.copy(Blocks.IRON_ORE)));

    public static final RegistryObject<Block> COBBLED_SLATE = BLOCKS.register("cobbled_slate", ()
            -> new Block(AbstractBlock.Properties.copy(Blocks.IRON_ORE)));

    public static final RegistryObject<Block> WOODWORKING_TABLE = BLOCKS.register("woodworking_table", ()
            -> new CraftingBlock(AbstractBlock.Properties.copy(net.minecraft.block.Blocks.CRAFTING_TABLE)));

    public static final RegistryObject<Block> BLUE_CRAFTING = BLOCKS.register("blue_crafting", ()
            -> new CraftingBlock(AbstractBlock.Properties.copy(net.minecraft.block.Blocks.CRAFTING_TABLE)));

    public static final RegistryObject<Block> mechanism_housing = BLOCKS.register("mechanism_housing", ()
            ->  new Block(AbstractBlock.Properties.of(Material.METAL, MaterialColor.METAL).strength(5.0F, 6.0F).sound(SoundType.METAL)));

}


