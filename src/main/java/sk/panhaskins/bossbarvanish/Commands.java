package sk.panhaskins.bossbarvanish;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import sk.panhaskins.bossbarvanish.util.Colors;

public class Commands implements CommandExecutor {

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (label.equalsIgnoreCase("bbv") || label.equalsIgnoreCase("bossbarvanish")) {
            if (sender instanceof Player) {
                if (args.length == 0) {
                    // /bbv
                    sender.sendMessage(Colors.translate(BossBarVanish.config.get().getString("Config.Reload.Usage")));
                    return true;
                }

                if (args.length == 1) {
                    if (args[0].equalsIgnoreCase("reload")) {

                        if (sender.hasPermission("bbv.reload")) {
                            sender.sendMessage(Colors.translate(BossBarVanish.config.get().getString("Config.Reload.Reloading")));
                            BossBarVanish.config.reloadFiles();
                            BossBarVanish.indicator.reloadBar();
                            sender.sendMessage(Colors.translate(BossBarVanish.config.get().getString("Config.Reload.Complete")));
                        } else {
                            sender.sendMessage(Colors.translate(BossBarVanish.config.get().getString("Config.Reload.NoPermission")));
                        }
                        return true;
                    }

                }
            }
        }
        return false;
    }
}