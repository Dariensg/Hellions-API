package com.helliongames.hellionsapi.datagen;

import com.helliongames.hellionsapi.datagen.loot.APIBlockLootTables;
import net.minecraft.data.PackOutput;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;

import java.util.List;
import java.util.Set;

public class APILootTableProvider {
    public static LootTableProvider create(PackOutput output) {
        return new LootTableProvider(output, Set.of(), List.of(new LootTableProvider.SubProviderEntry(APIBlockLootTables::new, LootContextParamSets.BLOCK)));
    }
}
