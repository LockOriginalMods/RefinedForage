package com.LockOriginalMods.refinedforage.procedures;

import com.LockOriginalMods.refinedforage.RefinedForage;
import com.LockOriginalMods.refinedforage.RefinedforageModElements;
import net.minecraftforge.energy.CapabilityEnergy;

import net.minecraft.world.IWorld;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.Direction;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.Entity;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.Map;

@RefinedforageModElements.ModElement.Tag
public class EnergyMeterRightClickedOnBlockProcedure extends RefinedforageModElements.ModElement {
    public EnergyMeterRightClickedOnBlockProcedure(RefinedforageModElements instance) {
        super(instance, 6);
    }

    public static void executeProcedure(Map<String, Object> dependencies) {
        if (dependencies.get("direction") == null) {
            if (!dependencies.containsKey("direction"))
                RefinedForage.LOGGER.warn("Failed to load dependency direction for procedure EnergyMeterRightClickedOnBlock!");
            return;
        }
        if (dependencies.get("entity") == null) {
            if (!dependencies.containsKey("entity"))
                RefinedForage.LOGGER.warn("Failed to load dependency entity for procedure EnergyMeterRightClickedOnBlock!");
            return;
        }
        if (dependencies.get("x") == null) {
            if (!dependencies.containsKey("x"))
                RefinedForage.LOGGER.warn("Failed to load dependency x for procedure EnergyMeterRightClickedOnBlock!");
            return;
        }
        if (dependencies.get("y") == null) {
            if (!dependencies.containsKey("y"))
                RefinedForage.LOGGER.warn("Failed to load dependency y for procedure EnergyMeterRightClickedOnBlock!");
            return;
        }
        if (dependencies.get("z") == null) {
            if (!dependencies.containsKey("z"))
                RefinedForage.LOGGER.warn("Failed to load dependency z for procedure EnergyMeterRightClickedOnBlock!");
            return;
        }
        if (dependencies.get("world") == null) {
            if (!dependencies.containsKey("world"))
                RefinedForage.LOGGER.warn("Failed to load dependency world for procedure EnergyMeterRightClickedOnBlock!");
            return;
        }
        Direction direction = (Direction) dependencies.get("direction");
        Entity entity = (Entity) dependencies.get("entity");
        double x = dependencies.get("x") instanceof Integer ? (int) dependencies.get("x") : (double) dependencies.get("x");
        double y = dependencies.get("y") instanceof Integer ? (int) dependencies.get("y") : (double) dependencies.get("y");
        double z = dependencies.get("z") instanceof Integer ? (int) dependencies.get("z") : (double) dependencies.get("z");
        IWorld world = (IWorld) dependencies.get("world");
        if (entity instanceof PlayerEntity && !entity.world.isRemote()) {
            ((PlayerEntity) entity).sendStatusMessage(new StringTextComponent((((new Object() {
                public int getEnergyStored(IWorld world, BlockPos pos) {
                    AtomicInteger _retval = new AtomicInteger(0);
                    TileEntity _ent = world.getTileEntity(pos);
                    if (_ent != null)
                        _ent.getCapability(CapabilityEnergy.ENERGY, direction).ifPresent(capability -> _retval.set(capability.getEnergyStored()));
                    return _retval.get();
                }
            }.getEnergyStored(world, new BlockPos((int) x, (int) y, (int) z)))) + "" + ("/") + "" + ((new Object() {
                public int getMaxEnergyStored(IWorld world, BlockPos pos) {
                    AtomicInteger _retval = new AtomicInteger(0);
                    TileEntity _ent = world.getTileEntity(pos);
                    if (_ent != null)
                        _ent.getCapability(CapabilityEnergy.ENERGY, direction).ifPresent(capability -> _retval.set(capability.getMaxEnergyStored()));
                    return _retval.get();
                }
            }.getMaxEnergyStored(world, new BlockPos((int) x, (int) y, (int) z)))) + "" + ("energy at") + "" + (direction))), (true));
        }
    }
}
