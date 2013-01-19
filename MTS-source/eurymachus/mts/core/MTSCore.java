package eurymachus.mts.core;

import java.io.File;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.src.ModLoader;
import net.minecraftforge.common.Configuration;
import slimevoid.lib.ICommonProxy;
import slimevoid.lib.core.BlockRemover;
import slimevoid.lib.core.ItemRemover;
import slimevoid.lib.core.RecipeRemover;
import cpw.mods.fml.common.registry.GameRegistry;
import eurymachus.mts.blocks.BlockMTSign;
import eurymachus.mts.items.ItemMTSignParts;
import eurymachus.mts.items.ItemMTSignTool;
import eurymachus.mts.items.ItemMTSigns;
import eurymachus.mts.tileentities.TileEntityMTSign;

public class MTSCore {
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
		RecipeRemover.registerItemRecipeToRemove(Item.sign);
		RecipeRemover.removeCrafting();
		BlockRemover.removeVanillaBlock(Block.signPost);
		BlockRemover.removeVanillaBlock(Block.signWall);
		ItemRemover.removeVanillaItem(Item.sign);
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
				MTSItems.mtsItemSigns.getID())).setItemName("mtsItemSigns");
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
		setDroppedItems();
	}

	private static void setDroppedItems() {
		MTSItemSigns.woodenSign.droppedItem = MTSItemSigns.woodenSign.me.splitStack(1);
		MTSItemSigns.ironCladSign.droppedItem = MTSItemParts.ironCladPlating.me.splitStack(1);
		MTSItemSigns.goldPlatedSign.droppedItem = MTSItemParts.goldPlating.me.splitStack(1);
		MTSItemSigns.diamondLatheredSign.droppedItem = MTSItemParts.diamondPlating.me.splitStack(1);
	}

	public static void registerBlocks() {
		for (MTSBlocks block : MTSBlocks.values()) {
			if (block != null && block.me != null) {
				System.out.println("Registering Block[" + block.name + "]");
				GameRegistry.registerBlock(block.me, block.name);
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
				MTSItemSigns.woodenSign.me.splitStack(1),
				new Object[] {
						"XXX",
						"XXX",
						" Y ",
						Character.valueOf('X'),
						Block.planks,
						Character.valueOf('Y'),
						Item.stick });
		
		GameRegistry.addRecipe(
				MTSItemSigns.ironCladSign.me.splitStack(1),
				new Object[] {
						"X",
						"Y",
						"X",
						Character.valueOf('X'),
						Item.ingotIron,
						Character.valueOf('Y'),
						MTSItemSigns.woodenSign.me });

		GameRegistry.addRecipe(
				MTSItemSigns.goldPlatedSign.me.splitStack(1),
				new Object[] {
						"X",
						"Y",
						"X",
						Character.valueOf('X'),
						Item.ingotGold,
						Character.valueOf('Y'),
						MTSItemSigns.woodenSign.me });

		GameRegistry.addRecipe(
				MTSItemSigns.diamondLatheredSign.me.splitStack(1),
				new Object[] {
						"X",
						"Y",
						"X",
						Character.valueOf('X'),
						Item.diamond,
						Character.valueOf('Y'),
						MTSItemSigns.woodenSign.me });

		FurnaceRecipes.smelting().addSmelting(
				MTSItems.mtsItemSignParts.getID(),
				MTSItemParts.ironCladPlating.stackID,
				(new ItemStack(Item.ingotIron, 2)),
				0);
		FurnaceRecipes.smelting().addSmelting(
				MTSItems.mtsItemSignParts.getID(),
				MTSItemParts.goldPlating.stackID,
				(new ItemStack(Item.ingotGold, 2)),
				2);
		FurnaceRecipes.smelting().addSmelting(
				MTSItems.mtsItemSignParts.getID(),
				MTSItemParts.diamondPlating.stackID,
				(new ItemStack(Item.diamond, 2)),
				4);

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
		MTSBlocks.mtSignPost.id = //Integer.parseInt(configuration.get(
				//Configuration.CATEGORY_BLOCK,
				//"mtSignPost",
				Block.signPost.blockID;
				//).value);
		MTSBlocks.mtSignPost.name = "Multi-Textured Sign-Post";

		MTSBlocks.mtSignWall.id = //Integer.parseInt(configuration.get(
				//Configuration.CATEGORY_BLOCK,
				//"mtSignWall",
				Block.signWall.blockID;
				//).value);
		MTSBlocks.mtSignWall.name = "Multi-Textured Wall-Sign";

		MTSItems.mtsItemSignParts.setID(Integer.parseInt(configuration.get(
				Configuration.CATEGORY_ITEM,
				"mtSignParts",
				7000).value));
		MTSItems.mtsItemSignParts.name = "Multi-Textured Sign Part";

		MTSItems.mtsItemSigns.setID(
				//Integer.parseInt(configuration.get(
				//Configuration.CATEGORY_ITEM,
				//"mtSign",
				Item.sign.itemID);
				//.value));
		MTSItems.mtsItemSigns.name = "Multi-Textured Sign";

		MTSItems.mtsItemSignTool.setID(Integer.parseInt(configuration.get(
				Configuration.CATEGORY_ITEM,
				"mtSignTool",
				7001).value));
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
		MTSItemSigns.woodenSign.name = "Wooden Sign";
		MTSItemSigns.woodenSign.stackID = 0;
		MTSItemSigns.ironCladSign.name = "Iron-Clad Sign";
		MTSItemSigns.ironCladSign.stackID = 1;
		MTSItemSigns.goldPlatedSign.name = "Gold-Plated Sign";
		MTSItemSigns.goldPlatedSign.stackID = 2;
		MTSItemSigns.diamondLatheredSign.name = "Diamond-Lathered Sign";
		MTSItemSigns.diamondLatheredSign.stackID = 3;

		configuration.save();
		return MTSBlocks.mtSignPost.id;
	}
}
