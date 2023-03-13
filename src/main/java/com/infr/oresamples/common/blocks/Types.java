package com.infr.oresamples.common.blocks;

import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.RegistryObject;

import javax.annotation.Nullable;

public class Types {
    public enum OreSamples {
        COAL("coal"),
        LAPIS("lapis"),
        REDSTONE("redstone"),
        COPPER("copper"),
        IRON("iron"),
        GOLD("gold"),
        DIAMOND("diamond"),
        EMERALD("emerald"),
        ZINC("zinc");

        private final String unlocalizedName;
        private RegistryObject<Block> sample;

        OreSamples(String unlocalizedName) {
            this.unlocalizedName = unlocalizedName;
        }

        public String toString() {
            return this.unlocalizedName;
        }

        public String getUnlocalizedName() {
            return this.unlocalizedName;
        }

        @Nullable
        public RegistryObject<Block> getSample() {
            return this.sample;
        }

        public void setSample(RegistryObject<Block> b) {
            this.sample = b;
        }
    }
}
