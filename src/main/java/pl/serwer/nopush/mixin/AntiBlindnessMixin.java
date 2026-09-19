package pl.serwer.nopush.mixin;

import net.minecraft.client.player.LocalPlayer;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffects;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import net.minecraft.registry.entry.RegistryEntry;
import pl.serwer.nopush.gui.ModSettings;

@Mixin(LocalPlayer.class)
public class AntiBlindnessMixin {
    @Inject(method = "hasStatusEffect", at = @At("HEAD"), cancellable = true)
    private void onHasStatusEffect(RegistryEntry<StatusEffect> effect, CallbackInfoReturnable<Boolean> cir) {
        if (ModSettings.antiBlindActive) {
            if (effect.value() == StatusEffects.BLINDNESS || effect.value() == StatusEffects.DARKNESS) {
                cir.setReturnValue(false);
            }
        }
    }
}
