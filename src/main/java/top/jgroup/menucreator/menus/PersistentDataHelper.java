package top.jgroup.menucreator.menus;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.Plugin;
import top.jgroup.menucreator.MenuCreatorAPI;

/**
 * Вспомогательный класс для работы с PersistentDataContainer у ItemStack.
 */
public class PersistentDataHelper {

    /**
     * Создаёт предмет с заданным "названием" (DisplayName) и
     * автоматически записывает это название в PDC по ключу "itemName".
     *
     * @param material    тип материала
     * @param displayName имя предмета (будет установлен как displayName)
     * @return ItemStack, у которого и DisplayName, и PDC["itemName"] = displayName
     */
    public static ItemStack createNamedItem(Material material, String displayName) {
        ItemStack item = new ItemStack(material);
        ItemMeta meta = item.getItemMeta();

        if (meta != null) {
            String coloredName = ChatColor.translateAlternateColorCodes('&', displayName);
            meta.setDisplayName(coloredName);

            // Запишем в PDC
            if (MenuCreatorAPI.getPlugin() != null) {
                NamespacedKey key = new NamespacedKey(MenuCreatorAPI.getPlugin(), "itemName");
                meta.getPersistentDataContainer().set(key, PersistentDataType.STRING, coloredName);
            }
            item.setItemMeta(meta);
        }
        return item;
    }

    /**
     * Устанавливает произвольное строковое значение в PDC предмета.
     */
    public static ItemStack setData(ItemStack item, Plugin plugin, String key, String value) {
        ItemMeta meta = item.getItemMeta();
        if (meta != null) {
            meta.getPersistentDataContainer().set(new NamespacedKey(plugin, key), PersistentDataType.STRING, value);
            item.setItemMeta(meta);
        }
        return item;
    }
}