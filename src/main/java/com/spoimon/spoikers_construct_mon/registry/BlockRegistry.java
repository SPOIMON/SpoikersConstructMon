package com.spoimon.spoikers_construct_mon.registry;

import com.spoimon.spoikers_construct_mon.blocks.*;
import com.spoimon.spoikers_construct_mon.blocks.enums.Ore01Enum;
import net.minecraft.block.Block;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.block.statemap.StateMapperBase;
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
import slimeknights.mantle.item.ItemBlockMeta;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * SCMで追加されるブロックの登録や管理を行うクラス
 * @author riku1227
 */
public class BlockRegistry {
    public static Map<String, SCMBlock> SCMBlocks = new HashMap<>();
    public static Map<String, SCMEnumBlock<?>> SCMEnumBlocks = new HashMap<>();

    /**
     * コンストラクタ実行時に EVENT_BUS に登録
     */
    public BlockRegistry() {
        MinecraftForge.EVENT_BUS.register(this);
        //ブロックを登録
        registerBlocks();
    }

    /**
     * SCMで追加されるブロックを全て登録する
     */
    private void registerBlocks() {
        PropertyEnum<Ore01Enum> ore01EnumPropertyEnum = PropertyEnum.create("type", Ore01Enum.class);
        registerBlock(new OreBlock<>(ore01EnumPropertyEnum, Ore01Enum.class, "ore_001"));
        registerBlock(new MineralBlock<>(ore01EnumPropertyEnum, Ore01Enum.class, "mineral_block_001"));
    }

    /**
     * ブロックを登録する
     * @param block 登録するブロック
     */
    private void registerBlock(SCMBlock block) {
        SCMBlocks.put(block.blockName, block);
    }

    /**
     * メタデータブロックを登録する
     * @param enumBlock 登録するメタデータブロック
     */
    private void registerBlock(SCMEnumBlock<?> enumBlock) {
        SCMEnumBlocks.put(enumBlock.blockName, enumBlock);
    }

    /**
     * Forgeのイベントから呼ばれる
     * アイテムブロックをレジストリに登録する
     */
    @SubscribeEvent
    public void registerItemsEvent(RegistryEvent.Register<Item> event) {
        for(SCMBlock value : SCMBlocks.values()) {
            event.getRegistry().register(value.getItemBlock());
            if(value.isOreDictionary()) {
                OreDictionary.registerOre(value.getOreDictionaryName(), value);
            }
        }

        for(SCMEnumBlock<?> value : SCMEnumBlocks.values()) {
            event.getRegistry().register(value.getItemBlock());
            ItemBlockMeta.setMappingProperty(value, value.prop);
            if(value.isOreDictionary()) {
                List<String> oreDicList = value.getOreDictionaryList();
                for(int i = 0; i < oreDicList.size(); i++) {
                    OreDictionary.registerOre(oreDicList.get(i), new ItemStack(value, 1, i));
                }
            }
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

        for (SCMEnumBlock<?> value : SCMEnumBlocks.values()) {
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

        for (SCMEnumBlock<?> value : SCMEnumBlocks.values()) {
            //EnumBlockからメタデータ全ての名前のリストを取得する
            ArrayList<String> metaNameList = value.getMetaNameList();
            for(int i = 0; i < metaNameList.size(); i++) {
                //ItemBlockのモデルの場所を指定する
                ModelLoader.setCustomModelResourceLocation(value.getItemBlock(), i,
                        new ModelResourceLocation(
                                value.getItemBlockResourceLocation(), metaNameList.get(i)
                        )
                );
            }
        }
    }

}