package me.goodbee.noomniouspotions;

import org.bukkit.plugin.java.JavaPlugin;

public final class NoOmniousPotions extends JavaPlugin {
    private static NoOmniousPotions instance;

    public static NoOmniousPotions getInstance() {
        return instance;
    }

    @Override
    public void onEnable() {
        // Plugin startup logic
        instance = this;
        getServer().getPluginManager().registerEvents(new RaidLeaderDeathListener(), this);

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
