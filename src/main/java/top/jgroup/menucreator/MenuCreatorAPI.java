package top.jgroup.menucreator;

import org.bukkit.plugin.Plugin;

/**
 * Класс для хранения глобальной ссылки на плагин.
 * Перед использованием библиотеки установите плагин:
 * MenuCreatorAPI.setPlugin(this);
 */
public class MenuCreatorAPI {
    private static Plugin plugin;

    public static void setPlugin(Plugin plugin) {
        MenuCreatorAPI.plugin = plugin;
    }

    public static Plugin getPlugin() {
        return plugin;
    }
}