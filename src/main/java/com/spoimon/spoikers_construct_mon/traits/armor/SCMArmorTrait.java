package com.spoimon.spoikers_construct_mon.traits.armor;

import c4.conarm.lib.traits.AbstractArmorTrait;
import net.minecraft.util.text.TextFormatting;

/**
 * SCMで追加されるアーマー用特性のベースクラス
 * IDの前に"scm_"が、後ろに"_armor"が自動的に追加される
 */
public class SCMArmorTrait extends AbstractArmorTrait {
    public SCMArmorTrait(String identifier, TextFormatting color) {
        super("scm_" +identifier, color);
    }

    public SCMArmorTrait(String identifier, int color) {
        super("scm_" + identifier, color);
    }
}
