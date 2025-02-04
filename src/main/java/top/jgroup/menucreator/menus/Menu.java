package top.jgroup.menucreator.menus;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import top.jgroup.menucreator.buttons.Button;

import java.util.HashMap;
import java.util.Map;

/**
 * Абстрактный класс для создания инвентарей-меню.
 */
public abstract class Menu {
    private final Inventory inventory;
    private final MenuHolder holder;
    private final Map<Integer, Button> buttonMap = new HashMap<>();

    /**
     * Создаёт меню, выбирая тип (Dropper, Single Chest, Double Chest) и заголовок.
     *
     * @param type  MenuType (DROPPER / SINGLE_CHEST / DOUBLE_CHEST)
     * @param title заголовок инвентаря (отображаемое название)
     */
    public Menu(MenuType type, String title) {
        // Кастомный holder, который хранит PDC
        holder = new MenuHolder();

        // Если в MenuType задан InventoryType (например, DROPPER), используем его
        if (type.getInventoryType() != null) {
            this.inventory = Bukkit.createInventory(holder, type.getInventoryType(), title);
        } else {
            // Иначе берём числовой размер (27/54) и создаём сундук
            this.inventory = Bukkit.createInventory(holder, type.getSize(), title);
        }

        holder.setInventory(inventory);
        holder.setMenuTitle(title);  // Запишем название меню в PDC
    }

    /**
     * Добавляет кнопку в указанный слот.
     */
    public void setButton(int slot, Button button) {
        buttonMap.put(slot, button);
        inventory.setItem(slot, button.getItem());
    }

    /**
     * Получает кнопку из указанного слота.
     */
    public Button getButton(int slot) {
        return buttonMap.get(slot);
    }

    /**
     * Открывает меню для игрока и регистрирует его, чтобы обрабатывать клики.
     */
    public void open(Player player) {
        MenuManager.setOpenMenu(player, this);
        player.openInventory(inventory);
    }

    /**
     * Обрабатывает клик по инвентарю.
     */
    public void handleClick(InventoryClickEvent event) {
        int slot = event.getRawSlot();
        Button button = buttonMap.get(slot);
        if (button != null) {
            event.setCancelled(true);
            button.onClick(event);
        }
    }

    public Inventory getInventory() {
        return inventory;
    }

    public MenuHolder getMenuHolder() {
        return holder;
    }
}