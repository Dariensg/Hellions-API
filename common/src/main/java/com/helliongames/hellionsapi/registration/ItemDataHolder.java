package com.helliongames.hellionsapi.registration;

import net.minecraft.world.item.Item;

import java.util.function.Supplier;

public class ItemDataHolder<T extends Item> {
    private T cachedEntry;
    private final Supplier<T> entrySupplier;

    public ItemDataHolder(Supplier<T> entrySupplier) {
        this.entrySupplier = entrySupplier;
    }

    public static ItemDataHolder<? extends Item> of(Supplier<?> itemSupplier) {
        return new ItemDataHolder(itemSupplier);
    }

    /**
     * Retrieves the cached entry if it exists, otherwise calls the supplier to create a new entry.
     * @return The cached entry, or a new entry if the cached entry does not exist.
     */
    public T get() {
        if (this.cachedEntry != null) return cachedEntry;

        T entry = entrySupplier.get();
        this.cachedEntry = entry;

        return entry;
    }

    /**
     * Builder for creating BlockDataHolders.
     * This mimics the default properties from Item.Properties
     */
    public static class Builder {
        private final ItemFactory<? extends Item> factory;
        private final Item.Properties properties;

        private Builder(ItemFactory<?> factory, Item.Properties properties) {
            this.factory = factory;
            this.properties = properties;
        }

        public static Builder of(ItemFactory<?> factory, Item.Properties properties) {
            return new Builder(factory, properties);
        }

        public <T extends Item> T build() {
            return (T) factory.create(this.properties);
        }

        @FunctionalInterface
        public interface ItemFactory<T extends Item> {
            T create(Item.Properties properties);
        }
    }
}
