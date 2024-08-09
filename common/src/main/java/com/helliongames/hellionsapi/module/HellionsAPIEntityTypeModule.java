package com.helliongames.hellionsapi.module;

import com.helliongames.hellionsapi.registration.EntityTypeDataHolder;
import net.minecraft.resources.ResourceLocation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HellionsAPIEntityTypeModule {
    private static final List<HellionsAPIEntityTypeModule> MODULES = new ArrayList<>();

    private final String modid;

    public HellionsAPIEntityTypeModule(String modid) {
        this.modid = modid;
        MODULES.add(this);
    }

    /** Map of all EntityType Resource Locations to their EntityTypeDataHolders. */
    private final Map<ResourceLocation, EntityTypeDataHolder> ENTITY_TYPE_REGISTRY = new HashMap<>();

    /**
     public static final HellionsAPIEntityTypeModule ENTITY_TYPE_MODULE = new HellionsAPIEntityTypeModule("examplemod");

     public static final EntityTypeDataHolder<ExampleEntity> EXAMPLE = ENTITY_TYPE_MODULE.register("example", EntityTypeDataHolder.of(() ->
                    EntityTypeDataHolder.Builder.of(ExampleEntity::new, MobCategory.CREATURE)
                            .sized(1.0f, 3.0f)
                            .build()
            )
            .attributes(ExampleEntity::createExampleEntityAttributes));
    **/

    public EntityTypeDataHolder register(String name, EntityTypeDataHolder entityTypeDataHolder) {
        ResourceLocation id = ResourceLocation.fromNamespaceAndPath(this.modid, name);
        this.ENTITY_TYPE_REGISTRY.put(id, entityTypeDataHolder);
        return entityTypeDataHolder;
    }

    public Map<ResourceLocation, EntityTypeDataHolder> getEntityTypeRegistry() {
        return this.ENTITY_TYPE_REGISTRY;
    }

    public static List<HellionsAPIEntityTypeModule> getModules() {
        return MODULES;
    }

    // Called in the mod initializer / constructor in order to make sure that items are registered
    public static void loadClass() {}
}