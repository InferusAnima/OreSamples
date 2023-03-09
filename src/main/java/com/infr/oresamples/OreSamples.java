package com.infr.oresamples;

import com.mojang.logging.LogUtils;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

@Mod("oresamples")
public class OreSamples
{
    private static OreSamples instance;
    public Logger LOGGER = LogUtils.getLogger();
    public OreSamples()
    {
        instance = this;

        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();

        Registry.register(bus);

        MinecraftForge.EVENT_BUS.register(this);
    }

    public static OreSamples getInstance() {
        return instance;
    }
}
