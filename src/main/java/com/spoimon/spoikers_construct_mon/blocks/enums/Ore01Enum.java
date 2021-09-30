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
    LEAD,
    TIN,
    SILVER,
    PLATINUM,
    NICKEL;

    public final int meta;

    Ore01Enum() {
        meta = ordinal();
    }


    @Override
    public boolean isAutoCreateOre() {
        return true;
    }


    @Override
    public int getHarvestLevel() {
        int defaultHarvestLevel = 0;
        switch (this) {
            case COPPER:
            case LEAD:
            case TIN:
            case SILVER:
            case NICKEL:
                defaultHarvestLevel = 1;
                break;
            case PLATINUM:
                defaultHarvestLevel = 3;
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

            case TIN:
                result = new OreGeneratorData(7, Blocks.STONE, 14, 1, 65);
                break;

            case SILVER:
                result = new OreGeneratorData(4, Blocks.STONE, 9, 1, 68);
                break;

            case PLATINUM:
                result = new OreGeneratorData(3, Blocks.STONE, 4, 1, 13);
                break;

            case NICKEL:
                result = new OreGeneratorData(6, Blocks.STONE, 12, 1, 72);
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
