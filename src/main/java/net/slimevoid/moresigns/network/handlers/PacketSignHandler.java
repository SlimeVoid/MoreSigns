package net.slimevoid.moresigns.network.handlers;

import net.slimevoid.library.network.PacketUpdate;
import net.slimevoid.library.network.handlers.SubPacketHandler;
import net.slimevoid.moresigns.network.packets.PacketUpdateSign;

public class PacketSignHandler extends SubPacketHandler {

    @Override
    protected PacketUpdate createNewPacket() {
        return new PacketUpdateSign();
    }

}
