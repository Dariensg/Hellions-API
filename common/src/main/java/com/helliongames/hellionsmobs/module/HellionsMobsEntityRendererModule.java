package com.helliongames.hellionsmobs.module;

import com.helliongames.hellionsmobs.client.renderer.ArchdemonRenderer;
import com.helliongames.hellionsmobs.registration.EntityTypeDataHolder;
import net.minecraft.client.renderer.entity.EntityRendererProvider;

import java.util.HashMap;
import java.util.Map;

public class HellionsMobsEntityRendererModule {
    /** Map of all EntityTypes to their EntityRendererProviders. */
    private static final Map<EntityTypeDataHolder, EntityRendererProvider> ENTITY_RENDERER_REGISTRY = new HashMap<>();

    public static void register(EntityTypeDataHolder entityTypeDataHolder, EntityRendererProvider rendererProvider) {
        ENTITY_RENDERER_REGISTRY.put(entityTypeDataHolder, rendererProvider);
    }

    public static Map<EntityTypeDataHolder, EntityRendererProvider> getEntityRendererRegistry() {
        return ENTITY_RENDERER_REGISTRY;
    }

    // Called in the mod initializer / constructor in order to make sure that items are registered
    public static void registerEntityRenderers() {
        register(HellionsMobsEntityTypeModule.ARCHDEMON, ArchdemonRenderer::new);
    }
}
