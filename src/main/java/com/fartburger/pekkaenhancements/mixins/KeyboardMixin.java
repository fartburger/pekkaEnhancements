package com.fartburger.pekkaenhancements.mixins;

import com.fartburger.pekkaenhancements.mixinUtil.SimpleOptionDuck;
import net.minecraft.client.Keyboard;
import net.minecraft.client.MinecraftClient;
import org.lwjgl.glfw.GLFW;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import static com.fartburger.pekkaenhancements.PekkaEnhancements.fBright;

@Mixin(Keyboard.class)
public class KeyboardMixin {
    @Inject(at=@At("HEAD"),method="onKey")
    void onKey(long window, int key, int scancode, int action, int modifiers, CallbackInfo ci) {
        if(key == GLFW.GLFW_KEY_B && action == 1 && MinecraftClient.getInstance().currentScreen==null) {
            fBright = !fBright;
            if (fBright) {
                ((SimpleOptionDuck<Double>) (Object)MinecraftClient.getInstance().options.getGamma()).setValueDirectly(10d);
            } else {
                ((SimpleOptionDuck<Double>) (Object)MinecraftClient.getInstance().options.getGamma()).setValueDirectly(1d);
            }
        }
    }
}
