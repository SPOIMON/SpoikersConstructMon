package com.spoimon.spoikers_construct_mon.items;

import com.spoimon.spoikers_construct_mon.SCM;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;

public class SCMItem extends Item {
    public final String itemName;

    public SCMItem(String itemName) {
        super();
        this.itemName = itemName;
        this.setRegistryName(SCM.MOD_ID, itemName);
        this.setUnlocalizedName(itemName);
    }

    public ResourceLocation getResourceLocation() {
        return new ResourceLocation(SCM.MOD_ID, this.itemName);
    }
}
