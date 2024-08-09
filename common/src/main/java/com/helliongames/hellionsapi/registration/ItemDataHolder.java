package com.helliongames.hellionsapi.registration;

import net.minecraft.core.component.DataComponentMap;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.component.DataComponents;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.Unit;
import net.minecraft.world.flag.FeatureFlag;
import net.minecraft.world.flag.FeatureFlagSet;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.EitherHolder;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.JukeboxPlayable;
import net.minecraft.world.item.JukeboxSong;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.component.ItemAttributeModifiers;

import javax.annotation.Nullable;
import java.util.function.Supplier;

public class ItemDataHolder {
    private Item cachedEntry;
    private final Supplier<Item> entrySupplier;

    public ItemDataHolder(Supplier<Item> entrySupplier) {
        this.entrySupplier = entrySupplier;
    }

    public static ItemDataHolder of(Supplier<Item> itemSupplier) {
        return new ItemDataHolder(itemSupplier);
    }

    /**
     * Retrieves the cached entry if it exists, otherwise calls the supplier to create a new entry.
     * @return The cached entry, or a new entry if the cached entry does not exist.
     */
    public Item get() {
        if (this.cachedEntry != null) return cachedEntry;

        Item entry = entrySupplier.get();
        this.cachedEntry = entry;

        return entry;
    }

    /**
     * Builder for creating BlockDataHolders.
     * This mimics the default properties from Item.Properties
     */
    public static class Builder {
        @Nullable
        private DataComponentMap.Builder components;
        @Nullable
        Item craftingRemainingItem;
        FeatureFlagSet requiredFeatures = FeatureFlags.VANILLA_SET;

        private Builder() {
        }

        public static Builder of() {
            return new Builder();
        }

        public Builder food(FoodProperties pFood) {
            return this.component(DataComponents.FOOD, pFood);
        }

        public Builder stacksTo(int pMaxStackSize) {
            return this.component(DataComponents.MAX_STACK_SIZE, pMaxStackSize);
        }

        public Builder durability(int pMaxDamage) {
            this.component(DataComponents.MAX_DAMAGE, pMaxDamage);
            this.component(DataComponents.MAX_STACK_SIZE, 1);
            this.component(DataComponents.DAMAGE, 0);
            return this;
        }

        public Builder craftRemainder(Item pCraftingRemainingItem) {
            this.craftingRemainingItem = pCraftingRemainingItem;
            return this;
        }

        public Builder rarity(Rarity pRarity) {
            return this.component(DataComponents.RARITY, pRarity);
        }

        public Builder fireResistant() {
            return this.component(DataComponents.FIRE_RESISTANT, Unit.INSTANCE);
        }

        public Builder jukeboxPlayable(ResourceKey<JukeboxSong> pSong) {
            return this.component(DataComponents.JUKEBOX_PLAYABLE, new JukeboxPlayable(new EitherHolder<>(pSong), true));
        }

        public Builder requiredFeatures(FeatureFlag... pRequiredFeatures) {
            this.requiredFeatures = FeatureFlags.REGISTRY.subset(pRequiredFeatures);
            return this;
        }

        public <T> Builder component(DataComponentType<T> pComponent, T pValue) {
            if (this.components == null) {
                this.components = DataComponentMap.builder().addAll(DataComponents.COMMON_ITEM_COMPONENTS);
            }

            this.components.set(pComponent, pValue);
            return this;
        }

        public Builder attributes(ItemAttributeModifiers pAttributes) {
            return this.component(DataComponents.ATTRIBUTE_MODIFIERS, pAttributes);
        }
    }
}
