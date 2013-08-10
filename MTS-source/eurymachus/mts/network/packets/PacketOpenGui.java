package eurymachus.mts.network.packets;

import net.minecraft.world.World;
import slimevoidlib.network.PacketIds;
import slimevoidlib.network.PacketUpdate;
import eurymachus.mts.core.lib.CommandLib;
import eurymachus.mts.core.lib.CoreLib;

public class PacketOpenGui extends PacketUpdate {
	public PacketOpenGui() {
		super(PacketIds.GUI);
		this.setChannel(CoreLib.MOD_CHANNEL);
	}

	public PacketOpenGui(int x, int y, int z) {
		this();
		this.setPosition(x, y, z, 0);
		this.setCommand(CommandLib.SIGN_GUI);
	}

	@Override
	public boolean targetExists(World world) {
		return false;
	}
}
