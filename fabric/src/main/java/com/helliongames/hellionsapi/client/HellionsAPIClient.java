package com.helliongames.hellionsapi.client;

import com.helliongames.hellionsapi.holders.HellionsAPIEntityRendererHolder;
import com.helliongames.hellionsapi.registration.EntityTypeDataHolder;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.renderer.entity.EntityRendererProvider;

import java.util.Map;

public class HellionsAPIClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        HellionsAPICommonClient.init();
        registerEntityRenderers();
    }

    private void registerEntityRenderers() {
        for (Map.Entry<EntityTypeDataHolder, EntityRendererProvider> entry : HellionsAPIEntityRendererHolder.getEntityRendererRegistry().entrySet()) {
            // Register entity renderers
            EntityRendererRegistry.register(entry.getKey().get(), entry.getValue());
        }
    }
}
