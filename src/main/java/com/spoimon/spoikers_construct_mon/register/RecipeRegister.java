package com.spoimon.spoikers_construct_mon.register;

import com.spoimon.spoikers_construct_mon.SCM;
import com.spoimon.spoikers_construct_mon.blocks.MineralBlock;
import com.spoimon.spoikers_construct_mon.blocks.SCMBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;
import net.minecraftforge.registries.IForgeRegistry;

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
    }

    /**
     * 鉱物ブロックのレシピを一括で自動登録するメソッド
     * @param registry IRecipeのレジストリ
     */
    private void registerAllMineralBlocksRecipe(IForgeRegistry<IRecipe> registry) {
        for (SCMBlock scmBlock : BlockRegister.SCMBlocks.values()) {
            if(scmBlock instanceof MineralBlock) {
                MineralBlock mineralBlock = (MineralBlock) scmBlock;
                //インゴットからブロックをクラフトするレシピの追加
                registerShapedOreRecipe(registry, mineralBlock.blockName, new ItemStack(mineralBlock),
                        "###",
                        "###",
                        "###",
                        '#', mineralBlock.baseIngotOreDictionaryName
                );

                //鉱石辞書からベースインゴットのItemStackを取得
                ItemStack ingotItem = OreDictionary.getOres(mineralBlock.baseIngotOreDictionaryName).get(0);
                //数を9個に
                ingotItem.setCount(9);
                //ブロックからインゴットにクラフトするレシピの追加
                registerShapelessOreRecipe(registry, mineralBlock.blockName + "_to_ingot", ingotItem, mineralBlock.getOreDictionaryName());
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
