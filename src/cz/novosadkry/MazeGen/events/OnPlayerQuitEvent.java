package cz.novosadkry.MazeGen.events;

import cz.novosadkry.MazeGen.Main;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class OnPlayerQuitEvent implements Listener {
    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent e) {
        if (Main.mazePersist.containsKey(e.getPlayer()))
            Main.mazePersist.remove(e.getPlayer());
    }
}
