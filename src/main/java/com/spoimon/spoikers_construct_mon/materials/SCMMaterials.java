package com.spoimon.spoikers_construct_mon.materials;

import com.spoimon.spoikers_construct_mon.SCM;
import com.spoimon.spoikers_construct_mon.register.TraitsRegistry;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.oredict.OreDictionary;
import slimeknights.mantle.util.RecipeMatch;
import slimeknights.tconstruct.library.TinkerRegistry;
import slimeknights.tconstruct.library.fluid.FluidMolten;
import slimeknights.tconstruct.library.materials.*;
import slimeknights.tconstruct.library.smeltery.MeltingRecipe;
import slimeknights.tconstruct.library.utils.HarvestLevels;
import slimeknights.tconstruct.shared.TinkerFluids;
import slimeknights.tconstruct.smeltery.TinkerSmeltery;
import slimeknights.tconstruct.smeltery.block.BlockMolten;
import slimeknights.tconstruct.tools.TinkerTraits;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SCMMaterials {
    public static final Material tin = createMaterial("tin", 0xddebed);

    public static final Map<String, FluidMolten> SCMFluidMoltenMap = new HashMap<>();
    public static final Map<String, BlockMolten> SCMBlockMoltenMap = new HashMap<>();


    public static void setupMaterials() {
        /*
         * 錫マテリアル
         */
        tin.addCommonItems("Tin");
        tin.setFluid(TinkerFluids.tin);
        tin.setRepresentativeItem("ingotTin");
        tin.setCraftable(false);
        tin.setCastable(true);

        TinkerRegistry.addMaterialStats(tin,
                new HeadMaterialStats(168, 3.5f, 3.8f, HarvestLevels.IRON),
                new HandleMaterialStats(0.75f, -10),
                new ExtraMaterialStats(-10)
        );
        TinkerRegistry.addMaterialStats(tin, new BowMaterialStats(0.75f, 0.8f, 0f));
        tin.addTrait(TraitsRegistry.soft);
        tin.addTrait(TinkerTraits.lightweight);

        TinkerSmeltery.registerToolpartMeltingCasting(tin);
        TinkerRegistry.integrate(tin).preInit();
    }

    /**
     * マテリアルを生成し、登録する
     * もしそのIDのマテリアルがすでに存在する場合は、そのマテリアルを返す
     * @param materialId マテリアルのID
     * @param color マテリアルの色
     * @return 生成したマテリアル
     */
    private static Material createMaterial(String materialId, int color) {
        Material result = TinkerRegistry.getMaterial(materialId);
        if(result == Material.UNKNOWN) {
            result = new Material(materialId, color);
        }
        return result;
    }

    /**
     * そのマテリアル用の液体を自動的に作成する
     * @param material 液体を作成するマテリアル
     */
    private static void createMolten(Material material) {
        FluidMolten fluidMolten = new FluidMolten(material.getIdentifier(), material.materialTextColor, FluidMolten.ICON_MetalStill, FluidMolten.ICON_MetalFlowing);
        FluidRegistry.registerFluid(fluidMolten);
        fluidMolten.setTemperature(500);

        BlockMolten blockMolten = new BlockMolten(fluidMolten);
        blockMolten.setRegistryName(SCM.MOD_ID, "block_molten_" + material.getIdentifier());
        Item item = new ItemBlock(blockMolten).setRegistryName(blockMolten.getRegistryName());
        ForgeRegistries.BLOCKS.register(blockMolten);
        ForgeRegistries.ITEMS.register(item);

        FluidRegistry.addBucketForFluid(fluidMolten);

        SCM.proxy.registerFluidModel(fluidMolten);

        material.setCraftable(false);
        material.setCastable(true);
        material.setFluid(fluidMolten);
    }

    /**
     * 指定した鉱石辞書名から指定したマテリアルの液体を作るレシピを自動で登録する
     * 鉱石 / インゴット / ブロック のレシピが登録される
     * @param material 完成するマテリアル
     * @param oreDicPrefix 溶かすアイテムの鉱石辞書名の'ore'などを省いた部分
     */
    private static void autoSetupMeltingRecipe(Material material, String oreDicPrefix) {
        //鉱石レシピ
        addMeltingRecipe(material.getFluid(), "ore" + oreDicPrefix, Material.VALUE_Ore());
        //インゴットレシピ
        addMeltingRecipe(material.getFluid(), "ingot" + oreDicPrefix, Material.VALUE_Ingot);
        //ブロックレシピ
        addMeltingRecipe(material.getFluid(), "block" + oreDicPrefix, Material.VALUE_Block);
    }

    /**
     * 液体から鉱石ブロックを作成するレシピを自動で登録する
     * @param material 使用する液体のマテリアル
     * @param oreDicPrefix 完成するブロックの鉱石辞書名の'ore'などを省いた部分
     */
    private static void autoSetupBasinCasting(Material material, String oreDicPrefix) {
        List<ItemStack> mineralBlocks = OreDictionary.getOres("block" + oreDicPrefix);
        if(mineralBlocks.size() > 0) {
            TinkerRegistry.registerBasinCasting(
                    mineralBlocks.get(0), ItemStack.EMPTY, material.getFluid(), Material.VALUE_Block
            );
        }

    }

    /**
     * 乾式製錬炉でアイテムを溶かすレシピを追加する
     * @param fluid 溶けた時にできる液体
     * @param itemStack 溶かすアイテム
     * @param value 溶けた時に何mlバケツ作られるか
     */
    private static void addMeltingRecipe(Fluid fluid, ItemStack itemStack, int value) {
        TinkerRegistry.registerMelting(
                MeltingRecipe.forAmount( RecipeMatch.of(itemStack, value ),
                        fluid, value)
        );
    }

    /**
     * 乾式製錬炉でアイテムを溶かすレシピを追加する
     * @param fluid 溶けた時にできる液体
     * @param oreDictionary 溶かすアイテムの鉱石辞書名
     * @param value 溶けた時に何mlバケツ作られるか
     */
    private static void addMeltingRecipe(Fluid fluid, String oreDictionary, int value) {
        TinkerRegistry.registerMelting(
                MeltingRecipe.forAmount( RecipeMatch.of(oreDictionary, value ),
                        fluid, value)
        );
    }
}
