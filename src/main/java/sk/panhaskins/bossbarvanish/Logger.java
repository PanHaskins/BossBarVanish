package sk.panhaskins.bossbarvanish;

import org.bukkit.Bukkit;

public class Logger {
    public static void log(LogLevel level, String message) {
        if (message == null) return;

        switch (level) {
            case ERROR:
                Bukkit.getConsoleSender().sendMessage(ColorsAPI.process( "&8[&cERROR&r&8] &f" + message));
                break;
            case WARNING:
                Bukkit.getConsoleSender().sendMessage(ColorsAPI.process( "&8[&6WARNING&r&8] &f" + message));
                break;
            case INFO:
                Bukkit.getConsoleSender().sendMessage(ColorsAPI.process( "&8[&eINFO&r&8] &f" + message));
                break;
            case SUCCESS:
                Bukkit.getConsoleSender().sendMessage(ColorsAPI.process( "&8[&aSUCCESS&r&8] &f" + message));
                break;
            case OUTLINE:
                Bukkit.getConsoleSender().sendMessage(ColorsAPI.process( "&7" + message));
                break;
        }
    }

    public enum LogLevel { ERROR, WARNING, INFO, SUCCESS, OUTLINE }
}
