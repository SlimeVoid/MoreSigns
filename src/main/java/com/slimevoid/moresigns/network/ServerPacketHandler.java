package com.slimevoid.moresigns.network;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import com.slimevoid.library.IPacketHandling;
import com.slimevoid.library.network.PacketTileEntity;
import com.slimevoid.library.network.PacketUpdate;
import com.slimevoid.moresigns.core.lib.CommandLib;
import com.slimevoid.moresigns.tileentities.TileEntitySign;

public class ServerPacketHandler implements IPacketHandling {
	@Override
	public void handleTileEntityPacket(PacketTileEntity packet, EntityPlayer entityplayer, World world) {
		if (packet != null && packet.targetExists(world)) {
			if (packet.getCommand().equals(CommandLib.SIGN_UPDATE)) {
				TileEntity tileentity = packet.getTileEntity(world);
				if ((tileentity != null)
					&& (tileentity instanceof TileEntitySign)) {
					TileEntitySign tileentitymtsign = (TileEntitySign) tileentity;
					tileentitymtsign.handleUpdatePacket(world,
														packet);
				}
			}
		}
	}

	@Override
	public void handleGuiPacket(PacketUpdate packet, EntityPlayer player, World world) {
	}

	@Override
	public void handlePacket(PacketUpdate packet, EntityPlayer entityplayer, World world) {
	}
}
