package top.jgroup.menucreator.buttons;

import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

/**
 * Кнопка, которая отображает информацию и не даёт перемещать предмет.
 * Можно дополнительно выводить сообщение при клике.
 */
public class InformationButton implements Button {

    private final ItemStack item;
    private final String clickMessage; // сообщение при клике (опционально)

    /**
     * Создаёт информационную кнопку без сообщения при клике.
     * @param item предмет, который будет отображаться в меню.
     */
    public InformationButton(ItemStack item) {
        this(item, null);
    }

    /**
     * Создаёт информационную кнопку, при клике выводит сообщение (если оно не null).
     * @param item предмет, который будет отображаться в меню.
     * @param clickMessage сообщение, отображаемое игроку при клике (можно null).
     */
    public InformationButton(ItemStack item, String clickMessage) {
        this.item = item;
        this.clickMessage = clickMessage;
    }

    @Override
    public void onClick(InventoryClickEvent event) {
        if (clickMessage != null && !clickMessage.isEmpty()) {
            event.getWhoClicked().sendMessage(clickMessage);
        }
    }

    @Override
    public ItemStack getItem() {
        return item;
    }
}