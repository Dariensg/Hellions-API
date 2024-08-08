package com.helliongames.hellionsapi;

import com.helliongames.hellionsapi.module.EntityTypeModuleFabric;
import net.fabricmc.api.ModInitializer;

public class HellionsAPI implements ModInitializer {
    
    @Override
    public void onInitialize() {
        HellionsAPICommon.init();

        EntityTypeModuleFabric.registerEntities();
    }
}
