package com.spoimon.spoikers_construct_mon.register;

import com.spoimon.spoikers_construct_mon.SCM;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.oredict.ShapedOreRecipe;
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
        registerShapedOreRecipe(registry, "block_of_copper", new ItemStack(BlockRegister.SCMBlocks.get("block_of_copper"), 1, 0),
                "###",
                "###",
                "###",
                '#', "ingotCopper" );
    }

    /**
     * レシピを登録するメソッド
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
}
