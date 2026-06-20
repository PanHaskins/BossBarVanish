package sk.panhaskins.bossbarvanish.vanishPlugins;

import ir.syrent.velocityvanish.spigot.event.PreUnVanishEvent;
import ir.syrent.velocityvanish.spigot.event.PreVanishEvent;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import sk.panhaskins.bossbarvanish.BossBarVanish;

// Last update VelocityVanish 3.27.2
public class VelocityVanishPlugin implements Listener {

    private final BossBarVanish plugin;

    public VelocityVanishPlugin(BossBarVanish plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void offVanish(PreUnVanishEvent e){
        Player player = e.getPlayer();
        if (plugin.indicator.hasBar(player)) {
            plugin.indicator.removePlayer(player);
        }
    }

    @EventHandler
    public void onVanish(PreVanishEvent e){
        Player player = e.getPlayer();
        plugin.indicator.addPlayer(player);
    }

}
