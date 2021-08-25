package com.spoimon.spoikers_construct_mon.client;

import com.spoimon.spoikers_construct_mon.registry.ItemRegistry;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;

import javax.annotation.Nonnull;

/**
 * SCMで追加されたアイテムを入れるクリエイティブタブ
 * @author riku1227
 */
public class SCMCreativeTab extends CreativeTabs {

    //SCMCreativeTabのインスタンス
    public static final SCMCreativeTab INSTANCE = new SCMCreativeTab();

    public SCMCreativeTab() {
        super("spoikers_construct_mon");
    }


    @Nonnull
    @Override
    public ItemStack getTabIconItem() {
        //TODO 象徴的なアイテムもしくはアイコンができたらそれに変える
        return new ItemStack(ItemRegistry.SCMItems.get("test_ingot"));
    }
}
