package net.slimevoid.moresigns.container;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.slimevoid.library.util.helpers.SlimevoidHelper;
import net.slimevoid.moresigns.tileentities.TileEntitySign;

public class ContainerSign extends Container {

    TileEntitySign sign;

    public ContainerSign(TileEntitySign tileentity) {
        super();
        this.sign = tileentity;
    }

    @Override
    public boolean canInteractWith(EntityPlayer entityplayer) {
        return SlimevoidHelper.isUseableByPlayer(this.sign.getWorldObj(),
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
