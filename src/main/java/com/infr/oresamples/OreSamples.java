package com.infr.oresamples;

import com.infr.oresamples.common.blocks.Types;
import com.mojang.logging.LogUtils;
import net.minecraft.nbt.Tag;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.Tags;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.ForgeRegistry;
import net.minecraftforge.registries.RegistryManager;
import org.slf4j.Logger;

@Mod("oresamples")
public class OreSamples
{
    private static OreSamples instance;
    public Logger LOGGER = LogUtils.getLogger();
    public OreSamples()
    {
        instance = this;

        MinecraftForge.EVENT_BUS.register(this);
    }

    @Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class RegistryEvents
    {
        @SubscribeEvent(priority = EventPriority.LOWEST)
        public static void onBlocksRegistry(final RegistryEvent.Register<Block> blockRegistryEvent)
        {
            for (Types.OreSamples o : Types.OreSamples.values()) {
                Registry.registerSample(blockRegistryEvent, o);
            }

            OreSamples.getInstance().LOGGER.info("LMAO" + ForgeRegistries.BLOCKS.tags());
        }

        @SubscribeEvent(priority = EventPriority.LOWEST)
        public static void onItemsRegistry(final RegistryEvent.Register<Item> itemRegistryEvent)
        {
            for (Types.OreSamples o : Types.OreSamples.values()) {
                Registry.registerSampleItem(itemRegistryEvent, o);
            }
        }
    }

    public static OreSamples getInstance() {
        return instance;
    }
}
