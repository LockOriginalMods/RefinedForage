package com.LockOriginalMods.refinedforage.procedures;

import com.LockOriginalMods.refinedforage.RefinedForage;
import com.LockOriginalMods.refinedforage.RefinedforageModElements;
import net.minecraftforge.energy.CapabilityEnergy;

import net.minecraft.world.World;
import net.minecraft.world.IWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.Direction;
import net.minecraft.tileentity.TileEntity;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.Map;

@RefinedforageModElements.ModElement.Tag
public class SolaradvancedUpdateTickProcedure extends RefinedforageModElements.ModElement {
    public SolaradvancedUpdateTickProcedure(RefinedforageModElements instance) {
        super(instance, 26);
    }

    public static void executeProcedure(Map<String, Object> dependencies) {
        if (dependencies.get("x") == null) {
            if (!dependencies.containsKey("x"))
                RefinedForage.LOGGER.warn("Failed to load dependency x for procedure SolaradvancedUpdateTick!");
            return;
        }
        if (dependencies.get("y") == null) {
            if (!dependencies.containsKey("y"))
                RefinedForage.LOGGER.warn("Failed to load dependency y for procedure SolaradvancedUpdateTick!");
            return;
        }
        if (dependencies.get("z") == null) {
            if (!dependencies.containsKey("z"))
                RefinedForage.LOGGER.warn("Failed to load dependency z for procedure SolaradvancedUpdateTick!");
            return;
        }
        if (dependencies.get("world") == null) {
            if (!dependencies.containsKey("world"))
                RefinedForage.LOGGER.warn("Failed to load dependency world for procedure SolaradvancedUpdateTick!");
            return;
        }
        double x = dependencies.get("x") instanceof Integer ? (int) dependencies.get("x") : (double) dependencies.get("x");
        double y = dependencies.get("y") instanceof Integer ? (int) dependencies.get("y") : (double) dependencies.get("y");
        double z = dependencies.get("z") instanceof Integer ? (int) dependencies.get("z") : (double) dependencies.get("z");
        IWorld world = (IWorld) dependencies.get("world");
        if ((((world.canBlockSeeSky(new BlockPos((int) x, (int) y, (int) z))) && ((world instanceof World) ? ((World) world).isDaytime() : false))
                && (new Object() {
            public boolean canReceiveEnergy(IWorld world, BlockPos pos) {
                AtomicBoolean _retval = new AtomicBoolean(false);
                TileEntity _ent = world.getTileEntity(pos);
                if (_ent != null)
                    _ent.getCapability(CapabilityEnergy.ENERGY, Direction.UP).ifPresent(capability -> _retval.set(capability.canReceive()));
                return _retval.get();
            }
        }.canReceiveEnergy(world, new BlockPos((int) x, (int) (y - 1), (int) z))))) {
            {
                TileEntity _ent = world.getTileEntity(new BlockPos((int) x, (int) (y - 1), (int) z));
                int _amount = (int) 15;
                if (_ent != null)
                    _ent.getCapability(CapabilityEnergy.ENERGY, Direction.UP).ifPresent(capability -> capability.receiveEnergy(_amount, false));
            }
        }
    }
}

