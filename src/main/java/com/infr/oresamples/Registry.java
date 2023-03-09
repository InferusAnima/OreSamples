package com.infr.oresamples;

import com.infr.oresamples.common.blocks.*;
import com.infr.oresamples.common.utils.Constants;
import com.infr.oresamples.common.utils.OreSamplesGroup;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class Registry {
    public static DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, Constants.MODID);
    public static DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Constants.MODID);

    public static void register(IEventBus modbus) {
        registerBlocks();
        BLOCKS.register(modbus);
        ITEMS.register(modbus);
    }

    private static void registerBlocks() {

        for (Types.OreSamples o : Types.OreSamples.values()) {
            registerSample(o);
        }

//        for (Block ore : ForgeRegistries.BLOCKS.getValues()) {
//            if (ForgeRegistries.BLOCKS.tags().getTag(Tags.Blocks.ORES).contains(ore)) {
//                OreSamples.getInstance().LOGGER.info("LMAO" + ore.getRegistryName());
//                registerSample(ore);
//            }
//        }

    }

    private static void registerSample(Types.OreSamples o) {
        final String SAMPLE_REGISTRY_NAME = o.getUnlocalizedName().toLowerCase() + "_ore_sample";

        RegistryObject<Block> sample = BLOCKS.register(SAMPLE_REGISTRY_NAME, SampleBlock::new);
        o.setSample(sample);

        ITEMS.register(SAMPLE_REGISTRY_NAME, itemOf(sample));
    }

    private static Supplier<Item> itemOf(RegistryObject<Block> block) {
        return () -> new BlockItem(block.get(), new Item.Properties().tab(OreSamplesGroup.getInstance()));
    }
}
