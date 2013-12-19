package slimevoid.moresigns.core;

import java.io.File;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.common.Configuration;
import slimevoid.moresigns.core.lib.CoreLib;
import slimevoid.moresigns.tileentities.TileEntitySign;
import slimevoidlib.core.SlimevoidCore;
import slimevoidlib.core.SlimevoidLib;

public class MSInit {
	public static boolean	initialized	= false;

	public static void initialize() {
		if (initialized) return;
		initialized = true;
		MSCore.configFile = new File(SlimevoidLib.proxy.getMinecraftDir(), "config/MoreSigns.cfg");
		MSCore.configuration = new Configuration(MSCore.configFile);
		load();
	}

	public static void load() {
		SlimevoidCore.console(	CoreLib.MOD_ID,
								"Registering items...");
		MSCore.addItems();
		SlimevoidCore.console(	CoreLib.MOD_ID,
								"Registering blocks...");
		MSCore.registerBlocks();
		MoreSigns.proxy.registerRenderInformation();
		MoreSigns.proxy.registerTileEntitySpecialRenderer(TileEntitySign.class);
		SlimevoidCore.console(	CoreLib.MOD_ID,
								"Naming items...");
		MSCore.addItemNames();
		SlimevoidCore.console(	CoreLib.MOD_ID,
								"Registering recipes...");
		MSCore.addRecipes();
	}

	public static int getDamageValue(IBlockAccess blockAccess, int x, int y, int z) {
		TileEntity tileentity = blockAccess.getBlockTileEntity(	x,
																y,
																z);
		if (tileentity != null && tileentity instanceof TileEntitySign) {
			TileEntitySign tileentitymtsign = (TileEntitySign) tileentity;
			return tileentitymtsign.getTexture();
		}
		return 0;
	}
}
