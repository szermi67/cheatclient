package pl.serwer.nopush.mixin;

import net.minecraft.client.player.LocalPlayer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import pl.serwer.nopush.gui.ModSettings;

@Mixin(LocalPlayer.class)
public class LocalPlayerMixin {
    @Inject(method = "pushOutOfBlocks", at = @At("HEAD"), cancellable = true)
    private void onPushOutOfBlocks(double x, double z, CallbackInfoReturnable<Boolean> cir) {
        if (ModSettings.noPushActive) {
            cir.setReturnValue(false);
        }
    }
}
