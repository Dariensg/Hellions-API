package com.helliongames.hellionsapi.module;

import com.helliongames.hellionsapi.holders.HellionsAPIBlockHolder;
import com.helliongames.hellionsapi.holders.HellionsAPIEntityHolder;
import com.helliongames.hellionsapi.holders.HellionsAPIItemHolder;
import com.helliongames.hellionsapi.registration.BlockDataHolder;
import com.helliongames.hellionsapi.registration.EntityTypeDataHolder;
import com.helliongames.hellionsapi.registration.ItemDataHolder;
import net.minecraft.core.registries.BuiltInRegistries;
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

        if (event.getRegistry().equals(BuiltInRegistries.ENTITY_TYPE)) {
            for (HellionsAPIEntityHolder module : HellionsAPIEntityHolder.getModules()) {
                for (Map.Entry<ResourceLocation, EntityTypeDataHolder> entry : module.getEntityTypeRegistry().entrySet()) {
                    // Register entity type
                    event.register(Registries.ENTITY_TYPE, entityTypeRegisterHelper ->
                            entityTypeRegisterHelper.register(entry.getKey(), entry.getValue().get())
                    );
                }
            }
        } else if (event.getRegistry().equals(BuiltInRegistries.ITEM)) {
            for (HellionsAPIItemHolder module : HellionsAPIItemHolder.getModules()) {
                for (Map.Entry<ResourceLocation, ItemDataHolder> entry : module.getItemRegistry().entrySet()) {
                    // Register item
                    event.register(Registries.ITEM, itemRegistryHelper ->
                            itemRegistryHelper.register(entry.getKey(), entry.getValue().get())
                    );
                }
            }
        } else if (event.getRegistry().equals(BuiltInRegistries.BLOCK)) {
            for (HellionsAPIBlockHolder module : HellionsAPIBlockHolder.getModules()) {
                for (Map.Entry<ResourceLocation, BlockDataHolder> entry : module.getBlockRegistry().entrySet()) {
                    // Register block
                    event.register(Registries.BLOCK, blockRegistryHelper ->
                            blockRegistryHelper.register(entry.getKey(), entry.getValue().get())
                    );
                }
            }
        }

    }

    @SubscribeEvent
    public static void registerEntityAttributes(EntityAttributeCreationEvent event) {
        for (HellionsAPIEntityHolder module : HellionsAPIEntityHolder.getModules()) {
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
