package com.LockOriginalMods.refinedforage.common.data;

import com.LockOriginalMods.refinedforage.Config;
import com.LockOriginalMods.refinedforage.RefinedForage;


import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;

import static com.LockOriginalMods.refinedforage.ModRegistry.B_HANDLE;
import static com.LockOriginalMods.refinedforage.ModRegistry.T_GRINDER;

public class TileGrinder extends TileEntity implements INamedContainerProvider, ITickableTileEntity, IInventory {

  private static final int MULT_OF_MAX_STAGE_BREAKSTUFF = 4;
  public static final String NBTINV = "inv";
  ItemStackHandler inputSlots = new ItemStackHandler(1);
  ItemStackHandler outputSlots = new ItemStackHandler(1);
  private ItemStackHandlerWrapper inventory = new ItemStackHandlerWrapper(inputSlots, outputSlots);
  private LazyOptional<IItemHandler> inventoryCap = LazyOptional.of(() -> inventory);
  private int stage = 0;
  private int timer = 0;
  private int emptyHits = 0;

  public TileGrinder() {
    super(T_GRINDER);
  }

  @Override
  public void tick() {
    timer--;
    if (timer < 0) {
      timer = 0;
    }
    //do we process
    if (canProcessOre()) {
      this.doProcess();
    }
  }

  public boolean canProcessOre() {
    return stage == Config.MAX_STAGE.get();
  }

  private void doProcess() {
    stage = 0;
    ItemStack input = this.inputSlots.getStackInSlot(0);
    if (input.isEmpty()) {
      return;
    }
    GrindRecipe currentRecipe = this.findMatchingRecipe();
    if (currentRecipe != null && this.tryProcessRecipe(currentRecipe)) {
      //we did it
      //pay all costs, RF etc
      if (level.isClientSide == false) {
        //server so process
        this.inputSlots.getStackInSlot(0).shrink(1);
        //and then insert it for real 
        this.outputSlots.insertItem(0, currentRecipe.assemble(this), false);
        //and sound on the trigger
        level.levelEvent((PlayerEntity) null, 1042, worldPosition, 0);
      }
    }
  }

  private boolean tryProcessRecipe(GrindRecipe currentRecipe) {
    // ok so do the thing
    ItemStack result = currentRecipe.assemble(this);
    //does it match? does it fit into the output slot 
    //insert in simulate mode. does it fit?
    if (this.outputSlots.insertItem(0, result, true).isEmpty()) {
      return true;
    }
    return false;
  }

  private GrindRecipe findMatchingRecipe() {
    for (GrindRecipe rec : GrindRecipe.RECIPES) {
      if (rec.matches(this, level)) {
        return rec;
      }
    }
    return null;
  }

  @Override
  public void load(BlockState bs, CompoundNBT tag) {
    inventory.deserializeNBT(tag.getCompound(NBTINV));
    stage = tag.getInt("grindstage");
    timer = tag.getInt("timer");
    emptyHits = tag.getInt("emptyHits");
    super.load(bs, tag);
  }

  @Override
  public CompoundNBT save(CompoundNBT tag) {
    tag.put(NBTINV, inventory.serializeNBT());
    tag.putInt("grindstage", stage);
    tag.putInt("timer", timer);
    tag.putInt("emptyHits", emptyHits);
    return super.save(tag);
  }

  @Override
  public <T> LazyOptional<T> getCapability(Capability<T> cap, Direction side) {
    if (cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY
        && Config.AUTOMATION_ALLOWED.get()) {
      return inventoryCap.cast();
    }
    return super.getCapability(cap, side);
  }

  @Override
  public ITextComponent getDisplayName() {
    return new StringTextComponent(getType().getRegistryName().getPath());
  }

  @Override
  public Container createMenu(int i, PlayerInventory playerInventory, PlayerEntity playerEntity) {
    return new ContainerGrinder(i, level, worldPosition, playerInventory, playerEntity);
  }

  public void incrementGrind() {
    timer = Config.TIMER_COOLDOWN.get(); //restart to allow another rotation
    stage++;
    if (stage > Config.MAX_STAGE.get()) {
      stage = Config.MAX_STAGE.get();
    }
    if (this.inputIsEmpty()) {
      //only track empty if its breakable
      this.emptyHits++;
      if (Config.BREAKABLE_HANDLE.get() &&
          this.emptyHits > Config.MAX_STAGE.get() * MULT_OF_MAX_STAGE_BREAKSTUFF) {
        this.breakHandleAboveMe();
      }
    }
    else {
      this.emptyHits = 0;
    }
  }

  private void breakHandleAboveMe() {
    BlockState state = level.getBlockState(worldPosition.above());
    if (state.getBlock() == B_HANDLE) {
      level.destroyBlock(worldPosition.above(), true);
      this.emptyHits = 0;
    }
  }

  private boolean inputIsEmpty() {
    return this.inputSlots.getStackInSlot(0).isEmpty();
  }

  public boolean canGrind() {
    return timer == 0;
  }

  /******** Fakeout stuff for IRecipe *********************/
  @Override
  public void clearContent() {
    // TODO Auto-generated method stub
  }

  @Override
  public ItemStack removeItem(int arg0, int arg1) {
    return ItemStack.EMPTY;
  }

  @Override
  public int getContainerSize() {
    return 0;
  }

  @Override
  public ItemStack getItem(int arg0) {
    return ItemStack.EMPTY;
  }

  @Override
  public boolean isEmpty() {
    return false;
  }

  @Override
  public boolean stillValid(PlayerEntity arg0) {
    return true;
  }

  @Override
  public ItemStack removeItemNoUpdate(int arg0) {
    return ItemStack.EMPTY;
  }

  @Override
  public void setItem(int arg0, ItemStack arg1) {}
}
