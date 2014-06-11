package net.slimevoid.moresigns.core.lib;

import net.slimevoid.library.network.PacketIds;
import net.slimevoid.library.network.handlers.PacketPipeline;
import net.slimevoid.moresigns.network.handlers.PacketSignHandler;
import net.slimevoid.moresigns.network.packets.executors.PacketUpdateExecutor;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class PacketLib {

    public static PacketPipeline handler = new PacketPipeline();

    @SideOnly(Side.CLIENT)
    public static void registerClientPacketHandlers() {
        handler.getPacketHandler(PacketIds.TILE).registerClientExecutor(CommandLib.SIGN_UPDATE,
                                                                        new PacketUpdateExecutor());
    }

    public static void registerPacketHandlers() {
        PacketSignHandler signHandler = new PacketSignHandler();

        signHandler.registerServerExecutor(CommandLib.SIGN_UPDATE,
                                           new PacketUpdateExecutor());

        handler.registerPacketHandler(PacketIds.TILE,
                                      signHandler);
    }
}
