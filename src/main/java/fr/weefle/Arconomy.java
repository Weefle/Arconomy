package fr.weefle;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public final class Arconomy extends JavaPlugin {

    public CreateConfig cc = new CreateConfig(this);
    public static Arconomy instance;

    @Override
    public void onEnable() {

        instance = this;
        getCommand("arconomy").setExecutor(new CommandArconomy(this));
        getServer().getPluginManager().registerEvents(new ArconomyListener(this), this);
        cc.createConfig();

    }

    @Override
    public void onDisable() {

        saveConfig();

    }

    public static double getMoney(Player p){

        if(Arconomy.instance.getConfig().get("Players." + p.getName()) != null) {
            return (double) Arconomy.instance.getConfig().get("Players." + p.getName());
        }

        return 0;

    }

    public static void setMoney(Player p, double money){

        Arconomy.instance.getConfig().set("Players." + p.getName(), money);
        Arconomy.instance.saveConfig();

    }

    public static void addMoney(Player p, double moneyToAdd){

        double money = (double) Arconomy.instance.getConfig().get("Players." + p.getName());
        money += moneyToAdd;
        Arconomy.instance.getConfig().set("Players." + p.getName(), money);
        Arconomy.instance.saveConfig();

    }

    public static void removeMoney(Player p, double moneyToRemove){

        double money = (double) Arconomy.instance.getConfig().get("Players." + p.getName());
        money -= moneyToRemove;
        Arconomy.instance.getConfig().set("Players." + p.getName(), money);
        Arconomy.instance.saveConfig();

    }

}
