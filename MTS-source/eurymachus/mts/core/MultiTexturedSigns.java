package eurymachus.mts.core;

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
import eurysmods.api.ICommonProxy;

@Mod(
		modid = "MultiTexturedSigns",
		name = "Multi-Textured Signs",
		dependencies = "after:EurysCore",
		version = "2.0.0.0")
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

	@Init
	public void MultiTexturedSignsInit(FMLInitializationEvent event) {
	}

	@PreInit
	public void MultiTexturedSignsPreInit(FMLPreInitializationEvent event) {

	}

	@PostInit
	public void MultiTexturedSignsPostInit(FMLPostInitializationEvent event) {
		MTSCore.initialize(proxy);
	}
}
