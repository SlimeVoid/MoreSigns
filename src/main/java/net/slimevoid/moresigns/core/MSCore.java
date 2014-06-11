package net.slimevoid.moresigns.core;

import java.io.File;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraftforge.common.config.Configuration;
import net.slimevoid.moresigns.core.lib.BlockLib;
import net.slimevoid.moresigns.core.lib.EnumBlockSigns;
import net.slimevoid.moresigns.core.lib.EnumItemSignParts;
import net.slimevoid.moresigns.core.lib.EnumItemSigns;
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
        configurationProperties();
        // RecipeRemover.registerItemRecipeToRemove(Item.sign);
        // RecipeRemover.removeCrafting();
        // BlockRemover.removeVanillaBlock(Block.signPost,
        // true);
        // BlockRemover.removeVanillaBlock(Block.signWall,
        // true);
        // ItemRemover.removeVanillaItem(Item.sign);
        EnumBlockSigns.registerBlockSigns();
        setBlockReferences();
        EnumItemSigns.registerItemSigns();
        EnumItemSignParts.registerItemSignParts();
        setDroppedItems();
        GameRegistry.registerTileEntity(TileEntitySign.class,
                                        BlockLib.BLOCK_SIGN);
    }

    private static void setBlockReferences() {
        EnumItemSigns.IRON.setBlockReference(EnumBlockSigns.IRON_STANDING.getInstance(),
                                             EnumBlockSigns.IRON_WALL.getInstance());
        EnumItemSigns.GOLD.setBlockReference(EnumBlockSigns.GOLD_STANDING.getInstance(),
                                             EnumBlockSigns.GOLD_WALL.getInstance());
        EnumItemSigns.DIAMOND.setBlockReference(EnumBlockSigns.DIAMOND_STANDING.getInstance(),
                                                EnumBlockSigns.DIAMOND_WALL.getInstance());
    }

    private static void setDroppedItems() {
        EnumBlockSigns.IRON_STANDING.setDroppedItem(EnumItemSignParts.IRON_PLATING.getItemStack(1));
        EnumBlockSigns.IRON_WALL.setDroppedItem(EnumItemSignParts.IRON_PLATING.getItemStack(1));
        EnumBlockSigns.GOLD_STANDING.setDroppedItem(EnumItemSignParts.GOLD_PLATING.getItemStack(1));
        EnumBlockSigns.GOLD_WALL.setDroppedItem(EnumItemSignParts.GOLD_PLATING.getItemStack(1));
        EnumBlockSigns.DIAMOND_STANDING.setDroppedItem(EnumItemSignParts.DIAMOND_PLATING.getItemStack(1));
        EnumBlockSigns.DIAMOND_WALL.setDroppedItem(EnumItemSignParts.DIAMOND_PLATING.getItemStack(1));
    }

    public static void addRecipes() {

        // Plating
        GameRegistry.addRecipe(EnumItemSignParts.IRON_PLATING.getItemStack(2),
                               new Object[] {
                                       "XX",
                                       "XX",
                                       Character.valueOf('X'),
                                       Items.iron_ingot });

        GameRegistry.addRecipe(EnumItemSignParts.GOLD_PLATING.getItemStack(2),
                               new Object[] {
                                       "XX",
                                       "XX",
                                       Character.valueOf('X'),
                                       Items.gold_ingot });

        GameRegistry.addRecipe(EnumItemSignParts.DIAMOND_PLATING.getItemStack(2),
                               new Object[] {
                                       "XX",
                                       "XX",
                                       Character.valueOf('X'),
                                       Items.diamond });

        // Poles
        GameRegistry.addRecipe(EnumItemSignParts.IRON_POLE.getItemStack(8),
                               new Object[] {
                                       "X",
                                       "Y",
                                       Character.valueOf('X'),
                                       Items.iron_ingot,
                                       Character.valueOf('Y'),
                                       Items.stick });

        GameRegistry.addRecipe(EnumItemSignParts.GOLD_POLE.getItemStack(8),
                               new Object[] {
                                       "X",
                                       "Y",
                                       Character.valueOf('X'),
                                       Items.gold_ingot,
                                       Character.valueOf('Y'),
                                       Items.stick });

        GameRegistry.addRecipe(EnumItemSignParts.DIAMOND_POLE.getItemStack(8),
                               new Object[] {
                                       "X",
                                       "Y",
                                       Character.valueOf('X'),
                                       Items.diamond,
                                       Character.valueOf('Y'),
                                       Items.stick });

        // Signs
        GameRegistry.addRecipe(EnumItemSigns.IRON.getItemStack(1),
                               new Object[] {
                                       "X",
                                       "Y",
                                       Character.valueOf('X'),
                                       EnumItemSignParts.IRON_PLATING.getItemStack(1),
                                       Character.valueOf('Y'),
                                       EnumItemSignParts.IRON_POLE.getItemStack(1) });
        GameRegistry.addRecipe(EnumItemSigns.GOLD.getItemStack(1),
                               new Object[] {
                                       "X",
                                       "Y",
                                       Character.valueOf('X'),
                                       EnumItemSignParts.GOLD_PLATING.getItemStack(1),
                                       Character.valueOf('Y'),
                                       EnumItemSignParts.GOLD_POLE.getItemStack(1) });
        GameRegistry.addRecipe(EnumItemSigns.DIAMOND.getItemStack(1),
                               new Object[] {
                                       "X",
                                       "Y",
                                       Character.valueOf('X'),
                                       EnumItemSignParts.DIAMOND_PLATING.getItemStack(1),
                                       Character.valueOf('Y'),
                                       EnumItemSignParts.DIAMOND_POLE.getItemStack(1) });

        GameRegistry.addRecipe(EnumItemSigns.IRON.getItemStack(1),
                               new Object[] {
                                       "X",
                                       "Y",
                                       "X",
                                       Character.valueOf('X'),
                                       Items.iron_ingot,
                                       Character.valueOf('Y'),
                                       Items.sign });

        GameRegistry.addRecipe(EnumItemSigns.GOLD.getItemStack(1),
                               new Object[] {
                                       "X",
                                       "Y",
                                       "X",
                                       Character.valueOf('X'),
                                       Items.gold_ingot,
                                       Character.valueOf('Y'),
                                       Items.sign });

        GameRegistry.addRecipe(EnumItemSigns.DIAMOND.getItemStack(1),
                               new Object[] {
                                       "X",
                                       "Y",
                                       "X",
                                       Character.valueOf('X'),
                                       Items.diamond,
                                       Character.valueOf('Y'),
                                       Items.sign });

        FurnaceRecipes.smelting().func_151394_a/* .addSmelting */(EnumItemSignParts.IRON_PLATING.getItemStack(1),
                                                                  (new ItemStack(Items.iron_ingot, 2)),
                                                                  0);
        FurnaceRecipes.smelting().func_151394_a/* addSmelting */(EnumItemSignParts.GOLD_PLATING.getItemStack(1),
                                                                 (new ItemStack(Items.gold_ingot, 2)),
                                                                 2);
        FurnaceRecipes.smelting().func_151394_a/* addSmelting */(EnumItemSignParts.DIAMOND_PLATING.getItemStack(1),
                                                                 (new ItemStack(Items.diamond, 2)),
                                                                 4);

        // Wand
        GameRegistry.addRecipe(EnumItemSignParts.SIGN_TOOL.getItemStack(1),
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

    public static void configurationProperties() {
        configuration.load();

        configuration.save();
    }
}
