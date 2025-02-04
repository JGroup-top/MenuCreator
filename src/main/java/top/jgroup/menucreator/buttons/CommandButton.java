package top.jgroup.menucreator.buttons;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import top.jgroup.menucreator.MenuCreatorAPI;
import top.jgroup.menucreator.menus.PersistentDataHelper;

/**
 * Кнопка, при клике на которую выполняется команда.
 */
public class CommandButton implements Button {
    private final String command;
    private final ItemStack item;

    /**
     * Создаёт кнопку для выполнения команды.
     *
     * @param command     команда для выполнения (без ведущего слеша).
     * @param material    материал для отображения предмета.
     * @param displayName название, отображаемое на кнопке.
     */
    public CommandButton(String command, Material material, String displayName) {
        this.command = command;
        this.item = new ItemStack(material);

        // Задаём displayName
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', displayName));
        item.setItemMeta(meta);

        // Сохраним "тип" кнопки в PDC (для наглядности)
        if (MenuCreatorAPI.getPlugin() != null) {
            PersistentDataHelper.setData(this.item, MenuCreatorAPI.getPlugin(), "buttonType", "command");
        }
    }

    @Override
    public void onClick(InventoryClickEvent event) {
        if (event.getWhoClicked() instanceof Player player) {
            // Выполняем команду
            player.performCommand(command);
        }
    }

    @Override
    public ItemStack getItem() {
        return item;
    }
}