package com.helliongames.hellionsapi.module;

import com.helliongames.hellionsapi.holders.HellionsAPIItemHolder;
import com.helliongames.hellionsapi.registration.ItemDataHolder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;

import java.util.Map;

public class ItemModuleFabric {

    public static void registerItems() {
        for (HellionsAPIItemHolder module : HellionsAPIItemHolder.getModules()) {
            for (Map.Entry<ResourceLocation, ItemDataHolder<?>> entry : module.getItemRegistry().entrySet()) {
                // Register item
                Registry.register(BuiltInRegistries.ITEM, entry.getKey(), entry.getValue().get());
            }
        }
    }
}
