package net.slimevoid.moresigns.core;

import java.io.File;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraftforge.common.config.Configuration;
import net.slimevoid.moresigns.blocks.BlockMoreSigns;
import net.slimevoid.moresigns.core.lib.ItemLib;
import net.slimevoid.moresigns.items.ItemSignParts;
import net.slimevoid.moresigns.items.ItemSignTool;
import net.slimevoid.moresigns.items.ItemSigns;
import net.slimevoid.moresigns.tileentities.TileEntitySign;
import cpw.mods.fml.common.registry.GameRegistry;

public class MSCore {
    public static File          configFile;
    public static Configuration configuration;
    public static boolean       initialized = false;

    public static void preInit() {
        if (initialized) return;
        initialized = true;
        MSInit.preInit();
    }

    public static void addItems() {
        MSBlocks.mtSignPost.id = configurationProperties();
        // RecipeRemover.registerItemRecipeToRemove(Item.sign);
        // RecipeRemover.removeCrafting();
        // BlockRemover.removeVanillaBlock(Block.signPost,
        // true);
        // BlockRemover.removeVanillaBlock(Block.signWall,
        // true);
        // ItemRemover.removeVanillaItem(Item.sign);
        MSBlocks.mtSignPost.me = (new BlockMoreSigns(MSBlocks.mtSignPost.id, TileEntitySign.class, true, 1F, 2F, true, true)).setBlockName("mtSignPost");
        MSBlocks.mtSignWall.me = (new BlockMoreSigns(MSBlocks.mtSignWall.id, TileEntitySign.class, false, 1F, 2F, true, true)).setBlockName("mtSignWall");
        GameRegistry.registerTileEntity(TileEntitySign.class,
                                        "mtSign");
        MSItems.mtsItemSignParts.me = (new ItemSignParts(MSItems.mtsItemSignParts.offsetID())).setUnlocalizedName(ItemLib.PART_PREFIX);
        MSItems.mtsItemSigns.me = (new ItemSigns(MSItems.mtsItemSigns.getID())).setUnlocalizedName(ItemLib.SIGN_PREFIX);
        MSItems.mtsItemSignTool.me = (new ItemSignTool(MSItems.mtsItemSignTool.offsetID())).setUnlocalizedName(ItemLib.TOOL_PREFIX);
        for (MSItemParts part : MSItemParts.values()) {
            part.me = new ItemStack(MSItems.mtsItemSignParts.me, 1, part.stackID);
        }
        for (MSItemSigns sign : MSItemSigns.values()) {
            sign.me = new ItemStack(MSItems.mtsItemSigns.me, 1, sign.stackID);
        }
        setDroppedItems();
    }

    private static void setDroppedItems() {
        MSItemSigns.woodenSign.droppedItem = MSItemSigns.woodenSign.me.splitStack(1);
        MSItemSigns.ironCladSign.droppedItem = MSItemParts.ironCladPlating.me.splitStack(1);
        MSItemSigns.goldPlatedSign.droppedItem = MSItemParts.goldPlating.me.splitStack(1);
        MSItemSigns.diamondLatheredSign.droppedItem = MSItemParts.diamondPlating.me.splitStack(1);
    }

    public static void registerBlocks() {
        for (MSBlocks block : MSBlocks.values()) {
            if (block != null && block.me != null) {
                System.out.println("Registering Block[" + block.name + "]");
                GameRegistry.registerBlock(block.me,
                                           block.name);
            }
        }
    }

