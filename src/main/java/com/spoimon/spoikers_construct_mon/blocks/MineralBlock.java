package com.spoimon.spoikers_construct_mon.blocks;

import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.IStringSerializable;
import slimeknights.mantle.block.EnumBlock;

import javax.annotation.ParametersAreNonnullByDefault;

/**
 * 鉱石ブロック用のメタデータブロッククラス
 * このクラスを使用することで、自動的に鉱石ブロックのクラフトレシピが追加される
 * @param <T>
 */
public class MineralBlock<T extends java.lang.Enum<T> & EnumBlock.IEnumMeta & IStringSerializable & OreBlock.IOreBlock > extends SCMEnumBlock<T> {

    public final PropertyEnum<T> prop;

    public MineralBlock(PropertyEnum<T> prop, Class<T> clazz, String blockName) {
        super(prop, clazz, Material.IRON, blockName, "block");
        this.prop = prop;
        setHardness(2.5f);
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
}
