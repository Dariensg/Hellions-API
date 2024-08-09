package com.helliongames.hellionsapi.registration;

import com.helliongames.hellionsapi.mixin.BlockBehaviourAccessor;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.flag.FeatureFlag;
import net.minecraft.world.flag.FeatureFlagSet;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import net.minecraft.world.level.storage.loot.BuiltInLootTables;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.phys.Vec3;

import javax.annotation.Nullable;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.function.ToIntFunction;

public class BlockDataHolder {
    private Block cachedEntry;
    private final Supplier<Block> entrySupplier;

    public BlockDataHolder(Supplier<Block> entrySupplier) {
        this.entrySupplier = entrySupplier;
    }

    public static BlockDataHolder of(Supplier<Block> blockSupplier) {
        return new BlockDataHolder(blockSupplier);
    }

    /**
     * Retrieves the cached entry if it exists, otherwise calls the supplier to create a new entry.
     * @return The cached entry, or a new entry if the cached entry does not exist.
     */
    public Block get() {
        if (this.cachedEntry != null) return cachedEntry;

        Block entry = entrySupplier.get();
        this.cachedEntry = entry;

        return entry;
    }

    /**
     * Builder for creating BlockDataHolders.
     * This mimics the default properties from BlockBehaviour.Properties
     */
    public static class Builder {
        Function<BlockState, MapColor> mapColor = p_284884_ -> MapColor.NONE;
        boolean hasCollision = true;
        SoundType soundType = SoundType.STONE;
        ToIntFunction<BlockState> lightEmission = p_60929_ -> 0;
        float explosionResistance;
        float destroyTime;
        boolean requiresCorrectToolForDrops;
        boolean isRandomlyTicking;
        float friction = 0.6F;
        float speedFactor = 1.0F;
        float jumpFactor = 1.0F;
        /**
         * Sets loot table information
         */
        ResourceKey<LootTable> drops;
        boolean canOcclude = true;
        boolean isAir;
        boolean ignitedByLava;
        @Deprecated
        boolean liquid;
        @Deprecated
        boolean forceSolidOff;
        boolean forceSolidOn;
        PushReaction pushReaction = PushReaction.NORMAL;
        boolean spawnTerrainParticles = true;
        NoteBlockInstrument instrument = NoteBlockInstrument.HARP;
        boolean replaceable;
        BlockBehaviour.StateArgumentPredicate<EntityType<?>> isValidSpawn = (p_284893_, p_284894_, p_284895_, p_284896_) -> p_284893_.isFaceSturdy(
                p_284894_, p_284895_, Direction.UP
        )
                && p_284893_.getLightEmission() < 14;
        BlockBehaviour.StatePredicate isRedstoneConductor = (p_284888_, p_284889_, p_284890_) -> p_284888_.isCollisionShapeFullBlock(p_284889_, p_284890_);
        BlockBehaviour.StatePredicate isSuffocating = (p_284885_, p_284886_, p_284887_) -> p_284885_.blocksMotion()
                && p_284885_.isCollisionShapeFullBlock(p_284886_, p_284887_);
        /**
         * If it blocks vision on the client side.
         */
        BlockBehaviour.StatePredicate isViewBlocking = this.isSuffocating;
        BlockBehaviour.StatePredicate hasPostProcess = (p_60963_, p_60964_, p_60965_) -> false;
        BlockBehaviour.StatePredicate emissiveRendering = (p_60931_, p_60932_, p_60933_) -> false;
        boolean dynamicShape;
        FeatureFlagSet requiredFeatures = FeatureFlags.VANILLA_SET;
        @Nullable
        BlockBehaviour.OffsetFunction offsetFunction;

        private Builder() {
        }

        public static Builder of() {
            return new Builder();
        }

        public Builder mapColor(DyeColor pMapColor) {
            this.mapColor = p_284892_ -> pMapColor.getMapColor();
            return this;
        }

        public Builder mapColor(MapColor pMapColor) {
            this.mapColor = p_222988_ -> pMapColor;
            return this;
        }

        public Builder mapColor(Function<BlockState, MapColor> pMapColor) {
            this.mapColor = pMapColor;
            return this;
        }

        public Builder noCollission() {
            this.hasCollision = false;
            this.canOcclude = false;
            return this;
        }

        public Builder noOcclusion() {
            this.canOcclude = false;
            return this;
        }

        public Builder friction(float pFriction) {
            this.friction = pFriction;
            return this;
        }

        public Builder speedFactor(float pSpeedFactor) {
            this.speedFactor = pSpeedFactor;
            return this;
        }

        public Builder jumpFactor(float pJumpFactor) {
            this.jumpFactor = pJumpFactor;
            return this;
        }

        public Builder sound(SoundType pSoundType) {
            this.soundType = pSoundType;
            return this;
        }

        public Builder lightLevel(ToIntFunction<BlockState> pLightEmission) {
            this.lightEmission = pLightEmission;
            return this;
        }

