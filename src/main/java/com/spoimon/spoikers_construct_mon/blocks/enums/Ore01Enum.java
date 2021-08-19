package com.spoimon.spoikers_construct_mon.blocks.enums;

import com.spoimon.spoikers_construct_mon.blocks.OreBlock;
import com.spoimon.spoikers_construct_mon.world.generator.data.OreGeneratorData;
import net.minecraft.init.Blocks;
import net.minecraft.util.IStringSerializable;
import slimeknights.mantle.block.EnumBlock;

import java.util.Locale;

/**
 * 鉱石関連のメタデータブロックに使用するEnum
 * @author riku1227
 */
public enum Ore01Enum implements IStringSerializable, EnumBlock.IEnumMeta, OreBlock.IOreBlock {
    COPPER,
    LEAD;

    public final int meta;

    Ore01Enum() {
        meta = ordinal();
    }

    @Override
    public int getHarvestLevel() {
        int defaultHarvestLevel = 0;
        switch (this) {
            case COPPER:
            case LEAD:
                defaultHarvestLevel = 1;
                break;
        }

        return defaultHarvestLevel;
    }

    @Override
    public OreGeneratorData getOreGeneratorData() {
        OreGeneratorData result = null;
        switch (this) {
            case COPPER:
                result = new OreGeneratorData(8, Blocks.STONE, 16, 1, 60);
                break;

            case LEAD:
                result = new OreGeneratorData(6, Blocks.STONE, 16, 1, 70);
                break;
        }

        return result;
    }

    @Override
    public String getName() {
        return this.toString().toLowerCase(Locale.US);
    }

    @Override
    public int getMeta() {
        return meta;
    }
}
