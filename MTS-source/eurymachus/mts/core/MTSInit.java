package eurymachus.mts.core;

import java.io.File;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.common.Configuration;
import slimevoidlib.ICommonProxy;
import slimevoidlib.core.SlimevoidCore;
import slimevoidlib.core.SlimevoidLib;
import eurymachus.mts.core.lib.CoreLib;
import eurymachus.mts.tileentities.TileEntityMTSign;

public class MTSInit {
	public static boolean initialized = false;

	public static void initialize() {
		if (initialized)
			return;
		initialized = true;
		MTSCore.configFile = new File(
				SlimevoidLib.proxy.getMinecraftDir(),
					"config/MoreSigns.cfg");
		MTSCore.configuration = new Configuration(MTSCore.configFile);
		load();
	}

	public static void load() {
		SlimevoidCore.console(CoreLib.MOD_ID, "Registering items...");
		MTSCore.addItems();
		SlimevoidCore.console(CoreLib.MOD_ID, "Registering blocks...");
		MTSCore.registerBlocks();
		MultiTexturedSigns.proxy.registerRenderInformation();
		MultiTexturedSigns.proxy.registerTileEntitySpecialRenderer(TileEntityMTSign.class);
		SlimevoidCore.console(CoreLib.MOD_ID, "Naming items...");
		MTSCore.addItemNames();
		SlimevoidCore.console(CoreLib.MOD_ID, "Registering recipes...");
		MTSCore.addRecipes();
	}

	public static int getDamageValue(IBlockAccess blockAccess, int x, int y, int z) {
		TileEntity tileentity = blockAccess.getBlockTileEntity(x, y, z);
		if (tileentity != null && tileentity instanceof TileEntityMTSign) {
			TileEntityMTSign tileentitymtsign = (TileEntityMTSign) tileentity;
			return tileentitymtsign.getTextureValue();
		}
		return 0;
	}
}
