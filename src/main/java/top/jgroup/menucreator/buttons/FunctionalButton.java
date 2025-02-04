package top.jgroup.menucreator.buttons;

import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import top.jgroup.menucreator.MenuCreatorAPI;
import top.jgroup.menucreator.menus.PersistentDataHelper;

/**
 * Кнопка с функциональным обработчиком, позволяющая задать произвольное действие через лямбда-выражение.
 */
public class FunctionalButton implements Button {

    private final ItemStack item;
    private final ButtonAction action;

    /**
     * Создаёт кнопку с указанным предметом и действием.
     *
     * @param item   предмет (ItemStack) для отображения.
     * @param action функциональное действие при клике (через лямбда-выражение).
     */
    public FunctionalButton(ItemStack item, ButtonAction action) {
        this.item = item;
        this.action = action;

        // Для наглядности отметим, что это "functional" кнопка
        if (MenuCreatorAPI.getPlugin() != null) {
            ItemMeta meta = this.item.getItemMeta();
            if (meta != null) {
                PersistentDataHelper.setData(this.item, MenuCreatorAPI.getPlugin(), "buttonType", "functional");
            }
        }
    }

    @Override
    public void onClick(InventoryClickEvent event) {
        action.execute(event);
    }

    @Override
    public ItemStack getItem() {
        return item;
    }

    /**
     * Функциональный интерфейс для обработки клика.
     */
    @FunctionalInterface
    public interface ButtonAction {
        void execute(InventoryClickEvent event);
    }
}