package com.LockOriginalMods.refinedforage.block;

import com.LockOriginalMods.refinedforage.RefinedForage;
import com.LockOriginalMods.refinedforage.RefinedforageModElements;
import com.LockOriginalMods.refinedforage.procedures.SolarPanelBasicUpdateTickProcedure;
import net.minecraftforge.registries.ObjectHolder;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.world.server.ServerWorld;
import net.minecraft.world.World;
import net.minecraft.world.IBlockReader;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.BlockPos;
import net.minecraft.loot.LootContext;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.Item;
import net.minecraft.item.BlockItem;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.block.material.Material;
import net.minecraft.block.SoundType;
import net.minecraft.block.BlockState;
import net.minecraft.block.Block;


import java.util.Random;
import java.util.Map;
import java.util.List;
import java.util.HashMap;
import java.util.Collections;

@RefinedforageModElements.ModElement.Tag
public class SolarBasicBlock extends RefinedforageModElements.ModElement {
    @ObjectHolder("refinedforage:solar_basic")
    public static final Block block = null;
    public SolarBasicBlock(RefinedforageModElements instance) {
        super(instance, 23);
    }

    @Override
    public void initElements() {
        elements.blocks.add(() -> new CustomBlock());
        elements.items
                .add(() -> new BlockItem(block, new Item.Properties().group(RefinedForage.REFINEDFORAGE_GROUP)).setRegistryName(block.getRegistryName()));
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void clientLoad(FMLClientSetupEvent event) {
        RenderTypeLookup.setRenderLayer(block, RenderType.getCutout());
    }
    public static class CustomBlock extends Block {
        public CustomBlock() {
            super(Block.Properties.create(Material.ROCK).sound(SoundType.STONE).hardnessAndResistance(1f, 10f).setLightLevel(s -> 0).notSolid()
                    .setOpaque((bs, br, bp) -> false));
            setRegistryName("solar_basic");
        }

        @Override
        public boolean propagatesSkylightDown(BlockState state, IBlockReader reader, BlockPos pos) {
            return true;
        }

        @Override
        public VoxelShape getShape(BlockState state, IBlockReader world, BlockPos pos, ISelectionContext context) {
            Vector3d offset = state.getOffset(world, pos);
            return VoxelShapes.or(makeCuboidShape(0, 0, 0, 16, 1, 16)).withOffset(offset.x, offset.y, offset.z);
        }

        @Override
        public List<ItemStack> getDrops(BlockState state, LootContext.Builder builder) {
            List<ItemStack> dropsOriginal = super.getDrops(state, builder);
            if (!dropsOriginal.isEmpty())
                return dropsOriginal;
            return Collections.singletonList(new ItemStack(this, 1));
        }

        @Override
        public void onBlockAdded(BlockState state, World world, BlockPos pos, BlockState oldState, boolean moving) {
            super.onBlockAdded(state, world, pos, oldState, moving);
            int x = pos.getX();
            int y = pos.getY();
            int z = pos.getZ();
            world.getPendingBlockTicks().scheduleTick(new BlockPos(x, y, z), this, 1);
        }

        @Override
        public void tick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
            super.tick(state, world, pos, random);
            int x = pos.getX();
            int y = pos.getY();
            int z = pos.getZ();
            {
                Map<String, Object> $_dependencies = new HashMap<>();
                $_dependencies.put("x", x);
                $_dependencies.put("y", y);
                $_dependencies.put("z", z);
                $_dependencies.put("world", world);
                SolarPanelBasicUpdateTickProcedure.executeProcedure($_dependencies);
            }
            world.getPendingBlockTicks().scheduleTick(new BlockPos(x, y, z), this, 1);
        }
    }
}
