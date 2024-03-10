package com.helliongames.hellionsmobs.module;

import com.helliongames.hellionsmobs.HellionsMobsCommon;
import com.helliongames.hellionsmobs.client.renderer.KitsuneRenderer;
import com.helliongames.hellionsmobs.entity.KitsuneEntity;
import com.helliongames.hellionsmobs.registration.EntityTypeDataHolder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.MobCategory;

import java.util.HashMap;
import java.util.Map;

public class HellionsMobsEntityTypeModule {
    /** Map of all EntityType Resource Locations to their EntityTypeDataHolders. */
    private static final Map<ResourceLocation, EntityTypeDataHolder> ENTITY_TYPE_REGISTRY = new HashMap<>();

    public static final EntityTypeDataHolder<KitsuneEntity> KITSUNE = register("kitsune", EntityTypeDataHolder.of(() ->
            EntityTypeDataHolder.Builder.of(KitsuneEntity::new, MobCategory.CREATURE)
                    .sized(1.0f, 1.75f)
                    .build()
            )
            .attributes(KitsuneEntity::createKitsuneAttributes)
            .renderer(KitsuneRenderer::new));

    public static EntityTypeDataHolder register(String name, EntityTypeDataHolder entityTypeDataHolder) {
        ResourceLocation id = HellionsMobsCommon.id(name);
        ENTITY_TYPE_REGISTRY.put(id, entityTypeDataHolder);
        return entityTypeDataHolder;
    }

    public static Map<ResourceLocation, EntityTypeDataHolder> getEntityTypeRegistry() {
        return ENTITY_TYPE_REGISTRY;
    }

    // Called in the mod initializer / constructor in order to make sure that items are registered
    public static void loadClass() {}
}
