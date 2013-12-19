package slimevoid.moresigns.client.network;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.src.ModLoader;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import slimevoid.moresigns.client.guis.GuiEditSign;
import slimevoid.moresigns.core.lib.CommandLib;
import slimevoid.moresigns.tileentities.TileEntitySign;
import slimevoidlib.IPacketHandling;
import slimevoidlib.network.PacketTileEntity;
import slimevoidlib.network.PacketUpdate;
import slimevoidlib.util.helpers.SlimevoidHelper;

public class ClientPacketHandler implements IPacketHandling {
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
	public void handleGuiPacket(PacketUpdate packet, EntityPlayer entityplayer, World world) {
		TileEntity tileentity = SlimevoidHelper.getBlockTileEntity(	world,
																	packet.xPosition,
																	packet.yPosition,
																	packet.zPosition);
		if (tileentity != null && tileentity instanceof TileEntitySign) {
			TileEntitySign tileentitymtsign = (TileEntitySign) tileentity;
			ModLoader.openGUI(	entityplayer,
								new GuiEditSign(tileentitymtsign));
		}
	}

	@Override
	public void handlePacket(PacketUpdate packet, EntityPlayer entityplayer, World world) {
	}
}
