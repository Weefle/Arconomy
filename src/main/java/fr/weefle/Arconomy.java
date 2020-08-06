package fr.weefle;

import org.bukkit.plugin.java.JavaPlugin;

import java.util.UUID;

public final class Arconomy extends JavaPlugin {

    public CreateConfig cc = new CreateConfig(this);
    public static Arconomy instance;

    @Override
    public void onEnable() {

        instance = this;
        getCommand("arconomy").setExecutor(new CommandArconomy());
        getServer().getPluginManager().registerEvents(new ArconomyListener(), this);
        cc.createConfig();

    }

    @Override
    public void onDisable() {

        saveConfig();

    }

    public static double getMoney(UUID uuid){

        if(Arconomy.instance.getConfig().get("Players." + uuid) != null) {
            return (double) Arconomy.instance.getConfig().get("Players." + uuid);
        }

        return -1;

    }

    public static void setMoney(UUID uuid, double money){

        Arconomy.instance.getConfig().set("Players." + uuid, money);
        Arconomy.instance.saveConfig();

    }

    public static void addMoney(UUID uuid, double moneyToAdd){

        double money = (double) Arconomy.instance.getConfig().get("Players." + uuid);
        money += moneyToAdd;
        Arconomy.instance.getConfig().set("Players." + uuid, money);
        Arconomy.instance.saveConfig();

    }

    public static void removeMoney(UUID uuid, double moneyToRemove){

        double money = (double) Arconomy.instance.getConfig().get("Players." + uuid);
        money -= moneyToRemove;
        Arconomy.instance.getConfig().set("Players." + uuid, money);
        Arconomy.instance.saveConfig();

    }

}
