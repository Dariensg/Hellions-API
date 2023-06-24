package com.helliongames.hellionsapi.datagen;

import com.helliongames.hellionsapi.Constants;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Constants.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class DataGenerators {

    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        PackOutput packOutput = generator.getPackOutput();
        ExistingFileHelper existingFileHelper = event.getExistingFileHelper();

        generator.addProvider(true, new APIRecipeProvider(packOutput));
        generator.addProvider(true, APILootTableProvider.create(packOutput));
        generator.addProvider(true, new APIBlockStateProvider(packOutput, Constants.MOD_ID, existingFileHelper));
        generator.addProvider(true, new APIItemModelProvider(packOutput, Constants.MOD_ID, existingFileHelper));
    }
}
