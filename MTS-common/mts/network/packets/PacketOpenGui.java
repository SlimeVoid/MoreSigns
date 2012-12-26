package mts.network.packets;

import mts.core.MTSInit;
import net.minecraft.world.World;
import eurysmods.network.packets.core.PacketIds;
import eurysmods.network.packets.core.PacketUpdate;

public class PacketOpenGui extends PacketUpdate {
	public PacketOpenGui() {
		super(PacketIds.GUI);
		this.setChannel(MTSInit.MTS.getModChannel());
	}

	public PacketOpenGui(int x, int y, int z) {
		this();
		this.setPosition(x, y, z, 0);
	}

	@Override
	public boolean targetExists(World world) {
		return false;
	}
}
