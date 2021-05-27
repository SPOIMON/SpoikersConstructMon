package com.spoimon.spoikers_construct_mon;

import com.spoimon.spoikers_construct_mon.register.ItemRegister;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLConstructionEvent;

@Mod(modid = SCM.MOD_ID, name = SCM.NAME, version = SCM.VERSION, dependencies = SCM.DEPENDENCIES)
public class SCM {
    public static final String MOD_ID = "spoikers_construct_mon";
    public static final String NAME = "Spoiker's ConstructMon";
    public static final String VERSION ="1.0.0_snap";
    public static final String DEPENDENCIES = "required-after:tconstruct@[1.12.2-2.13.0.183,);" + "required-after:mantle@[1.12-1.3.3.55,)";

    //SCMのアイテムの登録や管理
    public static ItemRegister itemRegister;

    @Mod.EventHandler
    public void construct(FMLConstructionEvent event) {
        itemRegister = new ItemRegister();
    }
}
