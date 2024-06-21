package me.goodbee.noomniouspotions;

import org.bukkit.Material;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.Iterator;

public class RaidLeaderDeathListener implements Listener {
    @EventHandler
    public void onDrop(EntityDeathEvent event) {
       if(!(event.getEntity() instanceof Raider)) {
           return;
       }

       Raider raider = (Raider) event.getEntity();

       if(!(raider.isPatrolLeader())) {
           return;
       }

        event.getDrops().removeIf(drop -> drop.getType() == Material.OMINOUS_BOTTLE);

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

                   BadOmenUtils.applyBadOmen(animalTamerLivingEntity);
                   return;
               }
           }
       }

       BadOmenUtils.applyBadOmen(livingEntity);
    }
}
