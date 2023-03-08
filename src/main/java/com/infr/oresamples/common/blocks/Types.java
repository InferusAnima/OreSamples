package com.infr.oresamples.common.blocks;

import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.RegistryObject;

import javax.annotation.Nullable;

public class Types {
    public enum Ores {
        LIGNITE_COAL("lignite", 2),
        BITUMINOUS_COAL("bituminous_coal", 2),
        ANTHRACITE_COAL("anthracite_coal", 2),
        COAL("coal", 2),
        CINNABAR("cinnabar", 0),
        GOLD("gold", 0),
        LAPIS("lapis", 5),
        QUARTZ("quartz", 5),
        KIMBERLITE("kimberlite", 7),
        BERYL("beryl", 7),
        NETHER_GOLD("nether_gold", 1),
        ANCIENT_DEBRIS("ancient_debris", 0),
        HEMATITE("hematite", 0),
        LIMONITE("limonite", 0),
        MALACHITE("malachite", 0),
        AZURITE("azurite", 0),
        CASSITERITE("cassiterite", 0),
        TEALLITE("teallite", 0),
        GALENA("galena", 0),
        BAUXITE("bauxite", 0),
        PLATINUM("platinum", 0),
        AUTUNITE("autunite", 0),
        SPHALERITE("sphalerite", 0);

        private final String unlocalizedName;
        private final int xp;

        private RegistryObject<Block> instance;
        private RegistryObject<Block> sample;

        Ores(String unlocalizedName, int xp) {
            this.unlocalizedName = unlocalizedName;
            this.xp = xp;
        }

        public String toString() {
            return this.unlocalizedName;
        }

        public String getUnlocalizedName() {
            return this.unlocalizedName;
        }

        public int getXp() {
            return this.xp;
        }

        @Nullable
        public RegistryObject<Block> getBlock() {
            return this.instance;
        }

        @Nullable
        public RegistryObject<Block> getSample() {
            return this.sample;
        }

        public void setBlock(RegistryObject<Block> b) {
            this.instance = b;
        }

        public void setSample(RegistryObject<Block> b) {
            this.sample = b;
        }
    }
}
