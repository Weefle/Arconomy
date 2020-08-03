package fr.weefle;

import org.bukkit.Bukkit;

import java.io.File;

public class CreateConfig {

    private Arconomy m;
    public CreateConfig(Arconomy m){
        this.m = m;
    }

    public void createConfig() {

        File f = new File(this.m.getDataFolder(), "config.yml");

        if(f.exists()){

            Bukkit.getLogger().info("The configuration's file is already created!");

        }else{
            m.getConfig().options().copyDefaults(false);
            m.getConfig().createSection("Players");
            m.saveConfig();
        }

    }

}
