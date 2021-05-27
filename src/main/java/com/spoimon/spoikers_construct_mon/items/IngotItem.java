package com.spoimon.spoikers_construct_mon.items;

import com.spoimon.spoikers_construct_mon.SCM;
import com.spoimon.spoikers_construct_mon.client.SCMCreativeTab;
import net.minecraft.util.ResourceLocation;

/**
 * インゴット系のアイテムクラス
 * ResourceLocationに 'ingot/' が追加される
 * クリエイティブタブがSCMCreativeTabになる
 * @author riku1227
 */
public class IngotItem extends SCMItem {
    public IngotItem(String itemName) {
        super(itemName);
        this.setCreativeTab(SCMCreativeTab.INSTANCE);
    }

    @Override
    public ResourceLocation getResourceLocation() {
        return new ResourceLocation(SCM.MOD_ID, "ingot/" + this.itemName);
    }
}
