package com.slimevoid.moresigns.core;

import java.io.File;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraftforge.common.Configuration;
import com.slimevoid.library.util.BlockRemover;
import com.slimevoid.library.util.ItemRemover;
import com.slimevoid.library.util.RecipeRemover;
import com.slimevoid.moresigns.blocks.BlockMoreSigns;
import com.slimevoid.moresigns.core.lib.ItemLib;
import com.slimevoid.moresigns.core.lib.LocalizationLib;
import com.slimevoid.moresigns.items.ItemSignParts;
import com.slimevoid.moresigns.items.ItemSignTool;
import com.slimevoid.moresigns.items.ItemSigns;
import com.slimevoid.moresigns.tileentities.TileEntitySign;

import cpw.mods.fml.common.registry.GameRegistry;

public class MSCore {
	public static File			configFile;
	public static Configuration	configuration;
	public static boolean		initialized	= false;

	public static void initialize() {
		if (initialized) return;
		initialized = true;
		MSInit.initialize();
	}

	public static void addItems() {
		MSBlocks.mtSignPost.id = configurationProperties();
		RecipeRemover.registerItemRecipeToRemove(Item.sign);
		RecipeRemover.removeCrafting();
		BlockRemover.removeVanillaBlock(Block.signPost,
										true);
		BlockRemover.removeVanillaBlock(Block.signWall,
										true);
		ItemRemover.removeVanillaItem(Item.sign);
		MSBlocks.mtSignPost.me = (new BlockMoreSigns(MSBlocks.mtSignPost.id, TileEntitySign.class, true, 1F, 2F, true, true)).setUnlocalizedName("mtSignPost");
		MSBlocks.mtSignWall.me = (new BlockMoreSigns(MSBlocks.mtSignWall.id, TileEntitySign.class, false, 1F, 2F, true, true)).setUnlocalizedName("mtSignWall");
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
				GameRegistry.registerBlock(	block.me,
											block.name);
			}
		}
	}

	public static void addItemNames() {
		/*
		 * for (MTSItems item : MTSItems.values()) { if (item != null && item.me
		 * != null && item.name != null) { LanguageRegistry.addName(item.me,
		 * item.name); } } for (MTSItemParts part : MTSItemParts.values()) { if
		 * (part != null && part.me != null && part.name != null) {
		 * LanguageRegistry.addName(part.me, part.name); } } for (MTSItemSigns
		 * sign : MTSItemSigns.values()) { if (sign != null && sign.me != null
		 * && sign.name != null) { LanguageRegistry.addName(sign.me, sign.name);
		 * } }
		 */
		LocalizationLib.registerLanguages();
	}

	public static void addRecipes() {

		// Plating
		GameRegistry.addRecipe(	MSItemParts.ironCladPlating.me.splitStack(2),
								new Object[] {
										"XX",
										"XX",
										Character.valueOf('X'),
										Item.ingotIron });

		GameRegistry.addRecipe(	MSItemParts.goldPlating.me.splitStack(2),
								new Object[] {
										"XX",
										"XX",
										Character.valueOf('X'),
										Item.ingotGold });

		GameRegistry.addRecipe(	MSItemParts.diamondPlating.me.splitStack(2),
								new Object[] {
										"XX",
										"XX",
										Character.valueOf('X'),
										Item.diamond });

		// Poles
		GameRegistry.addRecipe(	MSItemParts.ironCladPole.me.splitStack(8),
								new Object[] {
										"X",
										"Y",
										Character.valueOf('X'),
										Item.ingotIron,
										Character.valueOf('Y'),
										Item.stick });

		GameRegistry.addRecipe(	MSItemParts.goldenPole.me.splitStack(8),
								new Object[] {
										"X",
										"Y",
										Character.valueOf('X'),
										Item.ingotGold,
										Character.valueOf('Y'),
										Item.stick });

		GameRegistry.addRecipe(	MSItemParts.diamondPole.me.splitStack(8),
								new Object[] {
										"X",
										"Y",
										Character.valueOf('X'),
										Item.diamond,
										Character.valueOf('Y'),
										Item.stick });

		// Signs
		GameRegistry.addRecipe(	MSItemSigns.ironCladSign.me.splitStack(1),
								new Object[] {
										"X",
										"Y",
										Character.valueOf('X'),
										MSItemParts.ironCladPlating.me,
										Character.valueOf('Y'),
										MSItemParts.ironCladPole.me });
		GameRegistry.addRecipe(	MSItemSigns.goldPlatedSign.me.splitStack(1),
								new Object[] {
										"X",
										"Y",
										Character.valueOf('X'),
										MSItemParts.goldPlating.me,
										Character.valueOf('Y'),
										MSItemParts.goldenPole.me });
		GameRegistry.addRecipe(	MSItemSigns.diamondLatheredSign.me.splitStack(1),
								new Object[] {
										"X",
										"Y",
										Character.valueOf('X'),
										MSItemParts.diamondPlating.me,
										Character.valueOf('Y'),
										MSItemParts.diamondPole.me });

		GameRegistry.addRecipe(	MSItemSigns.woodenSign.me.splitStack(3),
								new Object[] {
										"XXX",
										"XXX",
										" Y ",
										Character.valueOf('X'),
										Block.planks,
										Character.valueOf('Y'),
										Item.stick });

		GameRegistry.addRecipe(	MSItemSigns.ironCladSign.me.splitStack(1),
								new Object[] {
										"X",
										"Y",
										"X",
										Character.valueOf('X'),
										Item.ingotIron,
										Character.valueOf('Y'),
										MSItemSigns.woodenSign.me });

		GameRegistry.addRecipe(	MSItemSigns.goldPlatedSign.me.splitStack(1),
								new Object[] {
										"X",
										"Y",
										"X",
										Character.valueOf('X'),
										Item.ingotGold,
										Character.valueOf('Y'),
										MSItemSigns.woodenSign.me });

		GameRegistry.addRecipe(	MSItemSigns.diamondLatheredSign.me.splitStack(1),
								new Object[] {
										"X",
										"Y",
										"X",
										Character.valueOf('X'),
										Item.diamond,
										Character.valueOf('Y'),
										MSItemSigns.woodenSign.me });

		FurnaceRecipes.smelting().addSmelting(	MSItems.mtsItemSignParts.getID(),
												MSItemParts.ironCladPlating.stackID,
												(new ItemStack(Item.ingotIron, 2)),
												0);
		FurnaceRecipes.smelting().addSmelting(	MSItems.mtsItemSignParts.getID(),
												MSItemParts.goldPlating.stackID,
												(new ItemStack(Item.ingotGold, 2)),
												2);
		FurnaceRecipes.smelting().addSmelting(	MSItems.mtsItemSignParts.getID(),
												MSItemParts.diamondPlating.stackID,
												(new ItemStack(Item.diamond, 2)),
												4);

		// Wand
		GameRegistry.addRecipe(	new ItemStack(MSItems.mtsItemSignTool.me, 1),
								new Object[] {
										"OXO",
										"ISI",
										"OXO",
										Character.valueOf('X'),
										Item.ingotIron,
										Character.valueOf('I'),
										new ItemStack(Item.dyePowder, 1, 0),
										Character.valueOf('S'),
										Item.stick });
	}

	public static int configurationProperties() {
		configuration.load();
		MSBlocks.mtSignPost.id = // Integer.parseInt(configuration.get(
		// Configuration.CATEGORY_BLOCK,
		// "mtSignPost",
		Block.signPost.blockID;
		// ).value);
		MSBlocks.mtSignPost.name = "Multi-Textured Sign-Post";

		MSBlocks.mtSignWall.id = // Integer.parseInt(configuration.get(
		// Configuration.CATEGORY_BLOCK,
		// "mtSignWall",
		Block.signWall.blockID;
		// ).value);
		MSBlocks.mtSignWall.name = "Multi-Textured Wall-Sign";

		MSItems.mtsItemSignParts.setID(configuration.get(	Configuration.CATEGORY_ITEM,
															"mtSignParts",
															7000).getInt());
		MSItems.mtsItemSignParts.name = "Multi-Textured Sign Part";

		MSItems.mtsItemSigns.setID(
		// Integer.parseInt(configuration.get(
		// Configuration.CATEGORY_ITEM,
		// "mtSign",
		Item.sign.itemID);
		// .value));
		MSItems.mtsItemSigns.name = "Multi-Textured Sign";

		MSItems.mtsItemSignTool.setID(configuration.get(Configuration.CATEGORY_ITEM,
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
