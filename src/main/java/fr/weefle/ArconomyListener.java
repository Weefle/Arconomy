package fr.weefle;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class ArconomyListener implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent e){

        Player pl = e.getPlayer();
        if(Arconomy.getMoney(pl.getUniqueId()) == -1) {
            Arconomy.setMoney(pl.getUniqueId(), 2000.0);
        }

    }

}
