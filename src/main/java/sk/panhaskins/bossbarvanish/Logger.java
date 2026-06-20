package sk.panhaskins.bossbarvanish;

import org.bukkit.Bukkit;
import sk.panhaskins.bossbarvanish.util.Colors;

public class Logger {
    public static void log(LogLevel level, String message) {
        if (message == null) return;

        switch (level) {
            case ERROR -> Bukkit.getConsoleSender().sendMessage(Colors.translate("&8[&cERROR&r&8] &f" + message));
            case WARNING -> Bukkit.getConsoleSender().sendMessage(Colors.translate("&8[&6WARNING&r&8] &f" + message));
            case INFO -> Bukkit.getConsoleSender().sendMessage(Colors.translate("&8[&eINFO&r&8] &f" + message));
            case SUCCESS -> Bukkit.getConsoleSender().sendMessage(Colors.translate("&8[&aSUCCESS&r&8] &f" + message));
            case OUTLINE -> Bukkit.getConsoleSender().sendMessage(Colors.translate("&7" + message));
        }
    }

    public enum LogLevel { ERROR, WARNING, INFO, SUCCESS, OUTLINE }
}
