package com.spoimon.spoikers_construct_mon.register;

import com.spoimon.spoikers_construct_mon.blocks.OreBlock;
import com.spoimon.spoikers_construct_mon.blocks.SCMBlock;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.block.statemap.StateMapperBase;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;
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
        registerBlock(new OreBlock("copper_ore", 1));
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
            //BlockStateの場所を指定する
            ModelLoader.setCustomStateMapper(value, new StateMapperBase() {
                @Override
                @Nonnull
                @ParametersAreNonnullByDefault
                protected ModelResourceLocation getModelResourceLocation(IBlockState state) {
                    return new ModelResourceLocation(value.getResourceLocation(), "normal");
                }
            });
            //Modelの場所を指定する
            ModelLoader.setCustomModelResourceLocation(value.getItemBlock(), 0,
                    new ModelResourceLocation(
                            value.getResourceLocation(), "inventory"
                    )
            );
        }
    }

}