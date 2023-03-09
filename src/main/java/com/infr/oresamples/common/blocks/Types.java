package com.infr.oresamples.common.blocks;

import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.RegistryObject;

import javax.annotation.Nullable;

public class Types {
    public enum OreSamples {
        DIAMOND("diamond"),
        GOLD("gold");

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