        public Builder strength(float pDestroyTime, float pExplosionResistance) {
            return this.destroyTime(pDestroyTime).explosionResistance(pExplosionResistance);
        }

        public Builder instabreak() {
            return this.strength(0.0F);
        }

        public Builder strength(float pStrength) {
            this.strength(pStrength, pStrength);
            return this;
        }

        public Builder randomTicks() {
            this.isRandomlyTicking = true;
            return this;
        }

        public Builder dynamicShape() {
            this.dynamicShape = true;
            return this;
        }

        public Builder noLootTable() {
            this.drops = BuiltInLootTables.EMPTY;
            return this;
        }

        public Builder dropsLike(Block pBlock) {
            this.drops = pBlock.getLootTable();
            return this;
        }

        public Builder ignitedByLava() {
            this.ignitedByLava = true;
            return this;
        }

        public Builder liquid() {
            this.liquid = true;
            return this;
        }

        public Builder forceSolidOn() {
            this.forceSolidOn = true;
            return this;
        }

        @Deprecated
        public Builder forceSolidOff() {
            this.forceSolidOff = true;
            return this;
        }

        public Builder pushReaction(PushReaction pPushReaction) {
            this.pushReaction = pPushReaction;
            return this;
        }

        public Builder air() {
            this.isAir = true;
            return this;
        }

        public Builder isValidSpawn(BlockBehaviour.StateArgumentPredicate<EntityType<?>> pIsValidSpawn) {
            this.isValidSpawn = pIsValidSpawn;
            return this;
        }

        public Builder isRedstoneConductor(BlockBehaviour.StatePredicate pIsRedstoneConductor) {
            this.isRedstoneConductor = pIsRedstoneConductor;
            return this;
        }

        public Builder isSuffocating(BlockBehaviour.StatePredicate pIsSuffocating) {
            this.isSuffocating = pIsSuffocating;
            return this;
        }

        /**
         * If it blocks vision on the client side.
         */
        public Builder isViewBlocking(BlockBehaviour.StatePredicate pIsViewBlocking) {
            this.isViewBlocking = pIsViewBlocking;
            return this;
        }

        public Builder hasPostProcess(BlockBehaviour.StatePredicate pHasPostProcess) {
            this.hasPostProcess = pHasPostProcess;
            return this;
        }

        public Builder emissiveRendering(BlockBehaviour.StatePredicate pEmissiveRendering) {
            this.emissiveRendering = pEmissiveRendering;
            return this;
        }

        public Builder requiresCorrectToolForDrops() {
            this.requiresCorrectToolForDrops = true;
            return this;
        }

        public Builder destroyTime(float pDestroyTime) {
            this.destroyTime = pDestroyTime;
            return this;
        }

        public Builder explosionResistance(float pExplosionResistance) {
            this.explosionResistance = Math.max(0.0F, pExplosionResistance);
            return this;
        }

        public Builder offsetType(BlockBehaviour.OffsetType pOffsetType) {
            this.offsetFunction = switch (pOffsetType) {
                case NONE -> null;
                case XZ -> (p_272565_, p_272566_, p_272567_) -> {
                    Block block = p_272565_.getBlock();
                    long i = Mth.getSeed(p_272567_.getX(), 0, p_272567_.getZ());
                    float f = ((BlockBehaviourAccessor) block).invokeGetMaxHorizontalOffset();
                    double d0 = Mth.clamp(((double)((float)(i & 15L) / 15.0F) - 0.5) * 0.5, -f, f);
                    double d1 = Mth.clamp(((double)((float)(i >> 8 & 15L) / 15.0F) - 0.5) * 0.5, -f, f);
                    return new Vec3(d0, 0.0, d1);
                };
                case XYZ -> (p_272562_, p_272563_, p_272564_) -> {
                    Block block = p_272562_.getBlock();
                    long i = Mth.getSeed(p_272564_.getX(), 0, p_272564_.getZ());
                    double d0 = ((double)((float)(i >> 4 & 15L) / 15.0F) - 1.0) * (double)((BlockBehaviourAccessor) block).invokeGetMaxVerticalOffset();
                    float f = ((BlockBehaviourAccessor) block).invokeGetMaxHorizontalOffset();
                    double d1 = Mth.clamp(((double)((float)(i & 15L) / 15.0F) - 0.5) * 0.5, -f, f);
                    double d2 = Mth.clamp(((double)((float)(i >> 8 & 15L) / 15.0F) - 0.5) * 0.5, -f, f);
                    return new Vec3(d1, d0, d2);
                };
            };
            return this;
        }

        public Builder noTerrainParticles() {
            this.spawnTerrainParticles = false;
            return this;
        }

        public Builder requiredFeatures(FeatureFlag... pRequiredFeatures) {
            this.requiredFeatures = FeatureFlags.REGISTRY.subset(pRequiredFeatures);
            return this;
        }

        public Builder instrument(NoteBlockInstrument pInstrument) {
            this.instrument = pInstrument;
            return this;
        }

        public Builder replaceable() {
            this.replaceable = true;
            return this;
        }
    }
}
