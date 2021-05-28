package com.spoimon.spoikers_construct_mon.blocks;

import com.spoimon.spoikers_construct_mon.SCM;
import net.minecraft.block.material.Material;
import net.minecraft.util.ResourceLocation;

/**
 * 鉱石ブロック用のクラス
 * マテリアルタイプがROCKになる
 * ResourceLocationに 'ore/' が追加される
 * 鉱石辞書名に"ore"プレフィックスがない場合、自動的に追加される
 * @author riku1227
 */
public class OreBlock extends SCMBlock {
    public OreBlock(String blockName, int harvestLevel) {
        super(Material.ROCK, blockName);
        setHarvestLevel("pickaxe", harvestLevel);
    }

    public OreBlock(String blockName, int harvestLevel, String oreDictionaryName) {
        this(blockName, harvestLevel);
        String oreDicNameTemp = oreDictionaryName;
        //鉱石辞書名が"ore"から始まっていない場合、"ore"というプレフィックスを前に追加する
        if(!oreDictionaryName.startsWith("ore")) {
            oreDicNameTemp = "ore" + oreDictionaryName;
        }
        this.oreDictionaryName = oreDicNameTemp;
    }

    @Override
    public ResourceLocation getResourceLocation() {
        return new ResourceLocation(SCM.MOD_ID, "ore/" + this.blockName);
    }
}
