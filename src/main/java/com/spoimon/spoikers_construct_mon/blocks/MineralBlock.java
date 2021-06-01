package com.spoimon.spoikers_construct_mon.blocks;

import com.spoimon.spoikers_construct_mon.SCM;
import net.minecraft.block.material.Material;
import net.minecraft.util.ResourceLocation;

public class MineralBlock extends SCMBlock {

    public MineralBlock(String blockName) {
        super(Material.IRON, blockName);
        setHarvestLevel("pickaxe", 1);
    }

    public MineralBlock(String blockName, String oreDictionaryName) {
        this(blockName);

        String oreDictNameTemp = oreDictionaryName;
        if(!oreDictNameTemp.startsWith("block")) {
            oreDictNameTemp = "block" + oreDictionaryName;
        }
        this.oreDictionaryName = oreDictNameTemp;
    }

    @Override
    public ResourceLocation getResourceLocation() {
        return new ResourceLocation(SCM.MOD_ID, "mineral_block/" + this.blockName);
    }
}
