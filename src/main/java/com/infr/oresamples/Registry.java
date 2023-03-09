package com.infr.oresamples;

import com.infr.oresamples.common.blocks.*;
import com.infr.oresamples.common.utils.Constants;
import com.infr.oresamples.common.utils.OreSamplesGroup;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.registries.ForgeRegistries;

public class Registry {

    public static void registerSample(RegistryEvent.Register<Block> event, Types.OreSamples o) {
        final String SAMPLE_REGISTRY_NAME = o.getUnlocalizedName().toLowerCase() + "_ore_sample";

        event.getRegistry().register(new SampleBlock(SAMPLE_REGISTRY_NAME));
    }

    public static void registerSampleItem(RegistryEvent.Register<Item> event, Types.OreSamples o) {
        final String SAMPLE_REGISTRY_NAME = o.getUnlocalizedName().toLowerCase() + "_ore_sample";
        final Block BLOCK = ForgeRegistries.BLOCKS.getValue(new ResourceLocation(Constants.MODID, SAMPLE_REGISTRY_NAME));
        o.setSample(BLOCK);

        event.getRegistry().register(itemOf(BLOCK).setRegistryName(SAMPLE_REGISTRY_NAME));
    }

    private static Item itemOf(Block block) {
        return new BlockItem(block, new Item.Properties().tab(OreSamplesGroup.getInstance()));
    }
}
