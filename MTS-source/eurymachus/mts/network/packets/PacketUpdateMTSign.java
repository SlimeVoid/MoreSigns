package eurymachus.mts.network.packets;

import slimevoidlib.network.PacketTileEntityMT;
import eurymachus.mts.core.lib.CommandLib;
import eurymachus.mts.core.lib.CoreLib;
import eurymachus.mts.tileentities.TileEntityMTSign;

public class PacketUpdateMTSign extends PacketTileEntityMT {

	public PacketUpdateMTSign() {
		super(CoreLib.MOD_CHANNEL);
	}

	public PacketUpdateMTSign(TileEntityMTSign tileentitymtsign) {
		super(CoreLib.MOD_CHANNEL, tileentitymtsign);
		this.payload = tileentitymtsign.getPacketPayload();
		this.setCommand(CommandLib.SIGN_UPDATE);
	}

	public void setMtSignText(String[] signText) {
		for (int i = 0; i < signText.length; i++) {
			this.payload.setStringPayload(i, signText[i]);
		}
	}

	public String[] getMtSignText() {
		String[] signText = new String[4];
		for (int i = 0; i < 4; i++) {
			signText[i] = this.payload.getStringPayload(i);
		}
		return signText;
	}
}
