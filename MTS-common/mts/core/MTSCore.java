package mts.core;

import java.io.File;

import mts.blocks.BlockMTSign;
import mts.items.ItemMTSignParts;
import mts.items.ItemMTSignTool;
import mts.items.ItemMTSigns;
import mts.tileentities.TileEntityMTSign;
import net.minecraft.src.FurnaceRecipes;
import net.minecraft.src.Item;
import net.minecraft.src.ItemStack;
import net.minecraft.src.ModLoader;
import net.minecraftforge.common.Configuration;
import cpw.mods.fml.common.registry.GameRegistry;
import eurysmods.api.ICommonProxy;

public class MTSCore {
	public static String version = "v3.1";
	public static File configFile;
	public static Configuration configuration;
	public static boolean initialized = false;

	public static void initialize(ICommonProxy proxy) {
		if (initialized)
			return;
		initialized = true;
		MTSInit.initialize(proxy);
	}

	public static void addItems() {
		MTSBlocks.mtSignPost.id = configurationProperties();
		MTSBlocks.mtSignPost.me = (new BlockMTSign(
				MTSBlocks.mtSignPost.id,
				TileEntityMTSign.class,
				true,
				1F,
				2F,
				true,
				true)).setBlockName("mtSignPost");
		MTSBlocks.mtSignWall.me = (new BlockMTSign(
				MTSBlocks.mtSignWall.id,
				TileEntityMTSign.class,
				false,
				1F,
				2F,
				true,
				true)).setBlockName("mtSignWall");
		GameRegistry.registerTileEntity(TileEntityMTSign.class, "mtSign");
		MTSItems.mtsItemSignParts.me = (new ItemMTSignParts(
				MTSItems.mtsItemSignParts.offsetID()))
				.setItemName("mtsItemSignParts");
		MTSItems.mtsItemSigns.me = (new ItemMTSigns(
				MTSItems.mtsItemSigns.offsetID())).setItemName("mtsItemSigns");
		MTSItems.mtsItemSignTool.me = (new ItemMTSignTool(
				MTSItems.mtsItemSignTool.offsetID()))
				.setItemName("mtsItemSignTool");
		for (MTSItemParts part : MTSItemParts.values()) {
			part.me = new ItemStack(
					MTSItems.mtsItemSignParts.me,
					1,
					part.stackID);
		}
		for (MTSItemSigns sign : MTSItemSigns.values()) {
			sign.me = new ItemStack(MTSItems.mtsItemSigns.me, 1, sign.stackID);
		}
	}

	public static void registerBlocks() {
		for (MTSBlocks block : MTSBlocks.values()) {
			if (block != null && block.me != null) {
				GameRegistry.registerBlock(block.me);
				if (block.name != null) {
					ModLoader.addName(block.me, block.name);
				}
			}
		}
	}

	public static void addItemNames() {
		for (MTSItems item : MTSItems.values()) {
			if (item != null && item.me != null && item.name != null) {
				ModLoader.addName(item.me, item.name);
			}
		}
		for (MTSItemParts part : MTSItemParts.values()) {
			if (part != null && part.me != null && part.name != null) {
				ModLoader.addName(part.me, part.name);
			}
		}
		for (MTSItemSigns sign : MTSItemSigns.values()) {
			if (sign != null && sign.me != null && sign.name != null) {
				ModLoader.addName(sign.me, sign.name);
			}
		}
	}

