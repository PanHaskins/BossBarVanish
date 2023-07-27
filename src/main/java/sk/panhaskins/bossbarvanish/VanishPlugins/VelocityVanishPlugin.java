package sk.panhaskins.bossbarvanish.VanishPlugins;

import ir.syrent.velocityvanish.spigot.event.PreUnVanishEvent;
import ir.syrent.velocityvanish.spigot.event.PreVanishEvent;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import sk.panhaskins.bossbarvanish.BossBarVanish;

public class VelocityVanishPlugin implements Listener {

    private final BossBarVanish plugin;

    public VelocityVanishPlugin(BossBarVanish plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void offVanish(PreUnVanishEvent e){
        Player player = e.getPlayer();
        if (plugin.bar.hasBar(player)) {
            plugin.bar.removePlayer(player);
        }
    }

    @EventHandler
    public void onVanish(PreVanishEvent e){
        Player player = e.getPlayer();
        plugin.bar.addPlayer(player);
    }

}
