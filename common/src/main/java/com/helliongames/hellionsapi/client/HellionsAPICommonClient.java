package com.helliongames.hellionsapi.client;

import com.helliongames.hellionsapi.module.HellionsMobsEntityRendererModule;

public class HellionsAPICommonClient {
    public static void init() {
        HellionsMobsEntityRendererModule.registerEntityRenderers();
    }
}
