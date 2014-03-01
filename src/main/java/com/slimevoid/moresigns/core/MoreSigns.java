package com.slimevoid.moresigns.core;

import com.slimevoid.library.ICommonProxy;
import com.slimevoid.moresigns.core.lib.CoreLib;
import com.slimevoid.moresigns.network.NetworkConnection;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;

@Mod(
		modid = CoreLib.MOD_ID,
		name = CoreLib.MOD_NAME,
		dependencies = CoreLib.MOD_DEPENDENCIES,
		version = CoreLib.MOD_VERSION)
@NetworkMod(
		clientSideRequired = true,
		serverSideRequired = false,
		channels = { CoreLib.MOD_CHANNEL },
		packetHandler = NetworkConnection.class,
		connectionHandler = NetworkConnection.class)
public class MoreSigns {
	@SidedProxy(
			clientSide = CoreLib.CLIENT_PROXY,
			serverSide = CoreLib.COMMON_PROXY)
	public static ICommonProxy	proxy;
	@Instance(CoreLib.MOD_ID)
	public static MoreSigns		instance;

	@EventHandler
	public void MoreSignsPreInit(FMLPreInitializationEvent event) {
		proxy.preInit();
	}

	@EventHandler
	public void MoreSignsInit(FMLInitializationEvent event) {
	}

	@EventHandler
	public void MoreSignsPostInit(FMLPostInitializationEvent event) {
		MSCore.initialize();
	}
}
