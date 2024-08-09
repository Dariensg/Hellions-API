package com.helliongames.hellionsapi.client;

import com.helliongames.hellionsapi.holders.HellionsAPIEntityRendererHolder;

public class HellionsAPICommonClient {
    public static void init() {
        HellionsAPIEntityRendererHolder.loadClass();
    }
}
