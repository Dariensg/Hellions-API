package com.helliongames.hellionsapi.module;

import com.helliongames.hellionsapi.holders.HellionsAPIBlockHolder;
import com.helliongames.hellionsapi.registration.BlockDataHolder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;

import java.util.Map;

public class BlockModuleFabric {

    public static void registerBlocks() {
        for (HellionsAPIBlockHolder module : HellionsAPIBlockHolder.getModules()) {
            for (Map.Entry<ResourceLocation, BlockDataHolder<?>> entry : module.getBlockRegistry().entrySet()) {
                // Register block
                Registry.register(BuiltInRegistries.BLOCK, entry.getKey(), entry.getValue().get());

                // Register the block items
                if (entry.getValue().hasItem()) {
                    Registry.register(BuiltInRegistries.ITEM, entry.getKey(), entry.getValue().getBlockItem().get());
                }
            }
        }
    }
}
