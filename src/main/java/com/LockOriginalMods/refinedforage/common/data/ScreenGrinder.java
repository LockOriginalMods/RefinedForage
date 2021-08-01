package com.LockOriginalMods.refinedforage.common.data;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;

import static com.LockOriginalMods.refinedforage.RefinedForage.MOD_ID;

public class ScreenGrinder extends ContainerScreen<ContainerGrinder> {

  public static final ResourceLocation INVENTORY = new ResourceLocation(MOD_ID, "textures/gui/inventory.png");
  public static final ResourceLocation SLOT = new ResourceLocation(MOD_ID, "textures/gui/slot.png");

  public ScreenGrinder(ContainerGrinder screenContainer, PlayerInventory inv, ITextComponent titleIn) {
    super(screenContainer, inv, titleIn);
  }

  @Override
  public void init() {
    super.init();
  }

  @Override
  protected void renderBg(MatrixStack ms, float partialTicks, int x, int y) {
    //    super.drawGuiContainerForegroundLayer(ms, x, y);
    this.drawBackground(ms, INVENTORY);
  }

  @Override
  public void render(MatrixStack ms, int mouseX, int mouseY, float partialTicks) {
    this.renderBackground(ms);
    super.render(ms, mouseX, mouseY, partialTicks);
    this.renderTooltip(ms, mouseX, mouseY);
  }

  protected void drawBackground(MatrixStack ms, ResourceLocation gui) {
    this.minecraft.getTextureManager().bind(gui);
    int relX = (this.width - this.imageWidth) / 2;
    int relY = (this.height - this.imageHeight) / 2;
    this.blit(ms, relX, relY, 0, 0, this.imageWidth, this.imageHeight);
    //
    this.drawSlot(ms, 54, 34);
    this.drawSlot(ms, 108, 34);
  }

  protected void drawSlot(MatrixStack ms, int x, int y) {
    final int size = 18;
    this.minecraft.getTextureManager().bind(SLOT);
    blit(ms, leftPos + x, topPos + y, 0, 0, size, size, size, size);
  }
}
