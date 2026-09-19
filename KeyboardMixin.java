package pl.serwer.nopush.mixin;

import net.minecraft.client.Keyboard;
import net.minecraft.client.MinecraftClient;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import pl.serwer.nopush.gui.ModSettings;
import pl.serwer.nopush.gui.ClickGuiScreen;

@Mixin(Keyboard.class)
public class KeyboardMixin {
    @Inject(method = "onKey", at = @At("HEAD"))
    private void onKeyInput(long window, int key, int scancode, int action, int modifiers, CallbackInfo ci) {
        if (key == ModSettings.guiKey && action == 1 && MinecraftClient.getInstance().currentScreen == null) {
            MinecraftClient.getInstance().execute(() -> MinecraftClient.getInstance().setScreen(new ClickGuiScreen()));
            return;
        }
        if (action == 1 && MinecraftClient.getInstance().currentScreen == null) {
            if (key == ModSettings.noPushKey) ModSettings.noPushActive = !ModSettings.noPushActive;
            if (key == ModSettings.antiBlindKey) ModSettings.antiBlindActive = !ModSettings.antiBlindActive;
            if (key == ModSettings.killauraKey) ModSettings.killauraActive = !ModSettings.killauraActive;
            if (key == ModSettings.chestStealerKey) ModSettings.chestStealerActive = !ModSettings.chestStealerActive;
            if (key == ModSettings.tracersKey) ModSettings.tracersActive = !ModSettings.tracersActive;
        }
    }
}
