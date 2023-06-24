package com.helliongames.hellionsapi.registration.util;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.Block;

public class BlockRegistryUtil {
    public static RegistrationProvider<Block> BLOCKS;

    public static void createBlockRegistry(String modid) {
        BLOCKS = RegistrationProvider.get(Registries.BLOCK, modid);
    }

//    public static BlockRegistryObject<Block> registerBlock(String identifier, Supplier<Block> block) {
//        return BLOCKS.register(identifier, block);
//    }
}
