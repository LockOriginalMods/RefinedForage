package com.LockOriginalMods.refinedforage.common.data;

import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import static com.LockOriginalMods.refinedforage.ModRegistry.B_GRINDER;
import static com.LockOriginalMods.refinedforage.ModRegistry.B_HANDLE;

public class GrindEvents {

  @SubscribeEvent
  public void onHit(PlayerInteractEvent.RightClickBlock event) {
    PlayerEntity player = event.getPlayer();
    ItemStack held = player.getItemInHand(event.getHand());
    World world = player.getCommandSenderWorld();
    if (!held.isEmpty() || event.getHand() == Hand.OFF_HAND) {
      return;
    }
    BlockPos pos = event.getPos();
    BlockState state = world.getBlockState(pos);
    if (state.getBlock() == B_HANDLE) {
      //unmapped rotate function
      //    state = state.cycle(BlockStateProperties.HORIZONTAL_FACING);
      // problem 1: its too fast
      //problem 2: should be 4x4 base to centralize
      BlockState below = world.getBlockState(pos.below());
      if (below.getBlock() == B_GRINDER) {
        //do the thing
        TileGrinder tile = (TileGrinder) world.getBlockEntity(pos.below());
        if (tile.canGrind()) {
          //can we?
          // and state
          if (world.isClientSide == false) {
            Direction old = state.getValue(BlockStateProperties.HORIZONTAL_FACING);
            world.setBlockAndUpdate(pos, state.setValue(BlockStateProperties.HORIZONTAL_FACING, old.getCounterClockWise()));
            tile.incrementGrind();
          }
          player.swing(event.getHand());
        }
      }
    }
  }
}

