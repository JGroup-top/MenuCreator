package top.jgroup.menucreator.menus;

import org.bukkit.event.inventory.InventoryType;

/**
 * Типы меню, соответствующие разным вариантам инвентаря.
 */
public enum MenuType {
    DROPPER(InventoryType.DROPPER, 9),       // 9 слотов, интерфейс дроппера
    SINGLE_CHEST(null, 27),                 // 27 слотов, сундук
    DOUBLE_CHEST(null, 54);                 // 54 слота, двойной сундук

    private final InventoryType inventoryType; // Может быть null, если создаём по числу слотов
    private final int size;

    MenuType(InventoryType inventoryType, int size) {
        this.inventoryType = inventoryType;
        this.size = size;
    }

    public InventoryType getInventoryType() {
        return inventoryType;
    }

    public int getSize() {
        return size;
    }
}