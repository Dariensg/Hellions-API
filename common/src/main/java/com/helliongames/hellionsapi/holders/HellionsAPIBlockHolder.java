package com.helliongames.hellionsapi.holders;

import com.helliongames.hellionsapi.registration.BlockDataHolder;
import net.minecraft.resources.ResourceLocation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HellionsAPIBlockHolder {
    private static final List<HellionsAPIBlockHolder> MODULES = new ArrayList<>();

    private final String modid;

    public HellionsAPIBlockHolder(String modid) {
        this.modid = modid;
        MODULES.add(this);
    }

    /** Map of all EntityType Resource Locations to their EntityTypeDataHolders. */
    private final Map<ResourceLocation, BlockDataHolder> BLOCK_REGISTRY = new HashMap<>();

    /**
     public static final HellionsAPIBlockHolder BLOCK_MODULE = new HellionsAPIBlockHolder("examplemod");

     public static final BlockDataHolder EXAMPLE_BLOCK = BLOCK_MODULE.register("example_block", BlockDataHolder.of(() ->
         BlockDataHolder.Builder.of()
         .mapColor(MapColor.DIRT)
         .instrument(NoteBlockInstrument.BASEDRUM)
         .requiresCorrectToolForDrops()
         .strength(1.5F, 6.0F)
         .build()
     ));
     **/

    public BlockDataHolder register(String name, BlockDataHolder blockDataHolder) {
        ResourceLocation id = ResourceLocation.fromNamespaceAndPath(this.modid, name);
        this.BLOCK_REGISTRY.put(id, blockDataHolder);
        return blockDataHolder;
    }

    public Map<ResourceLocation, BlockDataHolder> getBlockRegistry() {
        return this.BLOCK_REGISTRY;
    }

    public static List<HellionsAPIBlockHolder> getModules() {
        return MODULES;
    }
}
