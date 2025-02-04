package top.jgroup.menucreator.buttons;

import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

/**
 * Интерфейс для кнопки в меню.
 */
public interface Button {
    /**
     * Вызывается при клике по кнопке.
     *
     * @param event событие клика.
     */
    void onClick(InventoryClickEvent event);

    /**
     * Возвращает предмет, отображаемый в меню.
     *
     * @return ItemStack кнопки.
     */
    ItemStack getItem();
}