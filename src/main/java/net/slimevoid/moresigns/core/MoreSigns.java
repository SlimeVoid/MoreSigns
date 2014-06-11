package net.slimevoid.moresigns.core;

import net.slimevoid.library.ICommonProxy;
import net.slimevoid.library.util.helpers.PacketHelper;
import net.slimevoid.moresigns.core.lib.CoreLib;
import net.slimevoid.moresigns.core.lib.PacketLib;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

@Mod(
        modid = CoreLib.MOD_ID,
        name = CoreLib.MOD_NAME,
        dependencies = CoreLib.MOD_DEPENDENCIES,
        version = CoreLib.MOD_VERSION)
public class MoreSigns {
    @SidedProxy(
            clientSide = CoreLib.CLIENT_PROXY,
            serverSide = CoreLib.COMMON_PROXY)
    public static ICommonProxy proxy;
    @Instance(CoreLib.MOD_ID)
    public static MoreSigns    instance;

    @EventHandler
    public void MoreSignsPreInit(FMLPreInitializationEvent event) {
        proxy.preInit();
    }

    @EventHandler
    public void MoreSignsInit(FMLInitializationEvent event) {
        proxy.init();
        PacketHelper.registerHandler(CoreLib.MOD_CHANNEL,
                                     PacketLib.handler);
    }

    @EventHandler
    public void MoreSignsPostInit(FMLPostInitializationEvent event) {
        proxy.postInit();
    }
}
