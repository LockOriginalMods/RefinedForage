package com.LockOriginalMods.refinedforage.core.init;

import com.LockOriginalMods.refinedforage.RefinedForage;
import com.LockOriginalMods.refinedforage.block.Generator.CraftingBlock;
import com.LockOriginalMods.refinedforage.common.blocks.ModSaplingBlock;
import com.LockOriginalMods.refinedforage.common.blocks.WildberriesBush;
import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.ArrayList;
import java.util.List;

public class BlockInit {

    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, RefinedForage.MOD_ID);

    public static void init() {
        BLOCKS.register(FMLJavaModLoadingContext.get().getModEventBus());
    }

    public static final RegistryObject<Block> FORESTWILDBERRIES_BUSH = BLOCKS.register("forestwildberries_bush", ()
            -> new WildberriesBush(AbstractBlock.Properties.from(Blocks.SWEET_BERRY_BUSH)));

    public static final RegistryObject<Block> SLATE = BLOCKS.register("slate", ()
            -> new Block(AbstractBlock.Properties.create(Material.ROCK, MaterialColor.GRAY).hardnessAndResistance(1.5f, 6.0f)
            .harvestTool(ToolType.PICKAXE)
            .harvestLevel(0)
            .sound(SoundType.STONE)));

    public static final RegistryObject<Block> SLATE_BRICK = BLOCKS.register("slate_brick", ()
            -> new Block(AbstractBlock.Properties.create(Material.ROCK, MaterialColor.GRAY).hardnessAndResistance(1.5f, 6.0f)
            .harvestTool(ToolType.PICKAXE)
            .harvestLevel(0)
            .sound(SoundType.STONE)));

    public static final RegistryObject<Block> SLATE_COPPER = BLOCKS.register("slate_copper", ()
            -> new Block(AbstractBlock.Properties.from(Blocks.IRON_ORE)));

    public static final RegistryObject<Block> SLATE_TIN = BLOCKS.register("slate_tin", ()
            -> new Block(AbstractBlock.Properties.from(Blocks.IRON_ORE)));

    public static final RegistryObject<Block> SLATE_SILVER = BLOCKS.register("slate_silver", ()
            -> new Block(AbstractBlock.Properties.from(Blocks.IRON_ORE)));

    public static final RegistryObject<Block> SLATE_LEAD = BLOCKS.register("slate_lead", ()
            -> new Block(AbstractBlock.Properties.from(Blocks.IRON_ORE)));

    public static final RegistryObject<Block> COBBLED_SLATE = BLOCKS.register("cobbled_slate", ()
            -> new Block(AbstractBlock.Properties.from(Blocks.IRON_ORE)));

    public static final RegistryObject<Block> WOODWORKING_TABLE = BLOCKS.register("woodworking_table", ()
            -> new CraftingBlock(AbstractBlock.Properties.from(net.minecraft.block.Blocks.CRAFTING_TABLE)));

    public static final RegistryObject<Block> BLUE_CRAFTING = BLOCKS.register("blue_crafting", ()
            -> new CraftingBlock(AbstractBlock.Properties.from(net.minecraft.block.Blocks.CRAFTING_TABLE)));
}

