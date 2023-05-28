package sk.panhaskins.bossbarvanish;

import de.myzelyam.api.vanish.PlayerHideEvent;
import de.myzelyam.api.vanish.PlayerShowEvent;
import de.myzelyam.api.vanish.VanishAPI;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.java.JavaPlugin;
import sk.panhaskins.bossbarvanish.files.Config;

import java.util.ArrayList;
import java.util.List;

public final class BossBarVanish extends JavaPlugin implements Listener {

    public static Config config;
    public Bar bar;

    List<Player> players = new ArrayList<>();


    @Override
    public void onEnable() {
        if (Bukkit.getPluginManager().getPlugin("PremiumVanish") != null || Bukkit.getPluginManager().getPlugin("SuperVanish") != null) {
            // PLUGIN LOADING LOG
            //---------------------------------------------------------------------------------
            Logger.log(Logger.LogLevel.OUTLINE, "&b-------------------------------");
            Logger.log(Logger.LogLevel.OUTLINE, "&bBossBarVanish &fAddon");
            Logger.log(Logger.LogLevel.INFO, "Plugin loading...");

            config = new Config(this);
            this.getServer().getPluginManager().registerEvents(this, this);
            bar = new Bar(this);
            this.getCommand("bbv").setExecutor(new Commands());
            this.getCommand("bbv").setTabCompleter(new TabComplete());

            if (Bukkit.getPluginManager().getPlugin("PremiumVanish") != null) {
                Logger.log(Logger.LogLevel.INFO, "PremiumVanish: &ais found");
            }


            if (Bukkit.getPluginManager().getPlugin("SuperVanish") != null) {
                Logger.log(Logger.LogLevel.INFO, "SuperVanish: &ais found");
            }


            Logger.log(Logger.LogLevel.SUCCESS, "Plugin is loaded!");

            new UpdateChecker(this, 101063).getLatestVersion(version -> {
                if (this.getDescription().getVersion().equalsIgnoreCase(version)) {
                } else {
                    Logger.log(Logger.LogLevel.OUTLINE, "");
                    Logger.log(Logger.LogLevel.WARNING, "&eBossBarVanish &fAddon");
                    Logger.log(Logger.LogLevel.WARNING, "&fYour plugin version is out of date.");
                    Logger.log(Logger.LogLevel.WARNING, "&fI recommend updating it.");
                    Logger.log(Logger.LogLevel.WARNING, "https://www.spigotmc.org/resources/bbv-boss-bar-for-vanish.101063/");
                    Logger.log(Logger.LogLevel.OUTLINE, "");
                }
            });
            Logger.log(Logger.LogLevel.OUTLINE, "&b-------------------------------");
        } else {
            Logger.log(Logger.LogLevel.OUTLINE, "");
            Logger.log(Logger.LogLevel.ERROR, "&cBossBarVanish &fAddon");
            Logger.log(Logger.LogLevel.ERROR, "You must install");
            Logger.log(Logger.LogLevel.ERROR, "SuperVanish or PremiumVanish");
            Logger.log(Logger.LogLevel.OUTLINE, "");
            this.getPluginLoader().disablePlugin(this);
        }
        bar.createBar();
    }
        //---------------------------------------------------------------------------------

    @EventHandler
    public void onVanish(PlayerHideEvent e) {
        Player player = e.getPlayer();
        bar.addPlayer(player);
        player.sendMessage("Hide");
    }

    @EventHandler
    public void offVanish(PlayerShowEvent e) {
        Player player = e.getPlayer();
        if(bar.hasBar(player)){
            bar.removePlayer(player);
            player.sendMessage("Show");
        }
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        Player player = e.getPlayer();
        boolean isVanished = VanishAPI.isInvisible(player);
        if(isVanished){
            bar.addPlayer(player);
        }

        e.setJoinMessage(config.get().getString("Join")
                .replaceAll("%player%", player.getDisplayName())
                .replaceAll("%online%", String.valueOf(Bukkit.getOnlinePlayers().size() - 1 ))
                .replaceAll("%max_online%", String.valueOf(Bukkit.getMaxPlayers())));
    }

    @EventHandler
    public void onLeft(PlayerQuitEvent e) {
        Player player = e.getPlayer();
        boolean isVanished = VanishAPI.isInvisible(player);
        if(isVanished){
            if(players.contains(player)){
               players.remove(player);
               bar.removePlayer(player);
            }
        }
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
