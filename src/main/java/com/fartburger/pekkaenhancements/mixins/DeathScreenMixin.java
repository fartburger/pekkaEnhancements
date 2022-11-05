package com.fartburger.pekkaenhancements.mixins;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.DeathScreen;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.awt.*;

@Mixin(DeathScreen.class)
public class DeathScreenMixin extends Screen {

    protected DeathScreenMixin(Text title) {
        super(title);
    }

    @Inject(at=@At("TAIL"),method="render")
    void render(MatrixStack matrices, int mouseX, int mouseY, float delta, CallbackInfo ci) {
        BlockPos bp = MinecraftClient.getInstance().player.getBlockPos();
        String coords = bp.getX()+","+bp.getY()+","+bp.getZ();
        drawCenteredText(matrices,textRenderer,"You died at "+coords,this.width/2,107,new Color(255,255,255).getRGB());
    }
}
