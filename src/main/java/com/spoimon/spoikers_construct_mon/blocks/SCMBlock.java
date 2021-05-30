package com.spoimon.spoikers_construct_mon.blocks;

import com.spoimon.spoikers_construct_mon.SCM;
import com.spoimon.spoikers_construct_mon.client.SCMCreativeTab;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.ResourceLocation;

/**
 * SCMで追加されるブロックのベースクラス
 * レジストリ名, 翻訳キー, クリエイティブタブが自動で設定される
 * @author riku1227
 */
public class SCMBlock extends Block {
    public String blockName;
    protected String oreDictionaryName;
    protected ItemBlock itemBlock;

    public SCMBlock(Material blockMaterial, String blockName) {
        super(blockMaterial);
        this.blockName = blockName;
        this.setRegistryName(SCM.MOD_ID, blockName);
        this.setCreativeTab(SCMCreativeTab.INSTANCE);
        this.setUnlocalizedName(SCM.MOD_ABBREVIATION + "." + blockName);
    }

    public SCMBlock(Material blockMaterial, String blockName, String oreDictionaryName) {
        this(blockMaterial, blockName);
        this.oreDictionaryName = oreDictionaryName;
    }

    /**
     * ItemBlockを返す
     * ItemBlockがまだ作られていない場合は新規で作成する
     * @return そのブロックのアイテムブロック
     */
    public ItemBlock getItemBlock() {
        if(itemBlock == null) {
            this.itemBlock = new ItemBlock(this);
            this.itemBlock.setRegistryName(SCM.MOD_ID, this.blockName);
        }

        return this.itemBlock;
    }

    /**
     * @return 鉱石辞書の名前がある場合はtrue
     */
    public Boolean isOreDictionary() {
        return !this.oreDictionaryName.isEmpty();
    }

    /**
     * @return 鉱石辞書名を返す
     */
    public String getOreDictionaryName() {
        return oreDictionaryName;
    }

    /**
     * @return ドメインがMOD_ID, パスがアイテム名のResourceLocation
     */
    public ResourceLocation getResourceLocation() {
        return new ResourceLocation(SCM.MOD_ID, this.blockName);
    }
}
