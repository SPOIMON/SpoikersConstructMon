package com.spoimon.spoikers_construct_mon.register;

import com.spoimon.spoikers_construct_mon.blocks.SCMBlock;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
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

/**
 * SCMで追加されるブロックの登録や管理を行うクラス
 * @author riku1227
 */
public class BlockRegister {
    public static Map<String, SCMBlock> SCMBlocks = new HashMap<>();

    /**
     * コンストラクタ実行時に EVENT_BUS に登録
     */
    public BlockRegister() {
        MinecraftForge.EVENT_BUS.register(this);
        //ブロックを登録
        registerBlocks();
    }

    /**
     * SCMで追加されるブロックを全て登録する
     */
    private void registerBlocks() {
        registerBlock(new SCMBlock(Material.IRON, "test_block"));
    }

    /**
     * ブロックを登録する
     * @param block 登録するブロック
     */
    private void registerBlock(SCMBlock block) {
        SCMBlocks.put(block.blockName, block);
    }

    /**
     * Forgeのイベントから呼ばれる
     * アイテムブロックをレジストリに登録する
     */
    @SubscribeEvent
    public void registerItemsEvent(RegistryEvent.Register<Item> event) {
        for(SCMBlock value : SCMBlocks.values()) {
            event.getRegistry().register(value.getItemBlock());
        }
    }

    /**
     * Forgeのイベントから呼ばれる
     * ブロックをレジストリに登録する
     */
    @SubscribeEvent
    public void registerBlocksEvent(RegistryEvent.Register<Block> event) {
        for(SCMBlock value : SCMBlocks.values()) {
            event.getRegistry().register(value);
        }
    }

    /**
     * Forgeのイベントから呼ばれる
     * ブロックのモデルを設定する
     */
    @SubscribeEvent
    @SideOnly(Side.CLIENT)
    public void registerModelsEvent(ModelRegistryEvent event) {
        for(SCMBlock value : SCMBlocks.values()) {
            ModelLoader.setCustomModelResourceLocation(value.getItemBlock(), 0,
                    new ModelResourceLocation(
                            value.getResourceLocation(), "inventory"
                    )
            );
        }
    }

}