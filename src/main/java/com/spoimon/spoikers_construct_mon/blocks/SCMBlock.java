package com.spoimon.spoikers_construct_mon.blocks;

import com.spoimon.spoikers_construct_mon.SCM;
import com.spoimon.spoikers_construct_mon.client.SCMCreativeTab;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.ResourceLocation;

/**
 * SCMで追加されるブロックのベースクラス
 * レジストリ名, 未翻訳名, クリエイティブタブが自動で設定される
 * @author riku1227
 */
public class SCMBlock extends Block {
    public String blockName;
    protected ItemBlock itemBlock;

    public SCMBlock(Material blockMaterial, String blockName) {
        super(blockMaterial);
        this.blockName = blockName;
        this.setRegistryName(SCM.MOD_ID, blockName);
        this.setCreativeTab(SCMCreativeTab.INSTANCE);
        this.setUnlocalizedName(blockName);
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
     * @return ドメインがMOD_ID, パスがアイテム名のResourceLocation
     */
    public ResourceLocation getResourceLocation() {
        return new ResourceLocation(SCM.MOD_ID, this.blockName);
    }
}
