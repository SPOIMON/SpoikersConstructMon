package com.spoimon.spoikers_construct_mon.blocks;

import com.spoimon.spoikers_construct_mon.SCM;
import com.spoimon.spoikers_construct_mon.client.SCMCreativeTab;
import com.spoimon.spoikers_construct_mon.utils.StringUtil;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.ResourceLocation;
import slimeknights.mantle.block.EnumBlock;
import slimeknights.mantle.item.ItemBlockMeta;

import java.util.ArrayList;
import java.util.List;

/**
 * SCMで追加されるメタデータを持つブロックのベースクラス
 * レジストリ名, 翻訳キー, クリエイティブタブが自動で設定される
 * @author riku1227
 */
public class SCMEnumBlock<T extends java.lang.Enum<T> & EnumBlock.IEnumMeta & IStringSerializable > extends EnumBlock<T> {
    public final PropertyEnum<T> prop;
    protected final T[] values;

    public String blockName;
    protected String oreDictionaryCategory;
    protected ItemBlock itemBlock;

    public SCMEnumBlock(PropertyEnum<T> prop, Class<T> clazz, Material material, String blockName) {
        super(material, prop, clazz);
        this.prop = prop;
        this.values = clazz.getEnumConstants();
        this.blockName = blockName;

        setCreativeTab(SCMCreativeTab.INSTANCE);
        setRegistryName(SCM.MOD_ID, blockName);
        setUnlocalizedName(SCM.MOD_ABBREVIATION + "." + blockName);
    }

    public SCMEnumBlock(PropertyEnum<T> prop, Class<T> clazz, Material material, String registryName, String oreDictionaryCategory) {
        this(prop, clazz, material, registryName);
        this.oreDictionaryCategory = oreDictionaryCategory;
    }

    /**
     * ItemBlockを返す
     * ItemBlockがまだ作られていない場合は新規で作成する
     * @return そのブロックのアイテムブロック
     */
    public ItemBlock getItemBlock() {
        if(this.itemBlock == null) {
            this.itemBlock = new ItemBlockMeta(this);
            this.itemBlock.setRegistryName(SCM.MOD_ID, blockName);
            //ItemBlockMeta.setMappingProperty(this, this.prop);
        }

        return this.itemBlock;
    }

    /**
     * @return 鉱石辞書の名前がある場合はtrue
     */
    public Boolean isOreDictionary() {
        return this.oreDictionaryCategory != null;
    }

    /**
     * @return 鉱石辞書のカテゴリ(前に付けるやつ'ore'や'ingot'など)を返す
     */
    public String getOreDictionaryCategory() {
        return oreDictionaryCategory;
    }

    /**
     * 指定されたEnum全ての鉱石辞書名のリストを生成して返す
     * @return メタデータすべての香積寺署名のリスト
     */
    public List<String> getOreDictionaryList() {
        ArrayList<String> oreDicList = new ArrayList<>();
        for(T value : values) {
            oreDicList.add(getOreDictionaryCategory() + StringUtil.toFirstUpper(value.getName()));
        }

        return oreDicList;
    }

    public ArrayList<String> getMetaNameList() {
        ArrayList<String> metaNameList = new ArrayList<>();
        for (T value : values) {
            metaNameList.add(value.getName());
        }
        return metaNameList;
    }

    /**
     * @return ドメインがMOD_ID, パスがブロック名のResourceLocation
     */
    public ResourceLocation getResourceLocation() {
        return new ResourceLocation(SCM.MOD_ID, this.blockName);
    }

    /**
     * @return ドメインがMOD_ID, パスがブロック名'_item'のResourceLocation
     */
    public ResourceLocation getItemBlockResourceLocation() {
        return new ResourceLocation(SCM.MOD_ID, this.blockName + "_item");
    }
}
