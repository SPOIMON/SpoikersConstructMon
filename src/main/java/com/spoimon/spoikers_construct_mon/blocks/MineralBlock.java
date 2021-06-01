package com.spoimon.spoikers_construct_mon.blocks;

import com.spoimon.spoikers_construct_mon.SCM;
import net.minecraft.block.material.Material;
import net.minecraft.util.ResourceLocation;

/**
 * 鉱物ブロック用のクラス
 * このクラスでブロックを追加すると、指定したインゴットの鉱石辞書名で自動的にクラフトレシピが追加される
 * マテリアルタイプがIronになる
 * ResourceLocationに 'mineral_block/' が追加される
 * 鉱石辞書名に"block"プレフィックスがない場合、自動的に追加される
 * @author riku1227
 */
public class MineralBlock extends SCMBlock {
    //ブロックのクラフト素材となるインゴットの鉱石辞書名
    public final String baseIngotOreDictionaryName;

    /**
     * @param blockName ブロックの名前
     * @param baseIngotOreDictionaryName ブロックのクラフト素材となるインゴットの鉱石辞書名
     */
    public MineralBlock(String blockName, String baseIngotOreDictionaryName) {
        super(Material.IRON, blockName);
        this.baseIngotOreDictionaryName = baseIngotOreDictionaryName;
        setHarvestLevel("pickaxe", 1);
    }

    /**
     * @param blockName ブロックの名前
     * @param baseIngotOreDictionaryName ブロックのクラフト素材となるインゴットの鉱石辞書名
     * @param oreDictionaryName ブロックの鉱石辞書名
     */
    public MineralBlock(String blockName, String baseIngotOreDictionaryName, String oreDictionaryName) {
        this(blockName, baseIngotOreDictionaryName);

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
