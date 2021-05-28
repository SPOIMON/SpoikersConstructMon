package com.spoimon.spoikers_construct_mon;

import com.spoimon.spoikers_construct_mon.register.BlockRegister;
import com.spoimon.spoikers_construct_mon.register.ItemRegister;
import com.spoimon.spoikers_construct_mon.world.generator.OreGenerator;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLConstructionEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

@Mod(modid = SCM.MOD_ID, name = SCM.NAME, version = SCM.VERSION, dependencies = SCM.DEPENDENCIES)
public class SCM {
    public static final String MOD_ID = "spoikers_construct_mon";
    public static final String NAME = "Spoiker's ConstructMon";
    public static final String VERSION ="1.0.0_snap";
    public static final String DEPENDENCIES = "required-after:tconstruct@[1.12.2-2.13.0.183,);" + "required-after:mantle@[1.12-1.3.3.55,)";

    //SCMで追加されるアイテムの登録や管理
    public static ItemRegister itemRegister;
    //SCMで追加されるブロックの登録や管理
    public static BlockRegister blockRegister;

    @Mod.EventHandler
    public void construct(FMLConstructionEvent event) {
        itemRegister = new ItemRegister();
        blockRegister = new BlockRegister();
        GameRegistry.registerWorldGenerator(new OreGenerator(), 0);
    }
}
