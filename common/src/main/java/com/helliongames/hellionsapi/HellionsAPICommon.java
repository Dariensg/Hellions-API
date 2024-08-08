package com.helliongames.hellionsapi;

import com.helliongames.hellionsapi.module.HellionsMobsCreativeTabModule;
import com.helliongames.hellionsapi.module.HellionsMobsEntityTypeModule;
import net.minecraft.resources.ResourceLocation;

public class HellionsAPICommon {

    public static void init() {
        HellionsMobsEntityTypeModule.loadClass();
        HellionsMobsCreativeTabModule.loadClass();
    }

    public static ResourceLocation id(String name) {
        return new ResourceLocation(HellionsAPIConstants.MOD_ID, name);
    }
}