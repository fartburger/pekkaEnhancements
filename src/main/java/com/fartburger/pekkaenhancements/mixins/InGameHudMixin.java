package com.fartburger.pekkaenhancements.mixins;


import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.awt.*;

@Mixin(net.minecraft.client.gui.hud.InGameHud.class)
public abstract class InGameHudMixin {

    @Shadow public abstract TextRenderer getTextRenderer();

    @Shadow private int scaledWidth;

    @Inject(at=@At("TAIL"),method="render")
    void render(MatrixStack matrices, float tickDelta, CallbackInfo ci) {
        this.getTextRenderer().draw(matrices, String.valueOf(((MinecraftClientMixin) MinecraftClient.getInstance()).getCurrentFps()),
                1f,1f,new Color(255,255,255).getRGB());
        if(!MinecraftClient.getInstance().player.isAlive()) {
            BlockPos bp = MinecraftClient.getInstance().player.getBlockPos();
            String coords = bp.getX()+","+bp.getY()+","+bp.getZ();
            MinecraftClient.getInstance().player.sendMessage(Text.of("You died at "+coords));
        }
    }
}
