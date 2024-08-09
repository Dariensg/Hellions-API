package com.helliongames.hellionsapi.holders;

import com.helliongames.hellionsapi.registration.ItemDataHolder;
import net.minecraft.resources.ResourceLocation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HellionsAPIItemHolder {
    private static final List<HellionsAPIItemHolder> MODULES = new ArrayList<>();

    private final String modid;

    public HellionsAPIItemHolder(String modid) {
        this.modid = modid;
        MODULES.add(this);
    }

    /** Map of all Item Resource Locations to their ItemDataHolders. */
    private final Map<ResourceLocation, ItemDataHolder<?>> ITEM_REGISTRY = new HashMap<>();

    /*
    public static final HellionsAPIItemHolder ITEM_MODULE = new HellionsAPIItemHolder("examplemod");

    public static final ItemDataHolder<Item> EXAMPLE_ITEM = ITEM_MODULE.register("example_item", ItemDataHolder.of(() ->
            ItemDataHolder.Builder.of(ExampleItem::new, new Item.Properties().stacksTo(16))
                    .build()
    ));
    */

    public ItemDataHolder<?> register(String name, ItemDataHolder<?> itemDataHolder) {
        ResourceLocation id = ResourceLocation.fromNamespaceAndPath(this.modid, name);
        this.ITEM_REGISTRY.put(id, itemDataHolder);
        return itemDataHolder;
    }

    public Map<ResourceLocation, ItemDataHolder<?>> getItemRegistry() {
        return this.ITEM_REGISTRY;
    }

    public static List<HellionsAPIItemHolder> getModules() {
        return MODULES;
    }
}
