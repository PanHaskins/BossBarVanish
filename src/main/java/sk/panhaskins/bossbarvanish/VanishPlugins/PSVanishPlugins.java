package sk.panhaskins.bossbarvanish.VanishPlugins;

import de.myzelyam.api.vanish.PlayerHideEvent;
import de.myzelyam.api.vanish.PlayerShowEvent;
import de.myzelyam.api.vanish.VanishAPI;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import sk.panhaskins.bossbarvanish.BossBarVanish;

// This class is for PremiumVanish and SuperVanish plugin
public class PSVanishPlugins implements Listener {
    private final BossBarVanish plugin;

    public PSVanishPlugins(BossBarVanish plugin) {
        this.plugin = plugin;
    }

    @EventHandler
        public void onVanish(PlayerHideEvent e) {
            Player player = e.getPlayer();
            plugin.bar.addPlayer(player);
    }

    @EventHandler
    public void offVanish(PlayerShowEvent e) {
            Player player = e.getPlayer();
            if (plugin.bar.hasBar(player)) {
                plugin.bar.removePlayer(player);
            }
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        Player player = e.getPlayer();
        boolean isVanished = VanishAPI.isInvisible(player);
        if(isVanished){
            plugin.bar.addPlayer(player);
        }
    }

    @EventHandler
    public void onLeft(PlayerQuitEvent e) {
        Player player = e.getPlayer();
        boolean isVanished = VanishAPI.isInvisible(player);
        if(isVanished){
            if(plugin.bar.hasBar(player)){
                plugin.bar.removePlayer(player);
            }
        }
    }
}
