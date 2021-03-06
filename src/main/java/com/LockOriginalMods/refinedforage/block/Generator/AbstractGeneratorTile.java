package com.LockOriginalMods.refinedforage.block.Generator;

import com.LockOriginalMods.refinedforage.Registration;
import com.LockOriginalMods.refinedforage.network.EnergySyncPacket;
import com.LockOriginalMods.refinedforage.network.PacketHandler;
import com.google.common.collect.ArrayListMultimap;

import net.minecraft.block.BlockState;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.play.server.SUpdateTileEntityPacket;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.energy.IEnergyStorage;
import net.minecraftforge.fml.network.PacketDistributor;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.concurrent.atomic.AtomicInteger;

public abstract class AbstractGeneratorTile extends TileEntity implements ITickableTileEntity {
    private static final Logger LOGGER = LogManager.getLogger();
    /**
     * This mutlimap represents an array of actions (Character) that correspond with a given tick (Long).
     * Allows for easy scheduling based on ticks.
     */
    protected ArrayListMultimap<Long, Character> tickQueue = ArrayListMultimap.create();
    protected long tickCount;

    protected PowerEnergy energyStorage = createEnergy();
    protected ItemStackHandler itemHandler = (ItemStackHandler) createHandler();

    protected LazyOptional<IEnergyStorage> energy = LazyOptional.of(() -> energyStorage);
    protected LazyOptional<IItemHandler> handler = LazyOptional.of(() -> itemHandler);

    public AbstractGeneratorTile(TileEntityType<?> tileEntityType) {
        super(tileEntityType);
        tickCount = 0;
    }

    protected abstract int getMaxTransfer();
    protected abstract int getCapacity();
    protected abstract IItemHandler createHandler();

    @Override
    public void remove() {
        super.remove();
        energy.invalidate();
    }

    public long getTickCount() {
        return tickCount;
    }

    @Override
    public void tick() {
        if(world.isRemote) {
            return;
        }
        sendOutPower();
        tickCount++;
    }

    protected void sendOutPower() {
        AtomicInteger capacity = new AtomicInteger(energyStorage.getEnergyStored());
        if (capacity.get() > 0) {
            for (Direction direction : Direction.values()) {
                TileEntity te = world.getTileEntity(pos.offset(direction));
                if (te != null) {
                    boolean doContinue = te.getCapability(CapabilityEnergy.ENERGY, direction).map(handler -> {
                                if (handler.canReceive()) {
                                    int received = handler.receiveEnergy(Math.min(capacity.get(), getMaxTransfer()), false);
                                    capacity.addAndGet(-received);
                                    energyStorage.consumeEnergy(received);
                                    markDirty();
                                    return capacity.get() > 0;
                                } else {
                                    return true;
                                }
                            }
                    ).orElse(true);
                    if (!doContinue) {
                        return;
                    }
                }
            }
        }
    }


    public void generatePower(int power){
        int productionUpgrades = 0;
        for(int i = 0; i <= 2; i++) {// Checks for Production Upgrades
            if(itemHandler.getStackInSlot(i).getItem() == Registration.PRODUCTION_UPGRADE.get()) {
                productionUpgrades++;
            }
        }
        int remainingCapacity = energyStorage.getMaxEnergyStored() - energyStorage.getEnergyStored();
        int producedPower = Math.min(power + (productionUpgrades * power), remainingCapacity);
        energyStorage.addEnergy(producedPower);
        LOGGER.debug("{} energy created at ({}, {}, {})", Integer.toString(producedPower), Integer.toString(getPos().getX()), Integer.toString(getPos().getY()), Integer.toString(getPos().getZ()));
    }
    private void syncEnergy() {
        if(this.world != null && !this.world.isRemote())
            PacketHandler.CHANNEL.send(PacketDistributor.TRACKING_CHUNK.with(() -> this.world.getChunkAt(this.pos)), new EnergySyncPacket(this.pos, energyStorage.getEnergyStored()));
    }
    public void recieveSyncEnergy(int energy) {
        energyStorage.setEnergy(energy);
    }

    @Override
    public void markDirty() {
        if (energyStorage.getEnergyStored() > energyStorage.getMaxEnergyStored()){
            energyStorage.setEnergy(energyStorage.getMaxEnergyStored());
        }
        syncEnergy();
        super.markDirty();
    }

    @Override
    public void read(BlockState state, CompoundNBT tag) {
        energyStorage.deserializeNBT(tag.getCompound("energy"));
        itemHandler.deserializeNBT(tag.getCompound("inv"));
        super.read(state, tag);
    }

    @Override
    public CompoundNBT write(CompoundNBT tag) {
        tag.put("energy", energyStorage.serializeNBT());
        tag.put("inv", itemHandler.serializeNBT());
        return super.write(tag);
    }

    private PowerEnergy createEnergy() {
        return new PowerEnergy(getCapacity(), getMaxTransfer()) {
            @Override
            protected void onEnergyChanged() {
                markDirty();
            }

            @Override
            public boolean canReceive() {
                return false;
            }
        };
    }

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
        if (cap == CapabilityEnergy.ENERGY) {
            return energy.cast();
        }
        if (cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
            return handler.cast();
        }
        syncEnergy();
        return super.getCapability(cap, side);
    }
}