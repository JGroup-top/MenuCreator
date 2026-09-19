package top.jgroup.menucreator;

import org.bukkit.plugin.Plugin;
import top.jgroup.menucreator.menus.MenuListener;

/**
 * Класс для хранения глобальной ссылки на плагин.
 * Перед использованием библиотеки установите плагин:
 * MenuCreatorAPI.setPlugin(this);
 */
public class MenuCreatorAPI {
    private static Plugin plugin;

    public static void setPlugin(Plugin plugin) {
        MenuCreatorAPI.plugin = plugin;

        plugin.getServer()
                .getPluginManager()
                .registerEvents(new MenuListener(), plugin);
    }

    public static Plugin getPlugin() {
        return plugin;
    }
}