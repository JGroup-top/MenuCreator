package top.jgroup.menucreator.buttons;

import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import top.jgroup.menucreator.MenuCreatorAPI;
import top.jgroup.menucreator.menus.Menu;
import top.jgroup.menucreator.menus.PersistentDataHelper;

/**
 * Кнопка, открывающая другое меню.
 */
public class NextMenuButton implements Button {
    private final Menu nextMenu;
    private final ItemStack item;

    /**
     * Создаёт кнопку для открытия следующего меню.
     *
     * @param nextMenu следующее меню, которое откроется при клике.
     * @param item     предмет для отображения.
     */
    public NextMenuButton(Menu nextMenu, ItemStack item) {
        this.nextMenu = nextMenu;
        this.item = item;

        // Сохраним "тип" кнопки в PDC (для наглядности)
        if (MenuCreatorAPI.getPlugin() != null) {
            ItemMeta meta = this.item.getItemMeta();
            if (meta != null) {
                PersistentDataHelper.setData(this.item, MenuCreatorAPI.getPlugin(), "buttonType", "nextMenu");
            }
        }
    }

    @Override
    public void onClick(InventoryClickEvent event) {
        if (event.getWhoClicked() instanceof Player player) {
            nextMenu.open(player);
        }
    }

    @Override
    public ItemStack getItem() {
        return item;
    }
}