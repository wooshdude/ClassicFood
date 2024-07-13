package com.mrbysco.classicfood.client;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.event.RenderGuiOverlayEvent;
import net.minecraftforge.client.gui.overlay.GuiOverlayManager;
import net.minecraftforge.client.gui.overlay.IGuiOverlay;

public class ClientHandler {

    protected static final ResourceLocation ARMOR_FULL = new ResourceLocation(
        "minecraft",
        "textures/gui/sprites/hud/armor_full.png"
    );
    protected static final ResourceLocation ARMOR_HALF = new ResourceLocation(
        "minecraft",
        "textures/gui/sprites/hud/armor_half.png"
    );
    protected static final ResourceLocation ARMOR_EMPTY = new ResourceLocation(
        "minecraft",
        "textures/gui/sprites/hud/armor_empty.png"
    );

    public static void onGameOverlayRender(RenderGuiOverlayEvent.Pre event) {
        if (
            event.getOverlay() ==
            GuiOverlayManager.findOverlay(new ResourceLocation("food_level"))
        ) {
            event.setCanceled(true);
        }
        if (
            event.getOverlay() ==
            GuiOverlayManager.findOverlay(new ResourceLocation("armor_level"))
        ) {
            event.setCanceled(true);
        }
    }

    public static final IGuiOverlay ARMOR_LEVEL = (
        gui,
        guiGraphics,
        partialTick,
        screenWidth,
        screenHeight
    ) -> {
        Minecraft minecraft = Minecraft.getInstance();
        minecraft.getProfiler().push("armor");
        RenderSystem.enableBlend();
        int left = screenWidth / 2 + 10;
        int top = screenHeight - gui.rightHeight;
        int level = minecraft.player.getArmorValue();

        for (int i = 1; level > 0 && i < 20; i += 2) {
            if (i < level) {
                guiGraphics.blit(ARMOR_FULL, left, top, 0, 0, 9, 9, 9, 9);
            } else if (i == level) {
                guiGraphics.blit(ARMOR_HALF, left, top, 0, 0, 9, 9, 9, 9);
            } else if (i > level) {
                guiGraphics.blit(ARMOR_EMPTY, left, top, 0, 0, 9, 9, 9, 9);
            }

            left += 8;
        }

        RenderSystem.disableBlend();
        minecraft.getProfiler().pop();
    };
}
