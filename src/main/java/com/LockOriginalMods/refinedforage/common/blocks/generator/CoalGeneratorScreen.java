package com.LockOriginalMods.refinedforage.common.blocks.generator;

import com.LockOriginalMods.refinedforage.RefinedForage;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ColorHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CoalGeneratorScreen extends ContainerScreen<CoalGeneratorContainer> {
    private static final Logger LOGGER = LogManager.getLogger();
    private ResourceLocation GUI = new ResourceLocation(RefinedForage.MOD_ID, "textures/gui/slot_generator_gui.png");

    public CoalGeneratorScreen(CoalGeneratorContainer container, PlayerInventory inv, ITextComponent name) {
        super(container, inv, name);
    }

    @Override
    public void render(MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks) {
        this.renderBackground(matrixStack);
        super.render(matrixStack, mouseX, mouseY, partialTicks);
        this.renderTooltip(matrixStack, mouseX, mouseY);
    }
    @Override
    protected void renderLabels(MatrixStack matrixStack, int x, int y) {
        drawEnergyBar(matrixStack, 53, 25, 69, 73);

        int guiX = x - getGuiLeft(), guiY = y - getGuiTop();
        if(53 <= guiX && guiX <= 69 && 13 <= guiY && guiY <= 65) {// Tooltip to display specific amount of power when hovering over bar
            renderTooltip(matrixStack, new StringTextComponent(menu.getEnergy() + "/" + menu.getMaxEnergy() + " FE"), guiX, guiY);
        }
    }

    @SuppressWarnings("deprecation")
    @Override
    protected void renderBg(MatrixStack matrixStack, float partialTicks, int x, int y) {
        RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.minecraft.getTextureManager().bind(GUI);
        int relX = (this.width - this.imageWidth) / 2;
        int relY = (this.height - this.imageHeight) / 2;
        this.blit(matrixStack, relX, relY, 0, 0, this.imageWidth, this.imageHeight);
    }

    /**
     * Draws a two color bar designed for displaying energy
     */
    private void drawEnergyBar(MatrixStack matrixStack, int minX, int minY, int maxX, int maxY) {
        int energyHeight;
        try {
            energyHeight = Math.floorDiv((maxY - minY) * menu.getEnergy(), menu.getMaxEnergy());
        } catch(ArithmeticException e) {
            LOGGER.warn("GUI cannot render because " + e.getMessage());
            return;
        }

        int color1Amount = 3;
        int color2Amount = 2;
        int color1 = ColorHelper.PackedColor.color(255, 220, 0, 0);
        int color2 = ColorHelper.PackedColor.color(255, 150, 0, 0);

        int i = 0;
        boolean primaryColor = true;
        while(i < energyHeight) {
            if(i + (primaryColor ? color1Amount : color2Amount) > energyHeight) {
                fill(matrixStack, minX, maxY - energyHeight, maxX, maxY - i, primaryColor ? color1 : color2);
                break;
            }
            fill(matrixStack, minX, maxY - (i + (primaryColor ? color1Amount : color2Amount)), maxX, maxY - i, primaryColor ? color1 : color2);
            i += primaryColor ? color1Amount : color2Amount;
            primaryColor = !primaryColor;
        }
    }
}
