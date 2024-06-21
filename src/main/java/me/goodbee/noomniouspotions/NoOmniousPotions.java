package me.goodbee.noomniouspotions;

import org.bukkit.plugin.java.JavaPlugin;

public final class NoOmniousPotions extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic

        getServer().getPluginManager().registerEvents(new RaidLeaderDeathListener(), this);

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
