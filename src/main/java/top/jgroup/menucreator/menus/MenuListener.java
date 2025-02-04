package top.jgroup.menucreator.menus;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;

/**
 * Слушатель для обработки кликов по меню и закрытия инвентарей.
 */
public class MenuListener implements Listener {

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if (!(event.getWhoClicked() instanceof Player player)) return;

        Menu menu = MenuManager.getOpenMenu(player);
        if (menu != null) {
            event.setCancelled(true);

            if (event.getRawSlot() < player.getOpenInventory().getTopInventory().getSize()) {
                menu.handleClick(event);
            }
        }
    }

    @EventHandler
    public void onInventoryClose(InventoryCloseEvent event) {
        if (event.getPlayer() instanceof Player player) {
            MenuManager.removeOpenMenu(player);
        }
    }
}