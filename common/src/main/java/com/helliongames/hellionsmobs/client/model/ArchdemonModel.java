package com.helliongames.hellionsmobs.client.model;

import com.helliongames.hellionsmobs.HellionsMobsCommon;
import com.helliongames.hellionsmobs.entity.ArchdemonEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class ArchdemonModel extends GeoModel<ArchdemonEntity> {
    private final ResourceLocation MODEL = HellionsMobsCommon.id("geo/archdemon.geo.json");
    private final ResourceLocation TEXTURE = HellionsMobsCommon.id("textures/entity/archdemon.png");
    private final ResourceLocation ANIMATION = HellionsMobsCommon.id("animations/archdemon.animation.json");

    @Override
    public ResourceLocation getModelResource(ArchdemonEntity archdemonEntity) {
        return MODEL;
    }

    @Override
    public ResourceLocation getTextureResource(ArchdemonEntity archdemonEntity) {
        return TEXTURE;
    }

    @Override
    public ResourceLocation getAnimationResource(ArchdemonEntity archdemonEntity) {
        return ANIMATION;
    }
}
