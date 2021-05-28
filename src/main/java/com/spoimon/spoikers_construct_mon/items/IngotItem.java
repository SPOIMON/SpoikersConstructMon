package com.spoimon.spoikers_construct_mon.items;

import com.spoimon.spoikers_construct_mon.SCM;
import com.spoimon.spoikers_construct_mon.client.SCMCreativeTab;
import net.minecraft.util.ResourceLocation;

/**
 * インゴット系のアイテムクラス
 * ResourceLocationに 'ingot/' が追加される
 * クリエイティブタブがSCMCreativeTabになる
 * 鉱石辞書名に"ingot"プレフィックスがない場合、自動的に追加される
 * @author riku1227
 */
public class IngotItem extends SCMItem {
    public IngotItem(String itemName) {
        super(itemName);
        this.setCreativeTab(SCMCreativeTab.INSTANCE);
    }

    public IngotItem(String itemName, String oreDictionaryName) {
        this(itemName);
        String oreDicNameTemp = oreDictionaryName;
        //鉱石辞書名が"ingot"から始まっていない場合、"ingot"というプレフィックスを前に追加する
        if(!oreDictionaryName.startsWith("ingot")) {
            oreDicNameTemp = "ingot" + oreDicNameTemp;
        }
        this.oreDictionaryName = oreDicNameTemp;
    }

    @Override
    public ResourceLocation getResourceLocation() {
        return new ResourceLocation(SCM.MOD_ID, "ingot/" + this.itemName);
    }
}
