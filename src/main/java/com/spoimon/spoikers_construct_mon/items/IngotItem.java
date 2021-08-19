package com.spoimon.spoikers_construct_mon.items;

import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.util.IStringSerializable;
import slimeknights.mantle.block.EnumBlock;

/**
 * SCMで追加されるインゴット用のアイテムクラス
 * 鉱石辞書カテゴリに'ore'が自動的に設定される
 * @param <T>
 */
public class IngotItem<T extends java.lang.Enum<T> & EnumBlock.IEnumMeta & IStringSerializable> extends SCMEnumItem<T>{

    public IngotItem(PropertyEnum<T> prop, Class<T> clazz, String registryName) {
        super(prop, clazz, registryName, "ingot");
        this.setMaxStackSize(64);
    }
}
