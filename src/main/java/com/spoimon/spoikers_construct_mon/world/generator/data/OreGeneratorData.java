package com.spoimon.spoikers_construct_mon.world.generator.data;

import net.minecraft.block.Block;

/**
 * 鉱石の生成に関するデータを入れるクラス
 * @author riku1227
 */
public class OreGeneratorData {
    //一度に生成されるブロックの数
    public final int veinBlockCount;
    //生成する対象のブロック
    public final Block targetBlock;
    //1チャンクに生成される鉱脈の数
    public final int generateBlockChance;
    //鉱石を生成する最低の高度
    public final int minHeight;
    //鉱石を生成する最高の硬度
    public final int maxHeight;

    public OreGeneratorData(int veinBlockCount, Block targetBlock, int generateBlockChance, int minHeight, int maxHeight) {
        this.veinBlockCount = veinBlockCount;
        this.targetBlock = targetBlock;
        this.generateBlockChance = generateBlockChance;
        this.minHeight = minHeight;
        this.maxHeight = maxHeight;
    }
}
