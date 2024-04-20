package com.helliongames.hellionsmobs.client.renderer;

import com.helliongames.hellionsmobs.client.model.ArchdemonModel;
import com.helliongames.hellionsmobs.entity.ArchdemonEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class ArchdemonRenderer extends GeoEntityRenderer<ArchdemonEntity> {
    public ArchdemonRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new ArchdemonModel());
    }
}
