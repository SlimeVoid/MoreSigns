package eurymachus.mts.core;

import slimevoid.lib.ICommonProxy;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Init;
import cpw.mods.fml.common.Mod.PostInit;
import cpw.mods.fml.common.Mod.PreInit;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import eurymachus.mts.network.MTSConnection;

@Mod(
		modid = "MultiTexturedSigns",
		name = "Multi-Textured Signs",
		dependencies = "after:SlimevoidLib",
		version = "2.0.0.1")
@NetworkMod(
		clientSideRequired = true,
		serverSideRequired = false,
		channels = { "MTS" },
		packetHandler = MTSConnection.class,
		connectionHandler = MTSConnection.class)
public class MultiTexturedSigns {
	@SidedProxy(
			clientSide = "eurymachus.mts.client.proxy.ClientProxy",
			serverSide = "eurymachus.mts.proxy.CommonProxy")
	public static ICommonProxy proxy;

	@PreInit
	public void MultiTexturedSignsPreInit(FMLPreInitializationEvent event) {
	}

	@Init
	public void MultiTexturedSignsInit(FMLInitializationEvent event) {
	}

	@PostInit
	public void MultiTexturedSignsPostInit(FMLPostInitializationEvent event) {
		MTSCore.initialize(proxy);
	}
}
