package com.spoimon.spoikers_construct_mon.blocks;

import com.spoimon.spoikers_construct_mon.world.generator.data.OreGeneratorData;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.NonNullList;
import slimeknights.mantle.block.EnumBlock;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.ArrayList;
import java.util.List;

/**
 * 鉱石ブロック用のクラス
 * メタデータを利用し、ブロックIDを節約
 * 鉱石ブロック用のEnumを指定、採掘レベルなどはEnumの方で指定する
 * マテリアルタイプがROCKになる,
 * 鉱石辞書カテゴリ('ore'や'ingot'など)に'ore'が指定される
 * @author riku1227
 */
public class OreBlock<T extends java.lang.Enum<T> & EnumBlock.IEnumMeta & IStringSerializable & OreBlock.IOreBlock > extends SCMEnumBlock<T> {

    public final PropertyEnum<T> prop;

    public OreBlock(PropertyEnum<T> prop, Class<T> clazz, String registryName) {
        super(prop, clazz, Material.ROCK, registryName, "ore");
        this.prop = prop;

        setHardness(2.5f);
    }

    /**
     * OreGeneratorDataはEnumの方で設定する
     * @return メタデータ全てのOreGeneratorDataのリストを返す
     */
    public List<OreGeneratorData> getMetaOreGeneratorList() {
        ArrayList<OreGeneratorData> result = new ArrayList<>();
        for (T value : values) {
            result.add(value.getOreGeneratorData());
        }
        return result;
    }

    @Override
    public void getSubBlocks(CreativeTabs tab, NonNullList<ItemStack> list) {
        for(int i = 0; i < values.length; i++) {
            T value = values[i];
            if(value.isAutoCreateOre()) {
                list.add(new ItemStack(this, 1, i));
            }
        }
    }

    @Override
    @ParametersAreNonnullByDefault
    public int getHarvestLevel(IBlockState state) {
        int meta = this.getMetaFromState(state);
        return values[meta].getHarvestLevel();
    }

    @Override
    @ParametersAreNonnullByDefault
    public String getHarvestTool(IBlockState state) {
        return "pickaxe";
    }

    public interface IOreBlock {
        //マイニングレベル
        int getHarvestLevel();
        //自動生成に関するデータ
        OreGeneratorData getOreGeneratorData();
        //鉱石を自動的に生成するかどうか
        boolean isAutoCreateOre();
    }
}
