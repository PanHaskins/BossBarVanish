package sk.panhaskins.bossbarvanish.VanishPlugins;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.sayandev.sayanvanish.bukkit.api.event.BukkitUserUnVanishEvent;
import org.sayandev.sayanvanish.bukkit.api.event.BukkitUserVanishEvent;
import sk.panhaskins.bossbarvanish.BossBarVanish;

public class SayanVanishPlugin implements Listener {
    private final BossBarVanish plugin;

    public SayanVanishPlugin(BossBarVanish plugin){
        this.plugin = plugin;
    }


    @EventHandler
    public void offVanish(BukkitUserUnVanishEvent e){
        Player player = e.getUser().player();
        if (plugin.bar.hasBar(player)) {
            plugin.bar.removePlayer(player);
        }
    }

    @EventHandler
    public void onVanish(BukkitUserVanishEvent e){
        Player player = e.getUser().player();
        plugin.bar.addPlayer(player);
    }
}
