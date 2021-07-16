package com.LockOriginalMods.refinedforage.procedures;

import com.LockOriginalMods.refinedforage.RefinedForage;
import com.LockOriginalMods.refinedforage.RefinedforageModElements;
import net.minecraftforge.energy.CapabilityEnergy;

import net.minecraft.world.IWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.Direction;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.state.EnumProperty;
import net.minecraft.state.DirectionProperty;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Entity;
import net.minecraft.block.BlockState;


import java.util.function.Function;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.Map;
import java.util.Comparator;

@RefinedforageModElements.ModElement.Tag
public class FANUpdateTickProcedure extends RefinedforageModElements.ModElement {
    public FANUpdateTickProcedure(RefinedforageModElements instance) {
        super(instance, 5);
    }

    public static void executeProcedure(Map<String, Object> dependencies) {
        if (dependencies.get("x") == null) {
            if (!dependencies.containsKey("x"))
                RefinedForage.LOGGER.warn("Failed to load dependency x for procedure FANUpdateTick!");
            return;
        }
        if (dependencies.get("y") == null) {
            if (!dependencies.containsKey("y"))
                RefinedForage.LOGGER.warn("Failed to load dependency y for procedure FANUpdateTick!");
            return;
        }
        if (dependencies.get("z") == null) {
            if (!dependencies.containsKey("z"))
                RefinedForage.LOGGER.warn("Failed to load dependency z for procedure FANUpdateTick!");
            return;
        }
        if (dependencies.get("world") == null) {
            if (!dependencies.containsKey("world"))
                RefinedForage.LOGGER.warn("Failed to load dependency world for procedure FANUpdateTick!");
            return;
        }
        double x = dependencies.get("x") instanceof Integer ? (int) dependencies.get("x") : (double) dependencies.get("x");
        double y = dependencies.get("y") instanceof Integer ? (int) dependencies.get("y") : (double) dependencies.get("y");
        double z = dependencies.get("z") instanceof Integer ? (int) dependencies.get("z") : (double) dependencies.get("z");
        IWorld world = (IWorld) dependencies.get("world");
        if (((new Object() {
            public int getEnergyStored(IWorld world, BlockPos pos) {
                AtomicInteger _retval = new AtomicInteger(0);
                TileEntity _ent = world.getBlockEntity(pos);
                if (_ent != null)
                    _ent.getCapability(CapabilityEnergy.ENERGY, null).ifPresent(capability -> _retval.set(capability.getEnergyStored()));
                return _retval.get();
            }
        }.getEnergyStored(world, new BlockPos((int) x, (int) y, (int) z))) >= 50)) {
            {
                TileEntity _ent = world.getBlockEntity(new BlockPos((int) x, (int) y, (int) z));
                int _amount = (int) 50;
                if (_ent != null)
                    _ent.getCapability(CapabilityEnergy.ENERGY, null).ifPresent(capability -> capability.extractEnergy(_amount, false));
            }
            if (((new Object() {
                public Direction getDirection(BlockPos pos) {
                    try {
                        BlockState _bs = world.getBlockState(pos);
                        DirectionProperty property = (DirectionProperty) _bs.getBlock().getStateDefinition().getProperty("facing");
                        if (property != null)
                            return _bs.getValue(property);
                        return Direction.fromAxisAndDirection(
                                _bs.getValue((EnumProperty<Direction.Axis>) _bs.getBlock().getStateDefinition().getProperty("axis")),
                                Direction.AxisDirection.POSITIVE);
                    } catch (Exception e) {
                        return Direction.NORTH;
                    }
                }
            }.getDirection(new BlockPos((int) x, (int) y, (int) z))) == Direction.UP)) {
                if ((((Entity) world.getEntitiesOfClass(LivingEntity.class,
                        new AxisAlignedBB(x - (6 / 2d), (y + 3) - (6 / 2d), z - (6 / 2d), x + (6 / 2d), (y + 3) + (6 / 2d), z + (6 / 2d)), null)
                        .stream().sorted(new Object() {
                            Comparator<Entity> compareDistOf(double _x, double _y, double _z) {
                                return Comparator.comparing((Function<Entity, Double>) (_entcnd -> _entcnd.distanceToSqr(_x, _y, _z)));
                            }
                        }.compareDistOf(x, (y + 3), z)).findFirst().orElse(null)) != null)) {
                    ((Entity) world.getEntitiesOfClass(LivingEntity.class,
                            new AxisAlignedBB(x - (6 / 2d), (y + 3) - (6 / 2d), z - (6 / 2d), x + (6 / 2d), (y + 3) + (6 / 2d), z + (6 / 2d)), null)
                            .stream().sorted(new Object() {
                                Comparator<Entity> compareDistOf(double _x, double _y, double _z) {
                                    return Comparator.comparing((Function<Entity, Double>) (_entcnd -> _entcnd.distanceToSqr(_x, _y, _z)));
                                }
                            }.compareDistOf(x, (y + 3), z)).findFirst().orElse(null)).setDeltaMovement(0, 0.2, 0);
                }
                if ((((Entity) world.getEntitiesOfClass(ItemEntity.class,
                        new AxisAlignedBB(x - (6 / 2d), (y + 3) - (6 / 2d), z - (6 / 2d), x + (6 / 2d), (y + 3) + (6 / 2d), z + (6 / 2d)), null)
                        .stream().sorted(new Object() {
                            Comparator<Entity> compareDistOf(double _x, double _y, double _z) {
                                return Comparator.comparing((Function<Entity, Double>) (_entcnd -> _entcnd.distanceToSqr(_x, _y, _z)));
                            }
                        }.compareDistOf(x, (y + 3), z)).findFirst().orElse(null)) != null)) {
                    ((Entity) world.getEntitiesOfClass(ItemEntity.class,
                            new AxisAlignedBB(x - (6 / 2d), (y + 3) - (6 / 2d), z - (6 / 2d), x + (6 / 2d), (y + 3) + (6 / 2d), z + (6 / 2d)), null)
                            .stream().sorted(new Object() {
                                Comparator<Entity> compareDistOf(double _x, double _y, double _z) {
                                    return Comparator.comparing((Function<Entity, Double>) (_entcnd -> _entcnd.distanceToSqr(_x, _y, _z)));
                                }
                            }.compareDistOf(x, (y + 3), z)).findFirst().orElse(null)).setDeltaMovement(0, 0.2, 0);
                }
            } else if (((new Object() {
                public Direction getDirection(BlockPos pos) {
                    try {
                        BlockState _bs = world.getBlockState(pos);
                        DirectionProperty property = (DirectionProperty) _bs.getBlock().getStateDefinition().getProperty("facing");
                        if (property != null)
                            return _bs.getValue(property);
                        return Direction.fromAxisAndDirection(
                                _bs.getValue((EnumProperty<Direction.Axis>) _bs.getBlock().getStateDefinition().getProperty("axis")),
                                Direction.AxisDirection.POSITIVE);
                    } catch (Exception e) {
                        return Direction.NORTH;
                    }
                }
            }.getDirection(new BlockPos((int) x, (int) y, (int) z))) == Direction.DOWN)) {
                if ((((Entity) world.getEntitiesOfClass(LivingEntity.class,
                        new AxisAlignedBB(x - (6 / 2d), (y - 3) - (6 / 2d), z - (6 / 2d), x + (6 / 2d), (y - 3) + (6 / 2d), z + (6 / 2d)), null)
                        .stream().sorted(new Object() {
                            Comparator<Entity> compareDistOf(double _x, double _y, double _z) {
                                return Comparator.comparing((Function<Entity, Double>) (_entcnd -> _entcnd.distanceToSqr(_x, _y, _z)));
                            }
                        }.compareDistOf(x, (y - 3), z)).findFirst().orElse(null)) != null)) {
                    ((Entity) world.getEntitiesOfClass(LivingEntity.class,
                            new AxisAlignedBB(x - (6 / 2d), (y - 3) - (6 / 2d), z - (6 / 2d), x + (6 / 2d), (y - 3) + (6 / 2d), z + (6 / 2d)), null)
                            .stream().sorted(new Object() {
                                Comparator<Entity> compareDistOf(double _x, double _y, double _z) {
                                    return Comparator.comparing((Function<Entity, Double>) (_entcnd -> _entcnd.distanceToSqr(_x, _y, _z)));
                                }
                            }.compareDistOf(x, (y - 3), z)).findFirst().orElse(null)).setDeltaMovement(0, 0.2, 0);
                }
                if ((((Entity) world.getEntitiesOfClass(ItemEntity.class,
                        new AxisAlignedBB(x - (6 / 2d), (y - 3) - (6 / 2d), z - (6 / 2d), x + (6 / 2d), (y - 3) + (6 / 2d), z + (6 / 2d)), null)
                        .stream().sorted(new Object() {
                            Comparator<Entity> compareDistOf(double _x, double _y, double _z) {
                                return Comparator.comparing((Function<Entity, Double>) (_entcnd -> _entcnd.distanceToSqr(_x, _y, _z)));
                            }
                        }.compareDistOf(x, (y - 3), z)).findFirst().orElse(null)) != null)) {
                    ((Entity) world.getEntitiesOfClass(ItemEntity.class,
                            new AxisAlignedBB(x - (6 / 2d), (y - 3) - (6 / 2d), z - (6 / 2d), x + (6 / 2d), (y - 3) + (6 / 2d), z + (6 / 2d)), null)
                            .stream().sorted(new Object() {
                                Comparator<Entity> compareDistOf(double _x, double _y, double _z) {
                                    return Comparator.comparing((Function<Entity, Double>) (_entcnd -> _entcnd.distanceToSqr(_x, _y, _z)));
                                }
                            }.compareDistOf(x, (y - 3), z)).findFirst().orElse(null)).setDeltaMovement(0, 0.2, 0);
                }
            } else if (((new Object() {
                public Direction getDirection(BlockPos pos) {
                    try {
                        BlockState _bs = world.getBlockState(pos);
                        DirectionProperty property = (DirectionProperty) _bs.getBlock().getStateDefinition().getProperty("facing");
                        if (property != null)
                            return _bs.getValue(property);
                        return Direction.fromAxisAndDirection(
                                _bs.getValue((EnumProperty<Direction.Axis>) _bs.getBlock().getStateDefinition().getProperty("axis")),
                                Direction.AxisDirection.POSITIVE);
                    } catch (Exception e) {
                        return Direction.NORTH;
                    }
                }
            }.getDirection(new BlockPos((int) x, (int) y, (int) z))) == Direction.NORTH)) {
                if ((((Entity) world.getEntitiesOfClass(LivingEntity.class,
                        new AxisAlignedBB(x - (6 / 2d), (y - 3) - (6 / 2d), z - (6 / 2d), x + (6 / 2d), (y - 3) + (6 / 2d), z + (6 / 2d)), null)
                        .stream().sorted(new Object() {
                            Comparator<Entity> compareDistOf(double _x, double _y, double _z) {
                                return Comparator.comparing((Function<Entity, Double>) (_entcnd -> _entcnd.distanceToSqr(_x, _y, _z)));
                            }
                        }.compareDistOf(x, (y - 3), z)).findFirst().orElse(null)) != null)) {
                    ((Entity) world.getEntitiesOfClass(LivingEntity.class,
                            new AxisAlignedBB(x - (6 / 2d), (y - 3) - (6 / 2d), z - (6 / 2d), x + (6 / 2d), (y - 3) + (6 / 2d), z + (6 / 2d)), null)
                            .stream().sorted(new Object() {
                                Comparator<Entity> compareDistOf(double _x, double _y, double _z) {
                                    return Comparator.comparing((Function<Entity, Double>) (_entcnd -> _entcnd.distanceToSqr(_x, _y, _z)));
                                }
                            }.compareDistOf(x, (y - 3), z)).findFirst().orElse(null)).setDeltaMovement(0, 0.2, 0);
                }
                if ((((Entity) world.getEntitiesOfClass(ItemEntity.class,
                        new AxisAlignedBB(x - (6 / 2d), (y - 3) - (6 / 2d), z - (6 / 2d), x + (6 / 2d), (y - 3) + (6 / 2d), z + (6 / 2d)), null)
                        .stream().sorted(new Object() {
                            Comparator<Entity> compareDistOf(double _x, double _y, double _z) {
                                return Comparator.comparing((Function<Entity, Double>) (_entcnd -> _entcnd.distanceToSqr(_x, _y, _z)));
                            }
                        }.compareDistOf(x, (y - 3), z)).findFirst().orElse(null)) != null)) {
                    ((Entity) world.getEntitiesOfClass(ItemEntity.class,
                            new AxisAlignedBB(x - (6 / 2d), (y - 3) - (6 / 2d), z - (6 / 2d), x + (6 / 2d), (y - 3) + (6 / 2d), z + (6 / 2d)), null)
                            .stream().sorted(new Object() {
                                Comparator<Entity> compareDistOf(double _x, double _y, double _z) {
                                    return Comparator.comparing((Function<Entity, Double>) (_entcnd -> _entcnd.distanceToSqr(_x, _y, _z)));
                                }
                            }.compareDistOf(x, (y - 3), z)).findFirst().orElse(null)).setDeltaMovement(0, 0.2, 0);
                }
            } else if (((new Object() {
                public Direction getDirection(BlockPos pos) {
                    try {
                        BlockState _bs = world.getBlockState(pos);
                        DirectionProperty property = (DirectionProperty) _bs.getBlock().getStateDefinition().getProperty("facing");
                        if (property != null)
                            return _bs.getValue(property);
                        return Direction.fromAxisAndDirection(
                                _bs.getValue((EnumProperty<Direction.Axis>) _bs.getBlock().getStateDefinition().getProperty("axis")),
                                Direction.AxisDirection.POSITIVE);
                    } catch (Exception e) {
                        return Direction.NORTH;
                    }
                }
            }.getDirection(new BlockPos((int) x, (int) y, (int) z))) == Direction.SOUTH)) {
                if ((((Entity) world.getEntitiesOfClass(LivingEntity.class,
                        new AxisAlignedBB(x - (6 / 2d), (y + 3) - (6 / 2d), z - (6 / 2d), x + (6 / 2d), (y + 3) + (6 / 2d), z + (6 / 2d)), null)
                        .stream().sorted(new Object() {
                            Comparator<Entity> compareDistOf(double _x, double _y, double _z) {
                                return Comparator.comparing((Function<Entity, Double>) (_entcnd -> _entcnd.distanceToSqr(_x, _y, _z)));
                            }
                        }.compareDistOf(x, (y + 3), z)).findFirst().orElse(null)) != null)) {
                    ((Entity) world.getEntitiesOfClass(LivingEntity.class,
                            new AxisAlignedBB(x - (6 / 2d), (y + 3) - (6 / 2d), z - (6 / 2d), x + (6 / 2d), (y + 3) + (6 / 2d), z + (6 / 2d)), null)
                            .stream().sorted(new Object() {
                                Comparator<Entity> compareDistOf(double _x, double _y, double _z) {
                                    return Comparator.comparing((Function<Entity, Double>) (_entcnd -> _entcnd.distanceToSqr(_x, _y, _z)));
                                }
                            }.compareDistOf(x, (y + 3), z)).findFirst().orElse(null)).setDeltaMovement(0, 0.2, 0);
                }
                if ((((Entity) world.getEntitiesOfClass(ItemEntity.class,
                        new AxisAlignedBB(x - (6 / 2d), (y + 3) - (6 / 2d), z - (6 / 2d), x + (6 / 2d), (y + 3) + (6 / 2d), z + (6 / 2d)), null)
                        .stream().sorted(new Object() {
                            Comparator<Entity> compareDistOf(double _x, double _y, double _z) {
                                return Comparator.comparing((Function<Entity, Double>) (_entcnd -> _entcnd.distanceToSqr(_x, _y, _z)));
                            }
                        }.compareDistOf(x, (y + 3), z)).findFirst().orElse(null)) != null)) {
                    ((Entity) world.getEntitiesOfClass(ItemEntity.class,
                            new AxisAlignedBB(x - (6 / 2d), (y + 3) - (6 / 2d), z - (6 / 2d), x + (6 / 2d), (y + 3) + (6 / 2d), z + (6 / 2d)), null)
                            .stream().sorted(new Object() {
                                Comparator<Entity> compareDistOf(double _x, double _y, double _z) {
                                    return Comparator.comparing((Function<Entity, Double>) (_entcnd -> _entcnd.distanceToSqr(_x, _y, _z)));
                                }
                            }.compareDistOf(x, (y + 3), z)).findFirst().orElse(null)).setDeltaMovement(0, 0.2, 0);
                }
            } else if (((new Object() {
                public Direction getDirection(BlockPos pos) {
                    try {
                        BlockState _bs = world.getBlockState(pos);
                        DirectionProperty property = (DirectionProperty) _bs.getBlock().getStateDefinition().getProperty("facing");
                        if (property != null)
                            return _bs.getValue(property);
                        return Direction.fromAxisAndDirection(
                                _bs.getValue((EnumProperty<Direction.Axis>) _bs.getBlock().getStateDefinition().getProperty("axis")),
                                Direction.AxisDirection.POSITIVE);
                    } catch (Exception e) {
                        return Direction.NORTH;
                    }
                }
            }.getDirection(new BlockPos((int) x, (int) y, (int) z))) == Direction.WEST)) {
                if ((((Entity) world.getEntitiesOfClass(LivingEntity.class,
                        new AxisAlignedBB(x - (6 / 2d), (y - 3) - (6 / 2d), z - (6 / 2d), x + (6 / 2d), (y - 3) + (6 / 2d), z + (6 / 2d)), null)
                        .stream().sorted(new Object() {
                            Comparator<Entity> compareDistOf(double _x, double _y, double _z) {
                                return Comparator.comparing((Function<Entity, Double>) (_entcnd -> _entcnd.distanceToSqr(_x, _y, _z)));
                            }
                        }.compareDistOf(x, (y - 3), z)).findFirst().orElse(null)) != null)) {
                    ((Entity) world.getEntitiesOfClass(LivingEntity.class,
                            new AxisAlignedBB(x - (6 / 2d), (y - 3) - (6 / 2d), z - (6 / 2d), x + (6 / 2d), (y - 3) + (6 / 2d), z + (6 / 2d)), null)
                            .stream().sorted(new Object() {
                                Comparator<Entity> compareDistOf(double _x, double _y, double _z) {
                                    return Comparator.comparing((Function<Entity, Double>) (_entcnd -> _entcnd.distanceToSqr(_x, _y, _z)));
                                }
                            }.compareDistOf(x, (y - 3), z)).findFirst().orElse(null)).setDeltaMovement(0, 0.2, 0);
                }
                if ((((Entity) world.getEntitiesOfClass(ItemEntity.class,
                        new AxisAlignedBB(x - (6 / 2d), (y - 3) - (6 / 2d), z - (6 / 2d), x + (6 / 2d), (y - 3) + (6 / 2d), z + (6 / 2d)), null)
                        .stream().sorted(new Object() {
                            Comparator<Entity> compareDistOf(double _x, double _y, double _z) {
                                return Comparator.comparing((Function<Entity, Double>) (_entcnd -> _entcnd.distanceToSqr(_x, _y, _z)));
                            }
                        }.compareDistOf(x, (y - 3), z)).findFirst().orElse(null)) != null)) {
                    ((Entity) world.getEntitiesOfClass(ItemEntity.class,
                            new AxisAlignedBB(x - (6 / 2d), (y - 3) - (6 / 2d), z - (6 / 2d), x + (6 / 2d), (y - 3) + (6 / 2d), z + (6 / 2d)), null)
                            .stream().sorted(new Object() {
                                Comparator<Entity> compareDistOf(double _x, double _y, double _z) {
                                    return Comparator.comparing((Function<Entity, Double>) (_entcnd -> _entcnd.distanceToSqr(_x, _y, _z)));
                                }
                            }.compareDistOf(x, (y - 3), z)).findFirst().orElse(null)).setDeltaMovement(0, 0.2, 0);
                }
            } else if (((new Object() {
                public Direction getDirection(BlockPos pos) {
                    try {
                        BlockState _bs = world.getBlockState(pos);
                        DirectionProperty property = (DirectionProperty) _bs.getBlock().getStateDefinition().getProperty("facing");
                        if (property != null)
                            return _bs.getValue(property);
                        return Direction.fromAxisAndDirection(
                                _bs.getValue((EnumProperty<Direction.Axis>) _bs.getBlock().getStateDefinition().getProperty("axis")),
                                Direction.AxisDirection.POSITIVE);
                    } catch (Exception e) {
                        return Direction.NORTH;
                    }
                }
            }.getDirection(new BlockPos((int) x, (int) y, (int) z))) == Direction.EAST)) {
                if ((((Entity) world.getEntitiesOfClass(LivingEntity.class,
                        new AxisAlignedBB(x - (6 / 2d), (y + 3) - (6 / 2d), z - (6 / 2d), x + (6 / 2d), (y + 3) + (6 / 2d), z + (6 / 2d)), null)
                        .stream().sorted(new Object() {
                            Comparator<Entity> compareDistOf(double _x, double _y, double _z) {
                                return Comparator.comparing((Function<Entity, Double>) (_entcnd -> _entcnd.distanceToSqr(_x, _y, _z)));
                            }
                        }.compareDistOf(x, (y + 3), z)).findFirst().orElse(null)) != null)) {
                    ((Entity) world.getEntitiesOfClass(LivingEntity.class,
                            new AxisAlignedBB(x - (6 / 2d), (y + 3) - (6 / 2d), z - (6 / 2d), x + (6 / 2d), (y + 3) + (6 / 2d), z + (6 / 2d)), null)
                            .stream().sorted(new Object() {
                                Comparator<Entity> compareDistOf(double _x, double _y, double _z) {
                                    return Comparator.comparing((Function<Entity, Double>) (_entcnd -> _entcnd.distanceToSqr(_x, _y, _z)));
                                }
                            }.compareDistOf(x, (y + 3), z)).findFirst().orElse(null)).setDeltaMovement(0, 0.2, 0);
                }
                if ((((Entity) world.getEntitiesOfClass(ItemEntity.class,
                        new AxisAlignedBB(x - (6 / 2d), (y + 3) - (6 / 2d), z - (6 / 2d), x + (6 / 2d), (y + 3) + (6 / 2d), z + (6 / 2d)), null)
                        .stream().sorted(new Object() {
                            Comparator<Entity> compareDistOf(double _x, double _y, double _z) {
                                return Comparator.comparing((Function<Entity, Double>) (_entcnd -> _entcnd.distanceToSqr(_x, _y, _z)));
                            }
                        }.compareDistOf(x, (y + 3), z)).findFirst().orElse(null)) != null)) {
                    ((Entity) world.getEntitiesOfClass(ItemEntity.class,
                            new AxisAlignedBB(x - (6 / 2d), (y + 3) - (6 / 2d), z - (6 / 2d), x + (6 / 2d), (y + 3) + (6 / 2d), z + (6 / 2d)), null)
                            .stream().sorted(new Object() {
                                Comparator<Entity> compareDistOf(double _x, double _y, double _z) {
                                    return Comparator.comparing((Function<Entity, Double>) (_entcnd -> _entcnd.distanceToSqr(_x, _y, _z)));
                                }
                            }.compareDistOf(x, (y + 3), z)).findFirst().orElse(null)).setDeltaMovement(0, 0.2, 0);
                }
            }
        }
    }
}
