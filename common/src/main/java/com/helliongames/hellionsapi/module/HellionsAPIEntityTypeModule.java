package com.helliongames.hellionsapi.module;

import com.helliongames.hellionsapi.HellionsAPICommon;
import com.helliongames.hellionsapi.registration.EntityTypeDataHolder;
import net.minecraft.resources.ResourceLocation;

import java.util.HashMap;
import java.util.Map;

public class HellionsAPIEntityTypeModule {
    /** Map of all EntityType Resource Locations to their EntityTypeDataHolders. */
    private static final Map<ResourceLocation, EntityTypeDataHolder> ENTITY_TYPE_REGISTRY = new HashMap<>();

    public static EntityTypeDataHolder register(String name, EntityTypeDataHolder entityTypeDataHolder) {
        ResourceLocation id = HellionsAPICommon.id(name);
        ENTITY_TYPE_REGISTRY.put(id, entityTypeDataHolder);
        return entityTypeDataHolder;
    }

    public static Map<ResourceLocation, EntityTypeDataHolder> getEntityTypeRegistry() {
        return ENTITY_TYPE_REGISTRY;
    }

    // Called in the mod initializer / constructor in order to make sure that items are registered
    public static void loadClass() {}
}