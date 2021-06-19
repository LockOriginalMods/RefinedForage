package com.LockOriginalMods.refinedforage.core.init;

import net.minecraft.block.BlockState;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.Features;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import net.minecraft.world.gen.feature.template.RuleTest;
import net.minecraft.world.gen.placement.Placement;
import net.minecraft.world.gen.placement.TopSolidRangeConfig;
import net.minecraftforge.event.world.BiomeLoadingEvent;

public class FeatureInit {

    public static void addOres(final BiomeLoadingEvent event ){

        addOre(event, OreFeatureConfig.FillerBlockType.BASE_STONE_OVERWORLD, BlockInit.SLATE_COPPER.get().getDefaultState(), 4, 0, 60, 20);
        addOre(event, OreFeatureConfig.FillerBlockType.BASE_STONE_OVERWORLD, BlockInit.SLATE_LEAD.get().getDefaultState(), 4, 0, 60, 20);
        addOre(event, OreFeatureConfig.FillerBlockType.BASE_STONE_OVERWORLD, BlockInit.SLATE_SILVER.get().getDefaultState(), 4, 0, 60, 20);
        addOre(event, OreFeatureConfig.FillerBlockType.BASE_STONE_OVERWORLD, BlockInit.SLATE_TIN.get().getDefaultState(), 4, 0, 60, 20);
    }

    public static void addOre(final BiomeLoadingEvent event, RuleTest rule, BlockState state, int veinSize, int minHeight, int maxHeight,
                              int amount){
        event.getGeneration().withFeature(GenerationStage.Decoration.UNDERGROUND_ORES,
                Feature.ORE.withConfiguration(new OreFeatureConfig(rule, state, veinSize))
                        .withPlacement(Placement.RANGE.configure(new TopSolidRangeConfig(minHeight, 0, maxHeight)))
                        .square().count(amount));
    }


}