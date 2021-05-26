package com.spoimon.spoikers_construct_mon.client;

import com.spoimon.spoikers_construct_mon.SCM;
import com.spoimon.spoikers_construct_mon.register.ItemRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;

public class SCMCreativeTab extends CreativeTabs {

    //SCMCreativeTabのインスタンス
    public static final SCMCreativeTab INSTANCE = new SCMCreativeTab();

    public SCMCreativeTab() {
        super("spoikers_construct_mon");
    }

    @Override
    public ItemStack getTabIconItem() {
        return new ItemStack(ItemRegister.SCMItems.get("test_ingot"));
    }
}
