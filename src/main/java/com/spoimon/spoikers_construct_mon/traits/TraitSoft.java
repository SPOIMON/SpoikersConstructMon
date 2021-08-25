package com.spoimon.spoikers_construct_mon.traits;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;

/**
 * ・柔らか
 *     - ふにゃふにゃ！
 *     - 耐久度を2倍消費する
 *
 * デメリット特性
 * 耐久度を2倍消費する
 */
public class TraitSoft extends SCMTrait {
    public TraitSoft() {
        super("soft", 0xddebed);
    }

    @Override
    public int onToolDamage(ItemStack tool, int damage, int newDamage, EntityLivingBase entity) {
        //耐久値を2倍消費する
        return newDamage * 2;
    }
}
