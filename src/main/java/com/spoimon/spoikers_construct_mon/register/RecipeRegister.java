package com.spoimon.spoikers_construct_mon.register;

import com.spoimon.spoikers_construct_mon.SCM;
import com.spoimon.spoikers_construct_mon.blocks.MineralBlock;
import com.spoimon.spoikers_construct_mon.blocks.OreBlock;
import com.spoimon.spoikers_construct_mon.blocks.SCMEnumBlock;
import com.spoimon.spoikers_construct_mon.utils.StringUtil;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;
import net.minecraftforge.registries.IForgeRegistry;

import java.util.List;

/**
 * SCMでレシピの登録をするクラス
 * @author riku1227
 */
public class RecipeRegister {
    public RecipeRegister() {
        MinecraftForge.EVENT_BUS.register(this);
    }

    @SubscribeEvent
    public void registerRecipesEvent(RegistryEvent.Register<IRecipe> event) {
        IForgeRegistry<IRecipe> registry = event.getRegistry();
        registerAllMineralBlocksRecipe(registry);
        registerAllOreSmeltingRecipe();
    }


    /**
     * 鉱石の精錬レシピを追加する
     */
    private void registerAllOreSmeltingRecipe() {
        for(SCMEnumBlock<?> value : BlockRegister.SCMEnumBlocks.values()) {
            //OreBlockだったら
            if(value instanceof OreBlock) {
                OreBlock<?> oreBlock = (OreBlock<?>) value;
                //メタデータの名前のリストを取得
                List<String> metaNameList = oreBlock.getMetaNameList();
                for(int i = 0; i < metaNameList.size(); i++) {
                    //インゴットを鉱石辞書から取得
                    List<ItemStack> dictionary = OreDictionary.getOres("ingot" + StringUtil.toFirstUpper(metaNameList.get(i)));
                    if(dictionary.size() > 0) {
                        GameRegistry.addSmelting(new ItemStack(value, 1, i), dictionary.get(0), 0.5f);
                    }
                }
            }
        }
    }

    /**
     * 鉱物ブロックのレシピを一括で自動登録するメソッド
     * @param registry IRecipeのレジストリ
     */
    private void registerAllMineralBlocksRecipe(IForgeRegistry<IRecipe> registry) {
        for (SCMEnumBlock<?> scmEnumBlock : BlockRegister.SCMEnumBlocks.values()) {
            if(scmEnumBlock instanceof MineralBlock<?>) {
                MineralBlock<?> mineralBlock = (MineralBlock<?>) scmEnumBlock;
                List<String> metaNameList = mineralBlock.getMetaNameList();
                for (int i = 0; i < metaNameList.size(); i++) {
                    String metaName = metaNameList.get(i);
                    String ingotDictionaryName = "ingot" + StringUtil.toFirstUpper(metaName);
                    registerShapedOreRecipe(registry, mineralBlock.blockName + i, new ItemStack(mineralBlock, 1, i),
                            "###",
                            "###",
                            "###",
                            '#', ingotDictionaryName);

                    List<ItemStack> oreDictionaryList = OreDictionary.getOres(ingotDictionaryName);
                    if (oreDictionaryList.size() > 0) {
                        ItemStack ingotItem = oreDictionaryList.get(0).copy();
                        ingotItem.setCount(9);
                        registerShapelessOreRecipe(registry, mineralBlock.blockName + i + "_to_ingot", ingotItem, "block" + StringUtil.toFirstUpper(metaName));
                    }
                }
            }
        }
    }

    /**
     * 固定レシピを登録するメソッド
     * @param registry IRecipeのレジストリ
     * @param recipeName レシピの名前(リソースロケーションとレジストリ名で使用される)
     * @param outputItem クラフトで完成するアイテム
     * @param recipe レシピのオブジェクト
     */
    private void registerShapedOreRecipe(IForgeRegistry<IRecipe> registry, String recipeName, ItemStack outputItem, Object... recipe) {
        registry.register(
                new ShapedOreRecipe(
                        new ResourceLocation(SCM.MOD_ID, recipeName), outputItem, recipe
                ).setRegistryName(SCM.MOD_ID, recipeName)
        );
    }

    /**
     * 不定形レシピを登録するメソッド
     * @param registry IRecipeのレジストリ
     * @param recipeName レシピの名前(リソースロケーションとレジストリ名で使用される)
     * @param outputItem クラフトで完成するアイテム
     * @param recipe レシピのオブジェクト
     */
    private void registerShapelessOreRecipe(IForgeRegistry<IRecipe> registry, String recipeName, ItemStack outputItem, Object... recipe) {
        registry.register(
                new ShapelessOreRecipe(
                        new ResourceLocation(SCM.MOD_ID, recipeName), outputItem, recipe
                ).setRegistryName(SCM.MOD_ID, recipeName)
        );
    }
}
