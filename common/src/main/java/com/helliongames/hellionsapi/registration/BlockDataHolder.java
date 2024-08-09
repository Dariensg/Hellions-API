package com.helliongames.hellionsapi.registration;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;

import java.util.function.Supplier;

public class BlockDataHolder<T extends Block> {
    private T cachedEntry;
    private final Supplier<T> entrySupplier;

    private ItemDataHolder<? extends Item> blockItem;

    public BlockDataHolder(Supplier<T> entrySupplier) {
        this.entrySupplier = entrySupplier;
    }

    public static BlockDataHolder<? extends Block> of(Supplier<?> blockSupplier) {
        return new BlockDataHolder(blockSupplier);
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

    public BlockDataHolder<? extends Block> withItem() {
        this.blockItem = ItemDataHolder.of(() -> ItemDataHolder.Builder.of((properties) -> new BlockItem(this.get(), properties), new Item.Properties()));
        return this;
    }

    public boolean hasItem() {
        return this.blockItem != null;
    }

    public ItemDataHolder<?> getBlockItem() {
        return this.blockItem;
    }

    /**
     * Builder for creating BlockDataHolders.
     * This mimics the default properties from BlockBehaviour.Properties
     */
    public static class Builder {
        private final BlockFactory<? extends Block> factory;
        private final BlockBehaviour.Properties properties;

        private Builder(BlockFactory<?> factory, BlockBehaviour.Properties properties) {
            this.factory = factory;
            this.properties = properties;
        }

        public static Builder of(BlockFactory<?> factory, BlockBehaviour.Properties properties) {
            return new Builder(factory, properties);
        }

        public <T extends Block> T build() {
            return (T) factory.create(this.properties);
        }

        @FunctionalInterface
        public interface BlockFactory<T extends Block> {
            T create(BlockBehaviour.Properties properties);
        }
    }
}
