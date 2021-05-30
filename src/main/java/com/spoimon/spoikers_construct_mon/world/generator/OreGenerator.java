package com.spoimon.spoikers_construct_mon.world.generator;

import com.spoimon.spoikers_construct_mon.blocks.OreBlock;
import com.spoimon.spoikers_construct_mon.blocks.SCMBlock;
import com.spoimon.spoikers_construct_mon.register.BlockRegister;
import com.spoimon.spoikers_construct_mon.world.generator.data.OreGeneratorData;
import net.minecraft.block.state.pattern.BlockMatcher;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraftforge.fml.common.IWorldGenerator;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * 鉱石の生成を登録するクラス
 * @author riku1227
 */
public class OreGenerator implements IWorldGenerator {
    /**
     * WorldGenMinableとOreGeneratorDataをまとめたクラス
     * Mapに突っ込む用
     */
    public static class GenerationDatas {
        public final WorldGenMinable worldGenMinable;
        public final OreGeneratorData oreGeneratorData;

        public GenerationDatas(WorldGenMinable worldGenMinable, OreGeneratorData oreGeneratorData) {
            this.worldGenMinable = worldGenMinable;
            this.oreGeneratorData = oreGeneratorData;
        }
    }

    /**
     * GenerationDatasを収納するMap
     * コンストラクタで追加し、generateで利用する
     */
    public Map<String, GenerationDatas> generationDatasMap = new HashMap<>();

    public OreGenerator() {
        //SCMで追加されたブロックをforで回す
        for(SCMBlock block: BlockRegister.SCMBlocks.values()) {
            //追加されたブロックがOreBlockだったら
            if(block instanceof OreBlock) {
                OreBlock oreBlock = (OreBlock) block;
                //OreGenerationDataがそのブロックに存在していたら
                if(oreBlock.isExistsOreGenerationData()) {
                    OreGeneratorData oreGeneratorData = oreBlock.getOreGeneratorData();
                    //そのデータを元にGenerationDatasを作る
                    GenerationDatas generationDatas = new GenerationDatas(
                            new WorldGenMinable(oreBlock.getDefaultState(), oreGeneratorData.veinBlockCount, BlockMatcher.forBlock(oreGeneratorData.targetBlock)),
                            oreGeneratorData
                    );

                    this.generationDatasMap.put(oreBlock.blockName, generationDatas);
                }
            }
        }
    }

    @Override
    public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider) {
        //generationDatasMapをforで回す
        for(GenerationDatas generationDatas : this.generationDatasMap.values()) {
            runGenerator(generationDatas.worldGenMinable,
                    generationDatas.oreGeneratorData.generateBlockChance,
                    generationDatas.oreGeneratorData.minHeight,
                    generationDatas.oreGeneratorData.maxHeight,
                    world,
                    random,
                    chunkX,
                    chunkZ);
        }
    }

    private void runGenerator(WorldGenMinable generator, int chancesToSpawn, int minHeight, int maxHeight, World world, Random rand, int chunkX, int chunkZ){
        //生成最低高度が0未満の時と生成最高高度が257以上の時は例外を投げる
        if (minHeight < 0 || maxHeight > 256 || minHeight > maxHeight)
            throw new IllegalArgumentException("Illegal Height Arguments for WorldGenerator");

        /*
        * ベースとなるBlockPos
        * chunkXとchunkZにはそのチャンクの番号(?)で直接の座標ではないので16をかける
        * */
        BlockPos baseBlockPos = new BlockPos(chunkX * 16, 0, chunkZ * 16);

        /*
        * 最大高度から最低高度を引いた数
        * Y軸の乱数を最高高度に追加する形で生成するので先に最低高度分を引いておかないと最高高度を超えてしまう場合がある
        * +1するのはrand.nextIntが指定された値未満の数値を出すため
        * 例: 最低高度が10で最高高度が60で
        *     先に最低高度分を引かないまま生成してしまい、乱数で60が出たとき
        *     10 + 60 で最高高度の60を超えてしまう
        * */
        int heightDiff = maxHeight - minHeight +1;

        /*
        * チャンスが多ければ多いほど生成が呼び出され、1チャンクに生成される数が多くなる
        * 洞窟や既に他の鉱石が生成されていたりなどで必ずしもその数生成されるとも限らない
        * */
        for (int i = 0; i < chancesToSpawn; i++){
            //それぞれそのチャンクのどこに生成するのかの乱数を調整し、baseBlockPosに追加する
            int addX = rand.nextInt(16);
            int addY = minHeight + rand.nextInt(heightDiff);
            int addZ = rand.nextInt(16);

            //生成
            generator.generate(world, rand, baseBlockPos.add(
                    addX,
                    addY,
                    addZ
            ));
        }
    }
}
