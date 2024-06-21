package me.goodbee.noomniouspotions;

import org.bukkit.Material;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.EntityDropItemEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class NoDropListener implements Listener {
    @EventHandler
    public void onDrop(EntityDeathEvent event) {
       if(!(event.getEntity() instanceof Raider)) {
           return;
       }

       Raider raider = (Raider) event.getEntity();

       if(!(raider.isPatrolLeader())) {
           return;
       }

       int i = 0;
       for(ItemStack drop : event.getDrops()) {
           if (drop.getType() == Material.OMINOUS_BOTTLE) {
               event.getDrops().remove(i);
           }

           i++;
       }

       // FIXME: This does not account for patrol captains, which increasingly add an amplifier

       if(!(event.getDamageSource().getCausingEntity() instanceof LivingEntity)) {
           return;
       }

       LivingEntity livingEntity = (LivingEntity) event.getDamageSource().getCausingEntity();

       if(livingEntity instanceof Tameable) {
           Tameable tameable = (Tameable) livingEntity;

           if(tameable.isTamed()) {
               AnimalTamer animalTamer = tameable.getOwner();

               if(animalTamer instanceof LivingEntity) {
                   LivingEntity animalTamerLivingEntity = (LivingEntity) animalTamer;

                   animalTamerLivingEntity.addPotionEffect(new PotionEffect(PotionEffectType.BAD_OMEN, 100 * 60 * 20, 1));
               }
           }
       }

       livingEntity.addPotionEffect(new PotionEffect(PotionEffectType.BAD_OMEN, 100 * 60 * 20, 1));
    }
}