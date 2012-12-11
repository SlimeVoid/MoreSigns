package mts.network.packets;

import mts.core.MTSInit;
import mts.tileentities.TileEntityMTSign;
import eurysmods.network.packets.core.PacketTileEntityMT;

public class PacketUpdateMTSign extends PacketTileEntityMT {
	private String[] signLines;

	public PacketUpdateMTSign() {
		super(MTSInit.MTS.getModChannel());
	}

	public PacketUpdateMTSign(TileEntityMTSign tileentitymtsign) {
		super(MTSInit.MTS.getModChannel(), tileentitymtsign);
		this.payload = tileentitymtsign.getPacketPayload();
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
