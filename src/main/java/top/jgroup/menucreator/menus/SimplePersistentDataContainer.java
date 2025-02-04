package top.jgroup.menucreator.menus;

import org.bukkit.NamespacedKey;
import org.bukkit.persistence.PersistentDataAdapterContext;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Простейшая реализация PersistentDataContainer,
 * поддерживающая все методы интерфейса.
 * (Не поддерживает сложные адаптеры – используется для хранения простых значений.)
 */
public class SimplePersistentDataContainer implements PersistentDataContainer {
    private final Map<NamespacedKey, Object> data = new HashMap<>();

    /**
     * Сохраняет значение в контейнере по ключу.
     */
    @Override
    public <P, C> void set(NamespacedKey key, PersistentDataType<P, C> type, C value) {
        data.put(key, value);
    }

    /**
     * Проверяет, есть ли значение по ключу (и типу).
     */
    @Override
    public <P, C> boolean has(NamespacedKey key, PersistentDataType<P, C> type) {
        return data.containsKey(key);
    }

    /**
     * Проверяет, есть ли значение по ключу (без указания типа).
     */
    @Override
    public boolean has(NamespacedKey key) {
        return data.containsKey(key);
    }

    /**
     * Возвращает значение по ключу (и типу), либо null, если оно отсутствует.
     */
    @Override
    @SuppressWarnings("unchecked")
    public <P, C> C get(NamespacedKey key, PersistentDataType<P, C> type) {
        Object value = data.get(key);
        if (value == null) {
            return null;
        }
        return (C) value; // упрощённая логика
    }

    /**
     * Возвращает значение по ключу (и типу), либо defaultValue, если оно отсутствует.
     */
    @Override
    @SuppressWarnings("unchecked")
    public <P, C> C getOrDefault(NamespacedKey key, PersistentDataType<P, C> type, C defaultValue) {
        Object value = data.get(key);
        if (value == null) {
            return defaultValue;
        }
        return (C) value;
    }

    /**
     * Возвращает множество всех ключей, находящихся в контейнере.
     */
    @Override
    public Set<NamespacedKey> getKeys() {
        return data.keySet();
    }

    /**
     * Удаляет значение по ключу из контейнера.
     */
    @Override
    public void remove(NamespacedKey key) {
        data.remove(key);
    }

    /**
     * Проверяет, пуст ли контейнер.
     */
    @Override
    public boolean isEmpty() {
        return data.isEmpty();
    }

    /**
     * Копирует данные из этого контейнера в другой.
     *
     * @param target  контейнер, в который копируем
     * @param replace если true, существующие ключи в целевом контейнере будут заменены
     */
    @Override
    public void copyTo(PersistentDataContainer target, boolean replace) {
        if (!(target instanceof SimplePersistentDataContainer that)) {
            // Для простоты — если целевой контейнер не наш класс, выбрасываем исключение.
            throw new UnsupportedOperationException(
                    "Cannot copy to a different container implementation."
            );
        }

        for (Map.Entry<NamespacedKey, Object> entry : data.entrySet()) {
            NamespacedKey key = entry.getKey();
            Object value = entry.getValue();

            if (!replace && that.data.containsKey(key)) {
                // Если replace == false, не перезаписываем уже существующие ключи
                continue;
            }
            that.data.put(key, value);
        }
    }

    /**
     * Возвращает адаптер-контекст.
     * В упрощённой реализации обычно не используется,
     * поэтому выбрасываем исключение.
     */
    @Override
    public PersistentDataAdapterContext getAdapterContext() {
        throw new UnsupportedOperationException(
                "Adapter context is not implemented in SimplePersistentDataContainer."
        );
    }
}