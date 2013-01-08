package eurymachus.mts.network.packets;

import slimevoid.lib.network.PacketIds;
import slimevoid.lib.network.PacketUpdate;
import net.minecraft.world.World;
import eurymachus.mts.core.MTSInit;

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
