package sk.panhaskins.bossbarvanish;

import org.bukkit.Bukkit;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.Player;



public class Bar {

    private final BossBarVanish plugin;
    private BossBar bossBar;

    public Bar(BossBarVanish plugin) {
        this.plugin = plugin;
    }

    public void addPlayer(Player player){
        bossBar.addPlayer(player);
    }


    public void createBar() {
            bossBar = Bukkit.createBossBar(format(BossBarVanish.config.get().getString("Config.BossBarMessage")),
                    BarColor.valueOf(BossBarVanish.config.get().getString("Config.Color")),
                    BarStyle.valueOf(BossBarVanish.config.get().getString("Config.Type")));
            bossBar.setVisible(true);
    }

    private String format(String barMessage){
        return ColorsAPI.process(barMessage);
    }

    public void removePlayer(Player player) {
        bossBar.removePlayer(player);
    }

    public boolean hasBar(Player player){
        return bossBar.getPlayers().contains(player);
    }

}