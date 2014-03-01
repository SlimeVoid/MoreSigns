package com.slimevoid.moresigns.container;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import com.slimevoid.library.util.helpers.SlimevoidHelper;
import com.slimevoid.moresigns.tileentities.TileEntitySign;

public class ContainerSign extends Container {

	TileEntitySign	sign;

	public ContainerSign(TileEntitySign tileentity) {
		super();
		this.sign = tileentity;
	}

	@Override
	public boolean canInteractWith(EntityPlayer entityplayer) {
		return SlimevoidHelper.isUseableByPlayer(	this.sign.worldObj,
													entityplayer,
													this.sign.xCoord,
													this.sign.yCoord,
													this.sign.zCoord,
													0.5D,
													0.5D,
													0.5D,
													64D);
	}
}
