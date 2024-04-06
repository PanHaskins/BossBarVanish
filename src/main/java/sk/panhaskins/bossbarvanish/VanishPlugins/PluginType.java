package sk.panhaskins.bossbarvanish.VanishPlugins;

import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import sk.panhaskins.bossbarvanish.BossBarVanish;
import sk.panhaskins.bossbarvanish.Logger;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public enum PluginType {

    PREMIUMVANISH("PremiumVanish", "PSVanishPlugins"),
    SUPERVANISH("SuperVanish", "PSVanishPlugins"),
    VELOCITYVANISH("VelocityVanish", "VelocityVanishPlugin"),
    ADVANCEDVANISH("AdvancedVanish","AdvancedVanishPlugin"),
    SIMPLEVANISH("SimpleVanish", "SimpleVanishPlugin");

    @Getter
    private final String name;

    @Getter
    private final String className;

    PluginType(String name, String className) {
        this.name = name;
        this.className = className;
    }


    public void registerEvents(BossBarVanish plugin) {
        if (Bukkit.getPluginManager().getPlugin(name) != null) {
            Logger.log(Logger.LogLevel.INFO, name + ": &ais found");
            BossBarVanish.haveAddon += 1;
            String classPath = "sk.panhaskins.bossbarvanish.VanishPlugins." + className;
            try {
                Class<?> listenerClass = Class.forName(classPath);
                Constructor<?> constructor = listenerClass.getDeclaredConstructor(BossBarVanish.class);
                Object instance = constructor.newInstance(plugin);

                if (instance instanceof Listener) {
                    plugin.getServer().getPluginManager().registerEvents((Listener) instance, plugin);
                }
            } catch (ClassNotFoundException | NoSuchMethodException | IllegalAccessException | InstantiationException |
                     InvocationTargetException error) {
                Logger.log(Logger.LogLevel.ERROR, error.getMessage());
            }
        } else {
            Logger.log(Logger.LogLevel.INFO, name + ": &cnot found");
        }
    }
}