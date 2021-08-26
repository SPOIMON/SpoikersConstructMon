package com.spoimon.spoikers_construct_mon.traits.armor;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;

/**
 * ・柔らか
 *     - ふにゃふにゃ！
 *     - 耐久度を2倍消費する
 *
 * デメリット特性
 * 耐久度を2倍消費する
 */
public class ArmorTraitSoft extends SCMArmorTrait {
    public ArmorTraitSoft() {
        super("soft", 0xddebed);
    }

    @Override
    public int onArmorDamage(ItemStack armor, DamageSource source, int damage, int newDamage, EntityPlayer player, int slot) {
        //耐久値を2倍消費する
        return newDamage * 2;
    }
}
