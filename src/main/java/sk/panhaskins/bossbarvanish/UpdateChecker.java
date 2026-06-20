package sk.panhaskins.bossbarvanish;

import org.bukkit.Bukkit;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Scanner;
import java.util.function.Consumer;

public class UpdateChecker {

    private final BossBarVanish plugin;
    private final int resourceID;

    public UpdateChecker(BossBarVanish plugin, int resourceID)
    {
        this.plugin = plugin;
        this.resourceID = resourceID;
    }
    public void getLatestVersion(Consumer<String> consumer) {
        Bukkit.getScheduler().runTaskAsynchronously(this.plugin, () ->
        {
            try (InputStream inputStream = new URL("https://api.spigotmc.org/legacy/update.php?resource=" + this.resourceID).openStream();
                 Scanner scanner = new Scanner(inputStream)){
                if(scanner.hasNext())
                {
                    consumer.accept(scanner.next());
                }
            } catch (IOException exception)
            {
                Logger.log(Logger.LogLevel.ERROR, "UPDATE CHECKER is broken, can´t find an update!\n" + exception.getMessage());
            }
        });
    }
}
