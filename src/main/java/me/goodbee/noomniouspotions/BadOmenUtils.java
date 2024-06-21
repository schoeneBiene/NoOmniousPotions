package me.goodbee.noomniouspotions;

import org.bukkit.entity.LivingEntity;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class BadOmenUtils {
    public static void applyBadOmen(LivingEntity livingEntity) {
        if(!(livingEntity.hasPotionEffect(PotionEffectType.BAD_OMEN))) {
            NoOmniousPotions.getInstance().getLogger().info(String.format("Applying Bad Omen 1 to %s", livingEntity.getName()));
            applyBadOmen(livingEntity, 0);
            return;
        }

        PotionEffect existingPotionEffect = livingEntity.getPotionEffect(PotionEffectType.BAD_OMEN);

        if(existingPotionEffect == null) return;

        if(existingPotionEffect.getAmplifier() > 5) {
            NoOmniousPotions.getInstance().getLogger().info(String.format("Applying Bad Omen %d to %s", existingPotionEffect.getAmplifier() + 1, livingEntity.getName()));
            applyBadOmen(livingEntity, existingPotionEffect.getAmplifier() + 1);
        }

        applyBadOmen(livingEntity, 5);
    }

    private static void applyBadOmen(LivingEntity livingEntity, int amplifier) {
        livingEntity.addPotionEffect(new PotionEffect(PotionEffectType.BAD_OMEN, 100 * 60 * 20, amplifier));
    }
}
