package net.slimevoid.moresigns.network.packets.executors;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.slimevoid.library.IPacketExecutor;
import net.slimevoid.library.network.PacketUpdate;
import net.slimevoid.moresigns.network.packets.PacketUpdateSign;
import net.slimevoid.moresigns.tileentities.TileEntitySign;

public class PacketUpdateExecutor implements IPacketExecutor {

    @Override
    public void execute(PacketUpdate packet, World world, EntityPlayer entityplayer) {
        if (packet instanceof PacketUpdateSign) {
            TileEntity tileentity = ((PacketUpdateSign) packet).getTileEntity(world);
            if (tileentity instanceof TileEntitySign) {
                ((TileEntitySign) tileentity).handleUpdatePacket(world,
                                                                 packet);
            }
        }
    }

}
