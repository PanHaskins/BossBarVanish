package sk.panhaskins.bossbarvanish;

import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import sk.panhaskins.bossbarvanish.VanishPlugins.PluginType;
import sk.panhaskins.bossbarvanish.files.Config;


public final class BossBarVanish extends JavaPlugin implements Listener {

    public static Config config;
    public Bar bar;
    public static BossBarVanish instance;
    public static BossBarVanish getInstance() {
        return instance;
    }

    public static int haveAddon;


    @Override
    public void onEnable() {
            // PLUGIN LOADING LOG
            //---------------------------------------------------------------------------------
            Logger.log(Logger.LogLevel.OUTLINE, "&b-------------------------------");
            Logger.log(Logger.LogLevel.OUTLINE, "&bBossBarVanish &fAddon");
            Logger.log(Logger.LogLevel.INFO, "Plugin loading...");

            config = new Config(this);
            bar = new Bar(this);

            this.getCommand("bbv").setExecutor(new Commands());
            this.getCommand("bbv").setTabCompleter(new TabComplete());


            for (PluginType pluginType : PluginType.values()) {
                pluginType.registerEvents(this);
            }


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

        if (haveAddon == 1) {
            Logger.log(Logger.LogLevel.SUCCESS, "Plugin is loaded!");
            Logger.log(Logger.LogLevel.OUTLINE, "&b-------------------------------");
        } else {
            Logger.log(Logger.LogLevel.ERROR, "Plugin is not loaded!");

            Logger.log(Logger.LogLevel.OUTLINE, "");
            Logger.log(Logger.LogLevel.ERROR, "You must install one of the supported plugins");
            Logger.log(Logger.LogLevel.ERROR, "or you have more than 1 plugin on your server");
            Logger.log(Logger.LogLevel.ERROR, "that supports this plugin.");
            Logger.log(Logger.LogLevel.OUTLINE, "");
            Logger.log(Logger.LogLevel.OUTLINE, "&b-------------------------------");

            this.getPluginLoader().disablePlugin(this);
        }



        bar.createBar();
    }
        //---------------------------------------------------------------------------------

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

}
