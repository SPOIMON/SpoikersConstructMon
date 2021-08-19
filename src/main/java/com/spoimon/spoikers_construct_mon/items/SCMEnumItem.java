package com.spoimon.spoikers_construct_mon.items;

import com.spoimon.spoikers_construct_mon.SCM;
import com.spoimon.spoikers_construct_mon.client.SCMCreativeTab;
import com.spoimon.spoikers_construct_mon.utils.StringUtil;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import slimeknights.mantle.block.EnumBlock;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.ArrayList;
import java.util.List;

/**
 * SCMで追加されるメタデータアイテムのベースクラス
 * 種類などはEnumで指定する
 * @param <T>
 */
public class SCMEnumItem <T extends java.lang.Enum<T> & EnumBlock.IEnumMeta & IStringSerializable> extends Item {
    public final PropertyEnum<T> prop;
    protected final T[] values;

    protected String oreDictionaryCategory;
    protected String registryName;

    public SCMEnumItem(PropertyEnum<T> prop, Class<T> clazz, String registryName) {
        super();

        this.setRegistryName(SCM.MOD_ID, registryName);
        this.setHasSubtypes(true);
        this.setMaxDamage(0);
        this.setCreativeTab(SCMCreativeTab.INSTANCE);

        this.prop = prop;
        this.values = clazz.getEnumConstants();
        this.registryName = registryName;
    }

    public SCMEnumItem(PropertyEnum<T> prop, Class<T> clazz, String registryName, String oreDictionaryCategory) {
        this(prop, clazz, registryName);
        this.oreDictionaryCategory = oreDictionaryCategory;
    }

    /**
     * @return MODIDが含まれないレジストリ名を返す
     */
    public String getRegistryNameNoMODID() {
        return this.registryName;
    }

    /**
     * @return メタデータ全ての名前のリストを返す
     */
    public List<String> getMetaNameList() {
        ArrayList<String> result = new ArrayList<>();
        for(T value : values) {
            result.add(value.getName());
        }
        return result;
    }

    /**
     * @return 鉱石辞書カテゴリが設定されている場合はtrueを返す
     */
    public Boolean isOreDictionary() {
        return this.oreDictionaryCategory != null;
    }

    /**
     * @return メタデータ全ての鉱石辞書名のリストを返す
     */
    public List<String> getMetaOreDictionaryList() {
        ArrayList<String> result = new ArrayList<>();
        for(T value : values) {
            result.add(this.oreDictionaryCategory + StringUtil.toFirstUpper(value.getName()));
        }
        return result;
    }

    /**
     * @return ドメインがMOD_ID, パスがアイテム名のResourceLocation
     */
    public ResourceLocation getResourceLocation() {
        return new ResourceLocation(SCM.MOD_ID, this.registryName);
    }

    @Override
    @ParametersAreNonnullByDefault
    public String getUnlocalizedName(ItemStack stack) {
        String result = "";
        int meta = stack.getMetadata();
        return "item." + SCM.MOD_ABBREVIATION + "." + this.registryName + "." + values[meta].getName();
    }

    @Override
    public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> items) {
        if(this.isInCreativeTab(tab)) {
            for(int i = 0; i < values.length; i++) {
                items.add(new ItemStack(this, 1, i));
            }
        }
    }
}
