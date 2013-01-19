package eurymachus.mts.network.packets;

import slimevoid.lib.network.PacketTileEntityMT;
import eurymachus.mts.core.MTSInit;
import eurymachus.mts.tileentities.TileEntityMTSign;

public class PacketUpdateMTSign extends PacketTileEntityMT {

	public PacketUpdateMTSign() {
		super(MTSInit.MTS.getModChannel());
	}

	public PacketUpdateMTSign(TileEntityMTSign tileentitymtsign) {
		super(MTSInit.MTS.getModChannel(), tileentitymtsign);
		this.payload = tileentitymtsign.getPacketPayload();
		this.setCommand("MTSign");
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
