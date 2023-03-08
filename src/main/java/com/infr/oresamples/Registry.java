package com.infr.oresamples;

import com.infr.oresamples.common.blocks.*;
import com.infr.oresamples.common.utils.Constants;
import com.infr.oresamples.common.utils.OreSamplesGroup;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
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

        for (Types.Ores o : Types.Ores.values()) {
            registerOre(o);
        }

    }

    private static void registerOre(Types.Ores o) {
        BlockBehaviour.Properties stoneOreProperties = BlockBehaviour.Properties.of(Material.STONE, MaterialColor.STONE)
                .strength(5.0F, 10F).sound(SoundType.STONE).requiresCorrectToolForDrops();

        final String ORE_REGISTRY_NAME = o.getUnlocalizedName().toLowerCase() + "_ore";
        final String SAMPLE_REGISTRY_NAME = o.getUnlocalizedName().toLowerCase() + "_ore_sample";

        RegistryObject<Block> block = BLOCKS.register(ORE_REGISTRY_NAME,
                () -> new OreBlock(stoneOreProperties, o.getXp()));
        RegistryObject<Block> sample = BLOCKS.register(SAMPLE_REGISTRY_NAME, SampleBlock::new);
        o.setBlock(block);
        o.setSample(sample);

        ITEMS.register(ORE_REGISTRY_NAME, itemOf(block));
        ITEMS.register(SAMPLE_REGISTRY_NAME, itemOf(sample));
    }

    private static Supplier<Item> itemOf(RegistryObject<Block> block) {
        return () -> new BlockItem(block.get(), new Item.Properties().tab(OreSamplesGroup.getInstance()));
    }
}
