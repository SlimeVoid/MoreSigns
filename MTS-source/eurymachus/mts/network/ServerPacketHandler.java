package eurymachus.mts.network;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import eurymachus.mts.tileentities.TileEntityMTSign;
import eurysmods.api.IPacketHandling;
import eurysmods.network.packets.core.PacketTileEntity;
import eurysmods.network.packets.core.PacketUpdate;

public class ServerPacketHandler implements IPacketHandling {
	@Override
	public void handleTileEntityPacket(PacketTileEntity packet, EntityPlayer entityplayer, World world) {
		if (packet != null && packet.targetExists(world)) {
			TileEntity tileentity = packet.getTileEntity(world);
			if ((tileentity != null) && (tileentity instanceof TileEntityMTSign)) {
				TileEntityMTSign tileentitymtsign = (TileEntityMTSign) tileentity;
				tileentitymtsign.handleUpdatePacket(world, packet);
			}
		}
	}

	@Override
	public void handleGuiPacket(PacketUpdate packet, EntityPlayer player, World world) {
	}

	@Override
	public void handlePacket(PacketUpdate packet, EntityPlayer entityplayer, World world) {
		// TODO Auto-generated method stub

	}
}
