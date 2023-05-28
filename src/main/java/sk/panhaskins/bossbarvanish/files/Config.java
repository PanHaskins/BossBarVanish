package sk.panhaskins.bossbarvanish.files;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import sk.panhaskins.bossbarvanish.BossBarVanish;

import java.io.File;

public class Config {

    private final BossBarVanish plugin;
    private FileConfiguration customFile;
    private File file;
    String fileName = "config.yml";


    public Config(BossBarVanish plugin) {
        this.plugin = plugin;

        reloadFiles();
    }

    public void reloadFiles() {
        if (file == null)
            file = new File(this.plugin.getDataFolder(), fileName);
        if (!file.exists()) {
            this.plugin.saveResource(fileName, false);
        }
        customFile = YamlConfiguration.loadConfiguration(file);

    }



    public FileConfiguration get() {
        if (customFile == null)
            reloadFiles();

        return customFile;
    }
}
