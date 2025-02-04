package top.jgroup.menucreator.menus;

import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Менеджер для отслеживания открытых меню у игроков.
 */
public class MenuManager {
    private static final Map<UUID, Menu> openMenus = new HashMap<>();

    public static void setOpenMenu(Player player, Menu menu) {
        openMenus.put(player.getUniqueId(), menu);
    }

    public static void removeOpenMenu(Player player) {
        openMenus.remove(player.getUniqueId());
    }

    public static Menu getOpenMenu(Player player) {
        return openMenus.get(player.getUniqueId());
    }
}