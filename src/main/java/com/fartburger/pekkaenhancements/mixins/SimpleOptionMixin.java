package com.fartburger.pekkaenhancements.mixins;

import com.fartburger.pekkaenhancements.mixinUtil.SimpleOptionDuck;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.SimpleOption;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import java.util.Objects;
import java.util.function.Consumer;

@Mixin(SimpleOption.class)
public class SimpleOptionMixin<T> implements SimpleOptionDuck<T> {

    @Shadow
    T value;

    @Shadow
    @Final
    private Consumer<T> changeCallback;

    @Override
    public void setValueDirectly(T o) {
        if (!MinecraftClient.getInstance().isRunning()) {
            this.value = o;
        } else {
            if (!Objects.equals(this.value, o)) {
                this.value = o;
                this.changeCallback.accept(this.value);
            }

        }
    }
}
