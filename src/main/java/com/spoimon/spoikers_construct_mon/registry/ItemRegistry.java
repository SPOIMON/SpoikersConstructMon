package com.spoimon.spoikers_construct_mon.registry;

import com.spoimon.spoikers_construct_mon.blocks.enums.Ore01Enum;
import com.spoimon.spoikers_construct_mon.items.IngotItem;
import com.spoimon.spoikers_construct_mon.items.SCMEnumItem;
import com.spoimon.spoikers_construct_mon.items.SCMItem;
import net.minecraft.block.properties.PropertyEnum;
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
import java.util.List;
import java.util.Map;

/**
 * SCMで追加されるアイテムの登録や管理をするクラス
 * @author riku1227
 */

public class ItemRegistry {
    public static Map<String, SCMItem> SCMItems = new HashMap<>();
    public static Map<String, SCMEnumItem<?>> SCMEnumItems = new HashMap<>();

    /**
     * コンストラクタ実行時に 'MinecraftForge.EVENT_BUS.register' が呼ばれる
     */
    public ItemRegistry() {
        MinecraftForge.EVENT_BUS.register(this);
        //アイテムの登録
        registerItems();
    }

    /**
     * SCMで追加されるアイテムを全て登録する
     */
    private void registerItems() {
        PropertyEnum<Ore01Enum> ore01EnumPropertyEnum = PropertyEnum.create("type", Ore01Enum.class);
        registerItem(new IngotItem<>(ore01EnumPropertyEnum, Ore01Enum.class, "ingot_001"));
    }

    /**
     * SCMItemを登録する
     * @param item 登録するアイテム
     */
    private void registerItem(SCMItem item) {
        ItemRegistry.SCMItems.put(item.itemName, item);
    }

    /**
     * SCMEnumItemを登録する
     * @param item 登録するメタデータアイテム
     */
    private void registerItem(SCMEnumItem<?> item) {
        ItemRegistry.SCMEnumItems.put(item.getRegistryNameNoMODID(), item);
    }

    /**
     * Forgeのイベントから呼ばれる
     * レジストリにアイテムを登録する
     */
    @SubscribeEvent
    public void registerItemsEvent(RegistryEvent.Register<Item> event) {
        //SCMで追加されるアイテム
        for(SCMItem value : SCMItems.values()) {
            event.getRegistry().register(value);
            //アイテムに鉱石辞書名が指定されていたら
            if(value.isOreDictionary()) {
                //鉱石辞書に登録する
                OreDictionary.registerOre(value.getOreDictionaryName(), value);
            }
        }

        //SCMで追加されるメタデータアイテム
        for (SCMEnumItem<?> value : SCMEnumItems.values()) {
            event.getRegistry().register(value);
            //アイテムに鉱石辞書名が指定されていたら
            if(value.isOreDictionary()) {
                //メタデータ全ての鉱石辞書名リストを取得
                List<String> metaOreDictionaryList = value.getMetaOreDictionaryList();
                for(int i = 0; i < metaOreDictionaryList.size(); i++) {
                    //鉱石辞書に登録
                    OreDictionary.registerOre(metaOreDictionaryList.get(i), new ItemStack(value, 1, i));
                }
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

        for(SCMEnumItem<?> value : SCMEnumItems.values()) {
            List<String> metaNameList = value.getMetaNameList();
            for (int i = 0; i < metaNameList.size(); i++) {
                ModelLoader.setCustomModelResourceLocation(value, i,
                        new ModelResourceLocation(value.getResourceLocation(), metaNameList.get(i))
                );
            }
        }
    }
}
