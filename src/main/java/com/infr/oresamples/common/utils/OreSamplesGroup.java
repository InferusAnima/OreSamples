package com.infr.oresamples.common.utils;

import com.infr.oresamples.common.blocks.Types.OreSamples;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.registries.ForgeRegistries;

public class OreSamplesGroup extends CreativeModeTab {
    private static OreSamplesGroup instance;

    private int counter = -1;
    private long lastTick = 0L;
    private ItemStack display = new ItemStack(Blocks.STONE);

    private OreSamplesGroup() {
        super("oresamples.name");
    }

    public static OreSamplesGroup getInstance() {
        if (instance == null) {
            instance = new OreSamplesGroup();
        }
        return instance;
    }

    @Override
    public ItemStack makeIcon() {
        return new ItemStack(Blocks.STONE);
    }

    @Override
    public ItemStack getIconItem() {
        // Init the anim only when the first ore is init'd
        if (OreSamples.DIAMOND.getSample() != null && counter == -1) {
            counter = 0;
        }

        com.infr.oresamples.OreSamples.getInstance().LOGGER.info("KISS" + ForgeRegistries.BLOCKS.tags().getTagNames().toList());

        if (System.currentTimeMillis() - lastTick > 1000L) {
            display = new ItemStack(OreSamples.values()[counter].getSample());
            counter = counter == (OreSamples.values().length - 1) ? 0 : counter + 1;
            lastTick = System.currentTimeMillis();
        }
        return display;
    }
}
