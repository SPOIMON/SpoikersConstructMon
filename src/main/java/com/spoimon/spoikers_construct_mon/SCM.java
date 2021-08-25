package com.spoimon.spoikers_construct_mon;

import com.spoimon.spoikers_construct_mon.materials.SCMMaterials;
import com.spoimon.spoikers_construct_mon.proxy.CommonProxy;
import com.spoimon.spoikers_construct_mon.registry.BlockRegistry;
import com.spoimon.spoikers_construct_mon.registry.ItemRegistry;
import com.spoimon.spoikers_construct_mon.registry.RecipeRegistry;
import com.spoimon.spoikers_construct_mon.world.generator.OreGenerator;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLConstructionEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

@Mod(modid = SCM.MOD_ID, name = SCM.NAME, version = SCM.VERSION, dependencies = SCM.DEPENDENCIES)
public class SCM {
    //MODのID
    public static final String MOD_ID = "spoikers_construct_mon";
    //MODの略称
    public static final String MOD_ABBREVIATION = "scm";
    //MODの名前
    public static final String NAME = "Spoiker's ConstructMon";
    //MODのバージョン
    public static final String VERSION ="1.0.0_snap";
    //MODの依存関係
    public static final String DEPENDENCIES = "required-after:tconstruct@[1.12.2-2.13.0.183,);" + "required-after:mantle@[1.12-1.3.3.55,)";

    @SidedProxy(clientSide = "com.spoimon.spoikers_construct_mon.proxy.ClientProxy", serverSide = "com.spoimon.spoikers_construct_mon.proxy.CommonProxy")
    public static CommonProxy proxy;

    //SCMで追加されるアイテムの登録や管理
    public static ItemRegistry itemRegistry;
    //SCMで追加されるブロックの登録や管理
    public static BlockRegistry blockRegistry;
    //SC<で追加されるレシピの登録
    public static RecipeRegistry recipeRegistry;

    @Mod.EventHandler
    public void construct(FMLConstructionEvent event) {
        itemRegistry = new ItemRegistry();
        blockRegistry = new BlockRegistry();
        recipeRegistry = new RecipeRegistry();
        GameRegistry.registerWorldGenerator(new OreGenerator(), 0);
    }

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        MinecraftForge.EVENT_BUS.register(this);
        SCMMaterials.setupMaterials();
    }
}
