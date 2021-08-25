package com.spoimon.spoikers_construct_mon.traits;

import net.minecraft.util.text.TextFormatting;
import slimeknights.tconstruct.library.traits.AbstractTrait;

public class SCMTrait extends AbstractTrait {
    public SCMTrait(String identifier, TextFormatting color) {
        super("scm_" + identifier, color);
    }

    public SCMTrait(String identifier, int color) {
        super("scm_" + identifier, color);
    }
}
