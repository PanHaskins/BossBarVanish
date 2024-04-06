package sk.panhaskins.bossbarvanish.VanishPlugins;

import me.quantiom.advancedvanish.event.PlayerUnVanishEvent;
import me.quantiom.advancedvanish.event.PlayerVanishEvent;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import sk.panhaskins.bossbarvanish.BossBarVanish;

public class AdvancedVanishPlugin implements Listener {
    private final BossBarVanish plugin;

    public AdvancedVanishPlugin(BossBarVanish plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onVanish(PlayerVanishEvent e) {
        Player player = e.getPlayer();
        plugin.bar.addPlayer(player);
    }

    @EventHandler
    public void offVanish(PlayerUnVanishEvent e) {
        Player player = e.getPlayer();
        if (plugin.bar.hasBar(player)) {
            plugin.bar.removePlayer(player);
        }
    }
}
