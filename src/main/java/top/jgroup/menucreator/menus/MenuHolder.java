package top.jgroup.menucreator.menus;

import org.bukkit.NamespacedKey;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.persistence.PersistentDataAdapterContext;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import top.jgroup.menucreator.MenuCreatorAPI;

import java.util.Set;

/**
 * Кастомный InventoryHolder, реализующий PersistentDataContainer.
 * Позволяет хранить произвольные данные в меню, включая его название.
 */
public class MenuHolder implements InventoryHolder, PersistentDataContainer {
    private Inventory inventory;
    private final SimplePersistentDataContainer container = new SimplePersistentDataContainer();

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }

    @Override
    public Inventory getInventory() {
        return inventory;
    }

    /**
     * Записывает название меню в PDC, используя ключ "menuTitle".
     */
    public void setMenuTitle(String menuTitle) {
        if (MenuCreatorAPI.getPlugin() != null && menuTitle != null) {
            container.set(
                    new NamespacedKey(MenuCreatorAPI.getPlugin(), "menuTitle"),
                    PersistentDataType.STRING,
                    menuTitle
            );
        }
    }

    // Методы PersistentDataContainer делегируются "container"
    @Override
    public <T, Z> void set(NamespacedKey key, PersistentDataType<T, Z> type, Z value) {
        container.set(key, type, value);
    }

    @Override
    public <T, Z> Z get(NamespacedKey key, PersistentDataType<T, Z> type) {
        return container.get(key, type);
    }

    @Override
    public <P, C> C getOrDefault(NamespacedKey namespacedKey, PersistentDataType<P, C> persistentDataType, C c) {
        return null;
    }

    @Override
    public <T, Z> boolean has(NamespacedKey key, PersistentDataType<T, Z> type) {
        return container.has(key, type);
    }

    @Override
    public boolean has(NamespacedKey namespacedKey) {
        return false;
    }

    @Override
    public void remove(NamespacedKey key) {
        container.remove(key);
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public void copyTo(PersistentDataContainer persistentDataContainer, boolean b) {

    }

    @Override
    public Set<NamespacedKey> getKeys() {
        return container.getKeys();
    }

    @Override
    public PersistentDataAdapterContext getAdapterContext() {
        return container.getAdapterContext();
    }
}