package com.helliongames.hellionsapi.holders;

import com.helliongames.hellionsapi.registration.BlockDataHolder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;

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

    /** Map of all Block Resource Locations to their BlockDataHolders. */
    private final Map<ResourceLocation, BlockDataHolder<? extends Block>> BLOCK_REGISTRY = new HashMap<>();

/*
     public static final HellionsAPIBlockHolder BLOCK_MODULE = new HellionsAPIBlockHolder("examplemod");

     public static final BlockDataHolder<Block> EXAMPLE_BLOCK = BLOCK_MODULE.register("example_block", BlockDataHolder.of(() ->
         BlockDataHolder.Builder.of(ExampleBlock::new,
         BlockBehaviour.Properties.of()
             .mapColor(MapColor.DIRT)
             .instrument(NoteBlockInstrument.BASEDRUM)
             .requiresCorrectToolForDrops()
             .strength(1.5F, 6.0F)
         )
         .build()
     ));
*/

    public BlockDataHolder<? extends Block> register(String name, BlockDataHolder<? extends Block> blockDataHolder) {
        ResourceLocation id = ResourceLocation.fromNamespaceAndPath(this.modid, name);
        this.BLOCK_REGISTRY.put(id, blockDataHolder);
        return blockDataHolder;
    }

    public Map<ResourceLocation, BlockDataHolder<? extends Block>> getBlockRegistry() {
        return this.BLOCK_REGISTRY;
    }

    public static List<HellionsAPIBlockHolder> getModules() {
        return MODULES;
    }
}
