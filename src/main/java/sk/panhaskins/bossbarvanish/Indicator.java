package sk.panhaskins.bossbarvanish;

import net.kyori.adventure.bossbar.BossBar;
import net.kyori.adventure.title.Title;
import org.bukkit.entity.Player;
import sk.panhaskins.bossbarvanish.util.Colors;

import java.time.Duration;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;


public class Indicator {

    private final BossBarVanish plugin;
    private BossBar bossBar;
    private final Set<UUID> viewers = new HashSet<>();

    public Indicator(BossBarVanish plugin) {
        this.plugin = plugin;
    }

    public void create() {
        String message = cfg("Config.BossBarMessage");
        if (!message.isEmpty()) {
            bossBar = BossBar.bossBar(
                    Colors.translate(message),
                    1.0f,
                    BossBar.Color.valueOf(cfg("Config.Color")),
                    overlay(cfg("Config.Type")));
        }
    }

    public void addPlayer(Player player) {
        showBar(player);
        sendScreen(player, "Vanished");
    }

    public void removePlayer(Player player) {
        hideBar(player);
        sendScreen(player, "UnVanished");
    }

    public boolean hasBar(Player player) {
        return viewers.contains(player.getUniqueId());
    }

    public void reloadBar() {
        List<Player> current = new ArrayList<>();
        for (UUID id : viewers) {
            Player p = plugin.getServer().getPlayer(id);
            if (p != null) current.add(p);
        }
        current.forEach(this::hideBar);
        create();
        current.forEach(this::showBar);
    }

    private void showBar(Player player) {
        if (bossBar != null) {
            player.showBossBar(bossBar);
            viewers.add(player.getUniqueId());
        }
    }

    private void hideBar(Player player) {
        if (bossBar != null) player.hideBossBar(bossBar);
        viewers.remove(player.getUniqueId());
    }

    private void sendScreen(Player player, String state) {
        String title = cfg("Config.Screen." + state + ".TitleMessage");
        String subtitle = cfg("Config.Screen." + state + ".SubtitleMessage");
        if (title.isEmpty() && subtitle.isEmpty()) return;
        Title.Times times = Title.Times.times(
                ticks("Config.Screen.FadeIn"),
                ticks("Config.Screen.Stay"),
                ticks("Config.Screen.FadeOut"));
        player.showTitle(Title.title(Colors.translate(title, player), Colors.translate(subtitle, player), times));
    }

    private static String cfg(String path) {
        return BossBarVanish.config.get().getString(path, "");
    }

    private static Duration ticks(String path) {
        return Duration.ofMillis(BossBarVanish.config.get().getInt(path) * 50L);
    }

    /** Maps the config's Bukkit BarStyle names onto Adventure overlays. */
    private static BossBar.Overlay overlay(String type) {
        return switch (type) {
            case "SEGMENTED_6" -> BossBar.Overlay.NOTCHED_6;
            case "SEGMENTED_10" -> BossBar.Overlay.NOTCHED_10;
            case "SEGMENTED_12" -> BossBar.Overlay.NOTCHED_12;
            case "SEGMENTED_20" -> BossBar.Overlay.NOTCHED_20;
            default -> BossBar.Overlay.PROGRESS;
        };
    }

}
