package fr.weefle;

import org.bukkit.plugin.java.JavaPlugin;

public final class Arconomy extends JavaPlugin {

    public CreateConfig cc = new CreateConfig(this);

    @Override
    public void onEnable() {

        getCommand("arconomy").setExecutor(new CommandArconomy(this));
        getServer().getPluginManager().registerEvents(new ArconomyListener(this), this);
        cc.createConfig();

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        saveConfig();
    }
}
