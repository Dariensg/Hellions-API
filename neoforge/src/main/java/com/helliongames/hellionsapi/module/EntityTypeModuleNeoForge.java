package com.helliongames.hellionsapi.module;

import com.helliongames.hellionsapi.registration.EntityTypeDataHolder;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.common.NeoForgeMod;
import net.neoforged.neoforge.event.entity.EntityAttributeCreationEvent;
import net.neoforged.neoforge.registries.RegisterEvent;

import java.util.Map;

@EventBusSubscriber(modid = "hellionsapi", bus = EventBusSubscriber.Bus.MOD)
public class EntityTypeModuleNeoForge {

    @SubscribeEvent
    public static void registerEntityType(RegisterEvent event) {
        for (HellionsAPIEntityTypeModule module : HellionsAPIEntityTypeModule.getModules()) {
            for (Map.Entry<ResourceLocation, EntityTypeDataHolder> entry : module.getEntityTypeRegistry().entrySet()) {
                // Register entity type
                event.register(Registries.ENTITY_TYPE, entityTypeRegisterHelper ->
                        entityTypeRegisterHelper.register(entry.getKey(), entry.getValue().get())
                );
            }
        }
    }

    @SubscribeEvent
    public static void registerEntityAttributes(EntityAttributeCreationEvent event) {
        for (HellionsAPIEntityTypeModule module : HellionsAPIEntityTypeModule.getModules()) {
            for (Map.Entry<ResourceLocation, EntityTypeDataHolder> entry : module.getEntityTypeRegistry().entrySet()) {
                // Register entity attributes

                AttributeSupplier.Builder builder = (AttributeSupplier.Builder) entry.getValue().getAttributesSupplier().get();
                // Attach required Forge attributes and register
                builder.add(NeoForgeMod.SWIM_SPEED)
                        .add(NeoForgeMod.NAMETAG_DISTANCE);

                event.put(entry.getValue().get(), builder.build());
            }
        }
    }
}
