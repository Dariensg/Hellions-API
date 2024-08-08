package com.helliongames.hellionsapi;

import net.minecraft.resources.ResourceLocation;

public class HellionsAPICommon {

    public static void init() {
    }

    public static ResourceLocation id(String name) {
        return ResourceLocation.fromNamespaceAndPath(HellionsAPIConstants.MOD_ID, name);
    }
}