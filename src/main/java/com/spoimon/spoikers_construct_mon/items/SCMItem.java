package com.spoimon.spoikers_construct_mon.items;

import com.spoimon.spoikers_construct_mon.SCM;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;

/**
 * SCMで追加されるアイテムのベースクラス
 * レジストリ名, 未翻訳名が自動で設定される
 * @author riku1227
 */
public class SCMItem extends Item {
    //アイテムの名前
    public final String itemName;
    //鉱石辞書の名前
    protected String oreDictionaryName = "";

    public SCMItem(String itemName) {
        super();
        this.itemName = itemName;
        this.setRegistryName(SCM.MOD_ID, itemName);
        this.setUnlocalizedName(SCM.MOD_ABBREVIATION + "." + itemName);
    }

    public SCMItem(String itemName, String oreDictionaryName) {
        this(itemName);
        this.oreDictionaryName = oreDictionaryName;
    }

    /**
     * @return 鉱石辞書の名前がある場合はtrue
     */
    public Boolean isOreDictionary() {
        return !this.oreDictionaryName.isEmpty();
    }

    /**
     * @return 鉱石辞書名を返す
     */
    public String getOreDictionaryName() {
        return oreDictionaryName;
    }

    /**
     * @return ドメインがMOD_ID, パスがアイテム名のResourceLocation
     */
    public ResourceLocation getResourceLocation() {
        return new ResourceLocation(SCM.MOD_ID, this.itemName);
    }
}
