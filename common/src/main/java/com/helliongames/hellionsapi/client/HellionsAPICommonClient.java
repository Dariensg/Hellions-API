package com.helliongames.hellionsapi.client;

import com.helliongames.hellionsapi.module.HellionsAPIEntityRendererModule;

public class HellionsAPICommonClient {
    public static void init() {
        HellionsAPIEntityRendererModule.registerEntityRenderers();
    }
}
