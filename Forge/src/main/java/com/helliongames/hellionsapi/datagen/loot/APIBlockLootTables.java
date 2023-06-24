package com.helliongames.hellionsapi.datagen.loot;

import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.level.block.Block;

import java.util.Collections;

public class APIBlockLootTables extends BlockLootSubProvider {

    public APIBlockLootTables() {
        super(Collections.emptySet(), FeatureFlags.REGISTRY.allFlags());
    }

    @Override
    public void generate() {

    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return super.getKnownBlocks();
    }
}
