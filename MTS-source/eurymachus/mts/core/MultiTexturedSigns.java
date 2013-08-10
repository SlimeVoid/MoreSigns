package eurymachus.mts.core;

import slimevoidlib.ICommonProxy;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Init;
import cpw.mods.fml.common.Mod.PostInit;
import cpw.mods.fml.common.Mod.PreInit;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import eurymachus.mts.core.lib.CoreLib;
import eurymachus.mts.network.MTSConnection;

@Mod(
		modid = CoreLib.MOD_ID,
		name = CoreLib.MOD_NAME,
		dependencies = CoreLib.MOD_DEPENDENCIES,
		version = CoreLib.MOD_VERSION)
@NetworkMod(
		clientSideRequired = true,
		serverSideRequired = false,
		channels = { CoreLib.MOD_CHANNEL },
		packetHandler = MTSConnection.class,
		connectionHandler = MTSConnection.class)
public class MultiTexturedSigns {
	@SidedProxy(
			clientSide = CoreLib.CLIENT_PROXY,
			serverSide = CoreLib.COMMON_PROXY)
	public static ICommonProxy proxy;

	@EventHandler
	public void MultiTexturedSignsPreInit(FMLPreInitializationEvent event) {
	}

	@EventHandler
	public void MultiTexturedSignsInit(FMLInitializationEvent event) {
	}

	@EventHandler
	public void MultiTexturedSignsPostInit(FMLPostInitializationEvent event) {
		MTSCore.initialize();
	}
}
