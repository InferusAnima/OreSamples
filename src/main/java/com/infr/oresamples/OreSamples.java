package com.infr.oresamples;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod("oresamples")
public class OreSamples
{
    public OreSamples()
    {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();

        Registry.register(bus);

        MinecraftForge.EVENT_BUS.register(this);
    }
}
