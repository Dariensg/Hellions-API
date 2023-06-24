package com.helliongames.hellionsapi.registration.util;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public interface BlockRegistryObject<T extends Block> extends RegistryObject<T> {

    default RegistryObject<?> withDefaultItem() {
        ItemRegistryUtil.registerDefaultItem(this.getId().getPath(), () -> new BlockItem(this.get(), new Item.Properties()));
        return this;
    }

//    default RegistryObject<?> withWood() {
//        ItemRegistryUtil.registerDefaultItem(this.getId().getPath(), () -> new Block(this.get(), new Item.Properties()));
//        return this;
//    }
//
//    default RegistryObject<?> withStrippedWood() {
//        ItemRegistryUtil.registerDefaultItem(this.getId().getPath(), () -> new Block(this.get(), new Item.Properties()));
//        return this;
//    }
//
//    default RegistryObject<?> withStrippedLog() {
//        ItemRegistryUtil.registerDefaultItem(this.getId().getPath(), () -> new Block(this.get(), new Item.Properties()));
//        return this;
//    }
//
//    default RegistryObject<?> withPlanks() {
//        String plankIdentifier = this.getId().getPath().replace("_log", "_planks");
//
//        BlockRegistryUtil.registerBlock(plankIdentifier, () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.WOOD).instrument(NoteBlockInstrument.BASS).strength(2.0f, 3.0f).sound(SoundType.WOOD).ignitedByLava())).;
//        return this;
//    }
//
//    default RegistryObject<?> withSlabs() {
//        ItemRegistryUtil.registerDefaultItem(this.getId().getPath(), () -> new StairBlock(this.get(), new Item.Properties()));
//        return this;
//    }
//
//    default RegistryObject<?> withStairs() {
//        ItemRegistryUtil.registerDefaultItem(this.getId().getPath(), () -> new StairBlock(this.get(), new Item.Properties()));
//        return this;
//    }
//
//    default RegistryObject<?> withFence() {
//        ItemRegistryUtil.registerDefaultItem(this.getId().getPath(), () -> new StairBlock(this.get(), new Item.Properties()));
//        return this;
//    }
//
//    default RegistryObject<?> withFenceGate() {
//        ItemRegistryUtil.registerDefaultItem(this.getId().getPath(), () -> new StairBlock(this.get(), new Item.Properties()));
//        return this;
//    }
//
//    default RegistryObject<?> withDoor() {
//        ItemRegistryUtil.registerDefaultItem(this.getId().getPath(), () -> new StairBlock(this.get(), new Item.Properties()));
//        return this;
//    }
//
//    default RegistryObject<?> withTrapdoor() {
//        ItemRegistryUtil.registerDefaultItem(this.getId().getPath(), () -> new StairBlock(this.get(), new Item.Properties()));
//        return this;
//    }
//
//    default RegistryObject<?> withSign() {
//        ItemRegistryUtil.registerDefaultItem(this.getId().getPath(), () -> new StairBlock(this.get(), new Item.Properties()));
//        return this;
//    }
//
//    default RegistryObject<?> withHangingSign() {
//        ItemRegistryUtil.registerDefaultItem(this.getId().getPath(), () -> new StairBlock(this.get(), new Item.Properties()));
//        return this;
//    }
//
//    default RegistryObject<?> withBoat() {
//        ItemRegistryUtil.registerDefaultItem(this.getId().getPath(), () -> new BoatItem(this.get(), new Item.Properties()));
//        return this;
//    }

}
