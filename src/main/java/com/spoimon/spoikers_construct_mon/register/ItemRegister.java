package com.spoimon.spoikers_construct_mon.register;

import com.spoimon.spoikers_construct_mon.items.IngotItem;
import com.spoimon.spoikers_construct_mon.items.SCMItem;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.HashMap;
import java.util.Map;

public class ItemRegister {
    public static Map<String, SCMItem> SCMItems = new HashMap<>();

    public ItemRegister() {
        MinecraftForge.EVENT_BUS.register(this);
        registerItems();
    }

    private void registerItems() {
        registerItem(new IngotItem("test_ingot"));
    }

    private void registerItem(SCMItem item) {
        this.SCMItems.put(item.itemName, item);
    }

    @SubscribeEvent
    public void registerItemsEvent(RegistryEvent.Register<Item> event) {
        for(SCMItem value : SCMItems.values()) {
            event.getRegistry().register(value);
        }
    }

    @SubscribeEvent
    @SideOnly(Side.CLIENT)
    public void registerModelsEvent(ModelRegistryEvent event) {
        for(SCMItem value : SCMItems.values()) {
            ModelLoader.setCustomModelResourceLocation(value, 0,
                    new ModelResourceLocation(
                            value.getResourceLocation(), "inventory"
                    )
            );
        }
    }
}
