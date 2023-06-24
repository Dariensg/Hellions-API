package com.helliongames.hellionsapi.registration.util;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.Item;

import java.util.function.Supplier;

public class ItemRegistryUtil {
    public static RegistrationProvider<Item> ITEMS;

    public static void createBlockRegistry(String modid) {
        ITEMS = RegistrationProvider.get(Registries.ITEM, modid);
    }

    public static RegistryObject<Item> registerDefaultItem(String identifier, Supplier<Item> item) {
        return ITEMS.register(identifier, item);
    }
}
