package com.spoimon.spoikers_construct_mon.blocks;

import com.spoimon.spoikers_construct_mon.SCM;
import com.spoimon.spoikers_construct_mon.world.generator.data.OreGeneratorData;
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
    //鉱石の生成に関するデータ
    protected OreGeneratorData oreGeneratorData;

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

    /**
     * 鉱石の生成に関するデータを入れるクラスをセットする、セットした値は主にOreGeneratorで利用される
     * @param oreGeneratorData 鉱石の生成に関するデータを入れるクラス
     */
    public void setOreGeneratorData(OreGeneratorData oreGeneratorData) {
        this.oreGeneratorData = oreGeneratorData;
    }

    /**
     * OreGenerationDataを取得する
     * @return 鉱石の生成に関するデータを入れるクラス
     */
    public OreGeneratorData getOreGeneratorData() {
        return this.oreGeneratorData;
    }

    /**
     * @return OreGeneratorDataがセットされていた場合はtrueを返す
     */
    public Boolean isExistsOreGenerationData() {
        return this.oreGeneratorData != null;
    }
}
