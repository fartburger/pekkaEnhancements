package com.fartburger.pekkaenhancements;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;

public class PekkaEnhancements implements ModInitializer {

    public static boolean fBright = false;
    @Override
    public void onInitialize() {

    }


    void tick() {
        Thread ticker = new Thread(() -> {
            while(true) {
                sleep(10);
            }
        });
        ticker.start();
    }

    public static void sleep(long ms) {
        try {
            Thread.sleep(ms);
        } catch(Exception ignored) {}
    }


}
