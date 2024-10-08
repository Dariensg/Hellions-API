package com.helliongames.hellionsapi.registration;

import com.google.common.collect.ImmutableSet;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityAttachment;
import net.minecraft.world.entity.EntityAttachments;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.flag.FeatureFlag;
import net.minecraft.world.flag.FeatureFlagSet;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.phys.Vec3;

import java.util.function.Supplier;

public class EntityTypeDataHolder<T extends Entity> {
    private EntityType<T> cachedEntry;
    private final Supplier<EntityType<T>> entrySupplier;

    private Supplier<AttributeSupplier.Builder> attributesBuilderSupplier;

    public EntityTypeDataHolder(Supplier<EntityType<T>> entrySupplier) {
        this.entrySupplier = entrySupplier;
    }

    public static <U extends Entity> EntityTypeDataHolder<U> of(Supplier<EntityType<U>> entityTypeSupplier) {
        return new EntityTypeDataHolder<>(entityTypeSupplier);
    }

    /**
     * Retrieves the cached entry if it exists, otherwise calls the supplier to create a new entry.
     * @return The cached entry, or a new entry if the cached entry does not exist.
     */
    public EntityType<T> get() {
        if (this.cachedEntry != null) return cachedEntry;

        EntityType<T> entry = entrySupplier.get();
        this.cachedEntry = entry;

        return entry;
    }

    public EntityTypeDataHolder<T> attributes(Supplier<AttributeSupplier.Builder> attributesBuilderSupplier) {
        this.attributesBuilderSupplier = attributesBuilderSupplier;
        return this;
    }

    public boolean hasAttributes() {
        return this.attributesBuilderSupplier != null;
    }

    public Supplier<AttributeSupplier.Builder> getAttributesSupplier() {
        return this.attributesBuilderSupplier;
    }

    /**
     * Builder for creating EntityTypeDataHolders.
     * This directly mirrors the vanilla EntityType.Builder.
     */
    public static class Builder<T extends Entity> {
        private final EntityType.EntityFactory<T> factory;
        private final MobCategory category;
        private ImmutableSet<Block> immuneTo = ImmutableSet.of();
        private boolean serialize = true;
        private boolean summon = true;
        private boolean fireImmune;
        private boolean canSpawnFarFromPlayer;
        private int clientTrackingRange = 5;
        private int updateInterval = 3;
        private EntityDimensions dimensions = EntityDimensions.scalable(0.6F, 1.8F);
        private float spawnDimensionsScale = 1.0f;
        private EntityAttachments.Builder attachments = EntityAttachments.builder();
        private FeatureFlagSet requiredFeatures = FeatureFlags.VANILLA_SET;

        private Builder(EntityType.EntityFactory<T> entityFactory, MobCategory mobCategory) {
            this.factory = entityFactory;
            this.category = mobCategory;
            this.canSpawnFarFromPlayer = mobCategory == MobCategory.CREATURE || mobCategory == MobCategory.MISC;
        }

        public static <T extends Entity> Builder<T> of(EntityType.EntityFactory<T> entityFactory, MobCategory mobCategory) {
            return new Builder<>(entityFactory, mobCategory);
        }

        public Builder<T> sized(float width, float height) {
            this.dimensions = EntityDimensions.scalable(width, height);
            return this;
        }

        public Builder<T> spawnDimensionsScale(float scale) {
            this.spawnDimensionsScale = scale;
            return this;
        }

        public Builder<T> noSummon() {
            this.summon = false;
            return this;
        }

        public Builder<T> noSave() {
            this.serialize = false;
            return this;
        }

        public Builder<T> fireImmune() {
            this.fireImmune = true;
            return this;
        }

        public Builder<T> immuneTo(Block... blocks) {
            this.immuneTo = ImmutableSet.copyOf(blocks);
            return this;
        }

        public Builder<T> canSpawnFarFromPlayer() {
            this.canSpawnFarFromPlayer = true;
            return this;
        }

        public Builder<T> clientTrackingRange(int chunkRange) {
            this.clientTrackingRange = chunkRange;
            return this;
        }

        public Builder<T> updateInterval(int interval) {
            this.updateInterval = interval;
            return this;
        }

        public Builder<T> passengerAttachments(float... pAttachPoints) {
            for (float f : pAttachPoints) {
                this.attachments = this.attachments.attach(EntityAttachment.PASSENGER, 0.0F, f, 0.0F);
            }

            return this;
        }

        public Builder<T> passengerAttachments(Vec3... pAttachPoints) {
            for (Vec3 vec3 : pAttachPoints) {
                this.attachments = this.attachments.attach(EntityAttachment.PASSENGER, vec3);
            }

            return this;
        }

        public Builder<T> vehicleAttachment(Vec3 pAttachPoint) {
            return this.attach(EntityAttachment.VEHICLE, pAttachPoint);
        }

        public Builder<T> ridingOffset(float pRidingOffset) {
            return this.attach(EntityAttachment.VEHICLE, 0.0F, -pRidingOffset, 0.0F);
        }

        public Builder<T> nameTagOffset(float pNameTagOffset) {
            return this.attach(EntityAttachment.NAME_TAG, 0.0F, pNameTagOffset, 0.0F);
        }

        public Builder<T> attach(EntityAttachment pAttachment, float pX, float pY, float pZ) {
            this.attachments = this.attachments.attach(pAttachment, pX, pY, pZ);
            return this;
        }

        public Builder<T> attach(EntityAttachment pAttachment, Vec3 pPos) {
            this.attachments = this.attachments.attach(pAttachment, pPos);
            return this;
        }

        public Builder<T> requiredFeatures(FeatureFlag... $$0) {
            this.requiredFeatures = FeatureFlags.REGISTRY.subset($$0);
            return this;
        }

        public EntityType<T> build() {
            return new EntityType<>(this.factory, this.category, this.serialize, this.summon, this.fireImmune, this.canSpawnFarFromPlayer, this.immuneTo, this.dimensions, this.spawnDimensionsScale, this.clientTrackingRange, this.updateInterval, this.requiredFeatures);
        }
    }
}
