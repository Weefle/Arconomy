package fr.weefle;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class ArconomyListener implements Listener {

    private Arconomy m;
    public ArconomyListener(Arconomy m){
        this.m = m;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e){

        Player pl = e.getPlayer();
        if(m.getConfig().get("Players." + pl.getName()) == null) {
            m.getConfig().set("Players." + pl.getName(), Float.parseFloat("2000"));
            m.saveConfig();
        }

    }

}