    public static void addRecipes() {

        // Plating
        GameRegistry.addRecipe(MSItemParts.ironCladPlating.me.splitStack(2),
                               new Object[] {
                                       "XX",
                                       "XX",
                                       Character.valueOf('X'),
                                       Items.iron_ingot });

        GameRegistry.addRecipe(MSItemParts.goldPlating.me.splitStack(2),
                               new Object[] {
                                       "XX",
                                       "XX",
                                       Character.valueOf('X'),
                                       Items.gold_ingot });

        GameRegistry.addRecipe(MSItemParts.diamondPlating.me.splitStack(2),
                               new Object[] {
                                       "XX",
                                       "XX",
                                       Character.valueOf('X'),
                                       Items.diamond });

        // Poles
        GameRegistry.addRecipe(MSItemParts.ironCladPole.me.splitStack(8),
                               new Object[] {
                                       "X",
                                       "Y",
                                       Character.valueOf('X'),
                                       Items.iron_ingot,
                                       Character.valueOf('Y'),
                                       Items.stick });

        GameRegistry.addRecipe(MSItemParts.goldenPole.me.splitStack(8),
                               new Object[] {
                                       "X",
                                       "Y",
                                       Character.valueOf('X'),
                                       Items.gold_ingot,
                                       Character.valueOf('Y'),
                                       Items.stick });

        GameRegistry.addRecipe(MSItemParts.diamondPole.me.splitStack(8),
                               new Object[] {
                                       "X",
                                       "Y",
                                       Character.valueOf('X'),
                                       Items.diamond,
                                       Character.valueOf('Y'),
                                       Items.stick });

        // Signs
        GameRegistry.addRecipe(MSItemSigns.ironCladSign.me.splitStack(1),
                               new Object[] {
                                       "X",
                                       "Y",
                                       Character.valueOf('X'),
                                       MSItemParts.ironCladPlating.me,
                                       Character.valueOf('Y'),
                                       MSItemParts.ironCladPole.me });
        GameRegistry.addRecipe(MSItemSigns.goldPlatedSign.me.splitStack(1),
                               new Object[] {
                                       "X",
                                       "Y",
                                       Character.valueOf('X'),
                                       MSItemParts.goldPlating.me,
                                       Character.valueOf('Y'),
                                       MSItemParts.goldenPole.me });
        GameRegistry.addRecipe(MSItemSigns.diamondLatheredSign.me.splitStack(1),
                               new Object[] {
                                       "X",
                                       "Y",
                                       Character.valueOf('X'),
                                       MSItemParts.diamondPlating.me,
                                       Character.valueOf('Y'),
                                       MSItemParts.diamondPole.me });

        GameRegistry.addRecipe(MSItemSigns.woodenSign.me.splitStack(3),
                               new Object[] {
                                       "XXX",
                                       "XXX",
                                       " Y ",
                                       Character.valueOf('X'),
                                       Blocks.planks,
                                       Character.valueOf('Y'),
                                       Items.stick });

        GameRegistry.addRecipe(MSItemSigns.ironCladSign.me.splitStack(1),
                               new Object[] {
                                       "X",
                                       "Y",
                                       "X",
                                       Character.valueOf('X'),
                                       Items.iron_ingot,
                                       Character.valueOf('Y'),
                                       MSItemSigns.woodenSign.me });

        GameRegistry.addRecipe(MSItemSigns.goldPlatedSign.me.splitStack(1),
                               new Object[] {
                                       "X",
                                       "Y",
                                       "X",
                                       Character.valueOf('X'),
                                       Items.gold_ingot,
                                       Character.valueOf('Y'),
                                       MSItemSigns.woodenSign.me });

        GameRegistry.addRecipe(MSItemSigns.diamondLatheredSign.me.splitStack(1),
                               new Object[] {
                                       "X",
                                       "Y",
                                       "X",
                                       Character.valueOf('X'),
                                       Items.diamond,
                                       Character.valueOf('Y'),
                                       MSItemSigns.woodenSign.me });

        FurnaceRecipes.smelting().func_151394_a/* .addSmelting */(new ItemStack(MSItems.mtsItemSignParts.me, MSItemParts.ironCladPlating.stackID),
                                                                  (new ItemStack(Items.iron_ingot, 2)),
                                                                  0);
        FurnaceRecipes.smelting().func_151394_a/* addSmelting */(new ItemStack(MSItems.mtsItemSignParts.me, MSItemParts.goldPlating.stackID),
                                                                 (new ItemStack(Items.gold_ingot, 2)),
                                                                 2);
        FurnaceRecipes.smelting().func_151394_a/* addSmelting */(new ItemStack(MSItems.mtsItemSignParts.me, MSItemParts.diamondPlating.stackID),
                                                                 (new ItemStack(Items.diamond, 2)),
                                                                 4);

        // Wand
        GameRegistry.addRecipe(new ItemStack(MSItems.mtsItemSignTool.me, 1),
                               new Object[] {
                                       "OXO",
                                       "ISI",
                                       "OXO",
                                       Character.valueOf('X'),
                                       Items.iron_ingot,
                                       Character.valueOf('I'),
                                       new ItemStack(Items.dye, 1, 0),
                                       Character.valueOf('S'),
                                       Items.stick });
    }

    public static int configurationProperties() {
        configuration.load();
        MSBlocks.mtSignWall.name = "Multi-Textured Wall-Sign";
        MSBlocks.mtSignPost.name = "Multi-Textured Sign-Post";

        MSItems.mtsItemSignParts.setID(configuration.get(Configuration.CATEGORY_GENERAL,
                                                         "mtSignParts",
                                                         7000).getInt());
        MSItems.mtsItemSignParts.name = "Multi-Textured Sign Part";
        // .value));
        MSItems.mtsItemSigns.name = "Multi-Textured Sign";

        MSItems.mtsItemSignTool.setID(configuration.get(Configuration.CATEGORY_GENERAL,
                                                        "mtSignTool",
                                                        7001).getInt());
        MSItems.mtsItemSignTool.name = "Multi-Textured Sign Wand";

        MSItemParts.ironCladPlating.name = "Iron-Clad Plating";
        MSItemParts.ironCladPlating.stackID = 0;
        MSItemParts.ironCladPole.name = "Iron-Clad Pole";
        MSItemParts.ironCladPole.stackID = 1;
        MSItemParts.goldPlating.name = "Gold Plating";
        MSItemParts.goldPlating.stackID = 2;
        MSItemParts.goldenPole.name = "Golden Pole";
        MSItemParts.goldenPole.stackID = 3;
        MSItemParts.diamondPlating.name = "Diamond-Studded Plating";
        MSItemParts.diamondPlating.stackID = 4;
        MSItemParts.diamondPole.name = "Diamond-Encrusted Pole";
        MSItemParts.diamondPole.stackID = 5;
        MSItemSigns.woodenSign.name = "Wooden Sign";
        MSItemSigns.woodenSign.stackID = 0;
        MSItemSigns.ironCladSign.name = "Iron-Clad Sign";
        MSItemSigns.ironCladSign.stackID = 1;
        MSItemSigns.goldPlatedSign.name = "Gold-Plated Sign";
        MSItemSigns.goldPlatedSign.stackID = 2;
        MSItemSigns.diamondLatheredSign.name = "Diamond-Lathered Sign";
        MSItemSigns.diamondLatheredSign.stackID = 3;

        configuration.save();
        return MSBlocks.mtSignPost.id;
    }
}
