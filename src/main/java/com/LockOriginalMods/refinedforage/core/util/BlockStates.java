package com.LockOriginalMods.refinedforage.core.util;


import com.LockOriginalMods.refinedforage.RefinedForage;
import com.LockOriginalMods.refinedforage.Registration;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.data.DataGenerator;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.generators.*;
import net.minecraftforge.common.data.ExistingFileHelper;

import java.util.function.Function;

public class BlockStates extends BlockStateProvider {

    public BlockStates(DataGenerator gen, ExistingFileHelper exFileHelper) {
        super(gen, RefinedForage.MOD_ID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        simpleBlock(Registration.COAL_GENERATOR.get());
        registerFirstBlock();
        registerComplexMultipart();
    }

    private void registerFirstBlock() {
        ResourceLocation txt = new ResourceLocation(RefinedForage.MOD_ID, "block/coal_generator");
        BlockModelBuilder modelCoalGenerator = models().cube("coal_generator", txt, txt, new ResourceLocation(RefinedForage.MOD_ID, "block/coal_generator_front"), txt, txt, txt);
        BlockModelBuilder modelCoalGeneratorPowered = models().cube("coal_generator_powered", txt, txt, new ResourceLocation(RefinedForage.MOD_ID, "block/coal_generator_powered"), txt, txt, txt);
        orientedBlock(Registration.COAL_GENERATOR.get(), state -> {
            if (state.getValue(BlockStateProperties.POWERED)) {
                return modelCoalGeneratorPowered;
            } else {
                return modelCoalGenerator;
            }
        });
    }



    private void registerComplexMultipart() {
        BlockModelBuilder dimCellFrame = models().getBuilder("block/complex/main");

        floatingCube(dimCellFrame, 0f, 0f, 0f, 1f, 16f, 1f);
        floatingCube(dimCellFrame, 15f, 0f, 0f, 16f, 16f, 1f);
        floatingCube(dimCellFrame, 0f, 0f, 15f, 1f, 16f, 16f);
        floatingCube(dimCellFrame, 15f, 0f, 15f, 16f, 16f, 16f);

        floatingCube(dimCellFrame, 1f, 0f, 0f, 15f, 1f, 1f);
        floatingCube(dimCellFrame, 1f, 15f, 0f, 15f, 16f, 1f);
        floatingCube(dimCellFrame, 1f, 0f, 15f, 15f, 1f, 16f);
        floatingCube(dimCellFrame, 1f, 15f, 15f, 15f, 16f, 16f);

        floatingCube(dimCellFrame, 0f, 0f, 1f, 1f, 1f, 15f);
        floatingCube(dimCellFrame, 15f, 0f, 1f, 16f, 1f, 15f);
        floatingCube(dimCellFrame, 0f, 15f, 1f, 1f, 16f, 15f);
        floatingCube(dimCellFrame, 15f, 15f, 1f, 16f, 16f, 15f);

        floatingCube(dimCellFrame, 1f, 1f, 1f, 15f, 15f, 15f);

        dimCellFrame.texture("window", modLoc("block/complex_window"));


    }

    private void floatingCube(BlockModelBuilder builder, float fx, float fy, float fz, float tx, float ty, float tz) {
        builder.element().from(fx, fy, fz).to(tx, ty, tz).allFaces((direction, faceBuilder) -> faceBuilder.texture("#window")).end();
    }

    private void createDimensionalCellModel(Block block, BlockModelBuilder dimCellFrame) {
        BlockModelBuilder singleNone = models().getBuilder("block/complex/singlenone")
                .element().from(3, 3, 3).to(13, 13, 13).face(Direction.DOWN).texture("#single").end().end()
                .texture("single", modLoc("block/complex"));
        BlockModelBuilder singleIn = models().getBuilder("block/complex/singlein")
                .element().from(3, 3, 3).to(13, 13, 13).face(Direction.DOWN).texture("#single").end().end()
                .texture("single", modLoc("block/complex_in"));
        BlockModelBuilder singleOut = models().getBuilder("block/complex/singleout")
                .element().from(3, 3, 3).to(13, 13, 13).face(Direction.DOWN).texture("#single").end().end()
                .texture("single", modLoc("block/complex_out"));

        MultiPartBlockStateBuilder bld = getMultipartBuilder(block);

        bld.part().modelFile(dimCellFrame).addModel();

        BlockModelBuilder[] models = new BlockModelBuilder[] { singleNone, singleIn, singleOut };

        }


    private void orientedBlock(Block block, Function<BlockState, ModelFile> modelFunc) {
        getVariantBuilder(block)
                .forAllStates(state -> {
                    Direction dir = state.getValue(BlockStateProperties.FACING);
                    return ConfiguredModel.builder()
                            .modelFile(modelFunc.apply(state))
                            .rotationX(dir.getAxis() == Direction.Axis.Y ?  dir.getAxisDirection().getStep() * -90 : 0)
                            .rotationY(dir.getAxis() != Direction.Axis.Y ? ((dir.get2DDataValue() + 2) % 4) * 90 : 0)
                            .build();
                });
    }

}