	public static void addRecipes() {

		// Plating
		GameRegistry.addRecipe(
				MTSItemParts.ironCladPlating.me.splitStack(2),
				new Object[] {
						"XX",
						"XX",
						Character.valueOf('X'),
						Item.ingotIron });

		GameRegistry.addRecipe(
				MTSItemParts.goldPlating.me.splitStack(2),
				new Object[] {
						"XX",
						"XX",
						Character.valueOf('X'),
						Item.ingotGold });

		GameRegistry
				.addRecipe(
						MTSItemParts.diamondPlating.me.splitStack(2),
						new Object[] {
								"XX",
								"XX",
								Character.valueOf('X'),
								Item.diamond });

		// Poles
		GameRegistry.addRecipe(
				MTSItemParts.ironCladPole.me.splitStack(8),
				new Object[] {
						"X",
						"Y",
						Character.valueOf('X'),
						Item.ingotIron,
						Character.valueOf('Y'),
						Item.stick });

		GameRegistry.addRecipe(
				MTSItemParts.goldenPole.me.splitStack(8),
				new Object[] {
						"X",
						"Y",
						Character.valueOf('X'),
						Item.ingotGold,
						Character.valueOf('Y'),
						Item.stick });

		GameRegistry.addRecipe(
				MTSItemParts.diamondPole.me.splitStack(8),
				new Object[] {
						"X",
						"Y",
						Character.valueOf('X'),
						Item.diamond,
						Character.valueOf('Y'),
						Item.stick });

		// Signs
		GameRegistry.addRecipe(
				MTSItemSigns.ironCladSign.me.splitStack(1),
				new Object[] {
						"X",
						"Y",
						Character.valueOf('X'),
						MTSItemParts.ironCladPlating.me,
						Character.valueOf('Y'),
						MTSItemParts.ironCladPole.me });
		GameRegistry.addRecipe(
				MTSItemSigns.goldPlatedSign.me.splitStack(1),
				new Object[] {
						"X",
						"Y",
						Character.valueOf('X'),
						MTSItemParts.goldPlating.me,
						Character.valueOf('Y'),
						MTSItemParts.goldenPole.me });
		GameRegistry.addRecipe(
				MTSItemSigns.diamondLatheredSign.me.splitStack(1),
				new Object[] {
						"X",
						"Y",
						Character.valueOf('X'),
						MTSItemParts.diamondPlating.me,
						Character.valueOf('Y'),
						MTSItemParts.diamondPole.me });

		GameRegistry.addRecipe(
				MTSItemSigns.ironCladSign.me.splitStack(1),
				new Object[] {
						"X",
						"Y",
						"X",
						Character.valueOf('X'),
						Item.ingotIron,
						Character.valueOf('Y'),
						Item.sign });

		GameRegistry.addRecipe(
				MTSItemSigns.goldPlatedSign.me.splitStack(1),
				new Object[] {
						"X",
						"Y",
						"X",
						Character.valueOf('X'),
						Item.ingotGold,
						Character.valueOf('Y'),
						Item.sign });

		GameRegistry.addRecipe(
				MTSItemSigns.diamondLatheredSign.me.splitStack(1),
				new Object[] {
						"X",
						"Y",
						"X",
						Character.valueOf('X'),
						Item.diamond,
						Character.valueOf('Y'),
						Item.sign });

		FurnaceRecipes.smelting().addSmelting(
				MTSItems.mtsItemSignParts.getID(),
				0,
				(new ItemStack(Item.ingotIron, 2)));
		FurnaceRecipes.smelting().addSmelting(
				MTSItems.mtsItemSignParts.getID(),
				2,
				(new ItemStack(Item.ingotGold, 2)));
		FurnaceRecipes.smelting().addSmelting(
				MTSItems.mtsItemSignParts.getID(),
				4,
				(new ItemStack(Item.diamond, 2)));

		// Wand
		GameRegistry.addRecipe(
				new ItemStack(MTSItems.mtsItemSignTool.me, 1),
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
		MTSBlocks.mtSignPost.id = Integer.parseInt(configuration
				.get(Configuration.CATEGORY_BLOCK, "mtSignPost", 213).value);
		MTSBlocks.mtSignPost.name = "Multi-Textured Sign-Post";

		MTSBlocks.mtSignWall.id = Integer.parseInt(configuration
				.get(Configuration.CATEGORY_BLOCK, "mtSignWall", 212).value);
		MTSBlocks.mtSignWall.name = "Multi-Textured Wall-Sign";

		MTSItems.mtsItemSignParts.setID(Integer.parseInt(configuration
				.get(
						Configuration.CATEGORY_ITEM,
						"mtSignParts",
						7000).value));
		MTSItems.mtsItemSignParts.name = "Multi-Textured Sign Part";

		MTSItems.mtsItemSigns.setID(Integer.parseInt(configuration
				.get(
						Configuration.CATEGORY_ITEM,
						"mtSign",
						7001).value));
		MTSItems.mtsItemSigns.name = "Multi-Textured Sign";

		MTSItems.mtsItemSignTool.setID(Integer.parseInt(configuration
				.get(
						Configuration.CATEGORY_ITEM,
						"mtSignTool",
						7002).value));
		MTSItems.mtsItemSignTool.name = "Multi-Textured Sign Wand";

		MTSItemParts.ironCladPlating.name = "Iron-Clad Plating";
		MTSItemParts.ironCladPlating.stackID = 0;
		MTSItemParts.ironCladPole.name = "Iron-Clad Pole";
		MTSItemParts.ironCladPole.stackID = 1;
		MTSItemParts.goldPlating.name = "Gold Plating";
		MTSItemParts.goldPlating.stackID = 2;
		MTSItemParts.goldenPole.name = "Golden Pole";
		MTSItemParts.goldenPole.stackID = 3;
		MTSItemParts.diamondPlating.name = "Diamond-Studded Plating";
		MTSItemParts.diamondPlating.stackID = 4;
		MTSItemParts.diamondPole.name = "Diamond-Encrusted Pole";
		MTSItemParts.diamondPole.stackID = 5;
		MTSItemSigns.ironCladSign.name = "Iron-Clad Sign";
		MTSItemSigns.ironCladSign.stackID = 0;
		MTSItemSigns.goldPlatedSign.name = "Gold-Plated Sign";
		MTSItemSigns.goldPlatedSign.stackID = 1;
		MTSItemSigns.diamondLatheredSign.name = "Diamond-Lathered Sign";
		MTSItemSigns.diamondLatheredSign.stackID = 2;

		configuration.save();
		return MTSBlocks.mtSignPost.id;
	}
}
