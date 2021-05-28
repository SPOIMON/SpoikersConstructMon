package com.spoimon.spoikers_construct_mon.register;

import com.spoimon.spoikers_construct_mon.items.IngotItem;
import com.spoimon.spoikers_construct_mon.items.SCMItem;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.oredict.OreDictionary;

import java.util.HashMap;
import java.util.Map;

/**
 * SCMで追加されるアイテムの登録や管理をするクラス
 * @author riku1227
 */

public class ItemRegister {
    public static Map<String, SCMItem> SCMItems = new HashMap<>();

    /**
     * コンストラクタ実行時に 'MinecraftForge.EVENT_BUS.register' が呼ばれる
     */
    public ItemRegister() {
        MinecraftForge.EVENT_BUS.register(this);
        //アイテムの登録
        registerItems();
    }

    /**
     * SCMで追加されるアイテムを全て登録する
     */
    private void registerItems() {
        registerItem(new IngotItem("copper_ingot", "Copper"));
    }

    /**
     * SCMItemを登録する
     * @param item 登録するアイテム
     */
    private void registerItem(SCMItem item) {
        ItemRegister.SCMItems.put(item.itemName, item);
    }

    /**
     * Forgeのイベントから呼ばれる
     * レジストリにアイテムを登録する
     */
    @SubscribeEvent
    public void registerItemsEvent(RegistryEvent.Register<Item> event) {
        for(SCMItem value : SCMItems.values()) {
            event.getRegistry().register(value);
            if(value.isOreDictionary()) {
                OreDictionary.registerOre(value.getOreDictionaryName(), value);
            }
        }
    }

    /**
     * Forgeのイベントから呼ばれる
     * アイテムのモデルを設定する
     */
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
