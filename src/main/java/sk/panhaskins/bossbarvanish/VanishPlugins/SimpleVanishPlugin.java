package sk.panhaskins.bossbarvanish.VanishPlugins;

import net.achymake.simplevanish.SimpleVanish;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import sk.panhaskins.bossbarvanish.BossBarVanish;



public class SimpleVanishPlugin implements Listener {
    private final BossBarVanish plugin;

    public SimpleVanishPlugin(BossBarVanish plugin){
        this.plugin = plugin;


    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        Player player = e.getPlayer();
        boolean isVanished = SimpleVanish.getDatabase().isVanished(player);
        if(isVanished){
            plugin.bar.addPlayer(player);
        }
    }

    @EventHandler
    public void onLeft(PlayerQuitEvent e) {
        Player player = e.getPlayer();
        boolean isVanished = SimpleVanish.getDatabase().isVanished(player);
        if(isVanished){
            if(plugin.bar.hasBar(player)){
                plugin.bar.removePlayer(player);
            }
        }
    }

    @EventHandler
    public void usedVanishCommand(PlayerCommandPreprocessEvent e) {
        Player player = e.getPlayer();

        if (e.getMessage().equalsIgnoreCase("/vanish")) {

                if (player.hasPermission("simplevanish.command.vanish")) {
                    boolean isNotVanished = SimpleVanish.getDatabase().isVanished(player);
                    if (!isNotVanished) {
                        plugin.bar.addPlayer(player);
                    } else {
                        if (plugin.bar.hasBar(player)) {
                            plugin.bar.removePlayer(player);
                        }
                    }
                }
        }

        if(e.getMessage().toLowerCase().startsWith("/vanish ")){
            String[] args = e.getMessage().split(" ");

            if(args.length > 0){
                Player target = plugin.getServer().getPlayerExact(args[1]);
                if(target == null) {
                    return;
                }

                if (player.hasPermission("simplevanish.command.vanish.others")) {
                    boolean isNotVanished = SimpleVanish.getDatabase().isVanished(target);
                    if (!isNotVanished) {
                        plugin.bar.addPlayer(target);
                    } else {
                        if (plugin.bar.hasBar(target)) {
                            plugin.bar.removePlayer(target);
                        }
                    }
                }

            }
        }
    }

}

