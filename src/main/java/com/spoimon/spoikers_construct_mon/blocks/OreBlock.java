package com.spoimon.spoikers_construct_mon.blocks;

import com.spoimon.spoikers_construct_mon.SCM;
import net.minecraft.block.material.Material;
import net.minecraft.util.ResourceLocation;

/**
 * 鉱石ブロック用のクラス
 * マテリアルタイプがROCKになる
 * ResourceLocationに 'ore/' が追加される
 */
public class OreBlock extends SCMBlock {
    public OreBlock(String blockName, int harvestLevel) {
        super(Material.ROCK, blockName);
        setHarvestLevel("pickaxe", harvestLevel);
    }

    @Override
    public ResourceLocation getResourceLocation() {
        return new ResourceLocation(SCM.MOD_ID, "ore/" + this.blockName);
    }
}
