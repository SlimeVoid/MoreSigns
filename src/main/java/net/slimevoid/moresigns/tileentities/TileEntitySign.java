package net.slimevoid.moresigns.tileentities;

import java.util.ArrayList;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.slimevoid.library.network.PacketPayload;
import net.slimevoid.library.network.PacketUpdate;
import net.slimevoid.library.tileentity.TileEntityBase;
import net.slimevoid.moresigns.core.lib.BlockLib;
import net.slimevoid.moresigns.core.lib.ItemLib;
import net.slimevoid.moresigns.network.packets.PacketUpdateSign;

public class TileEntitySign extends TileEntityBase {
    private int     metadata;
    public String   signText[] = { "", "", "", "" };
    public int      mtsLineBeingEdited;
    private boolean mtsIsEditable;

    public boolean getIsEditAble() {
        return mtsIsEditable;
    }

    public String[] getMtSignText() {
        return this.signText;
    }

    public void setMtSignText(String[] newSignText) {
        for (int i = 0; i < 4; i++) {
            this.signText[i] = newSignText[i];
        }
    }

    public TileEntitySign() {
        mtsLineBeingEdited = -1;
        mtsIsEditable = true;
    }

    @Override
    public void writeToNBT(NBTTagCompound nbttagcompound) {
        super.writeToNBT(nbttagcompound);
        nbttagcompound.setString(BlockLib.NBT_SIGN_TEXT + "1",
                                 signText[0]);
        nbttagcompound.setString(BlockLib.NBT_SIGN_TEXT + "2",
                                 signText[1]);
        nbttagcompound.setString(BlockLib.NBT_SIGN_TEXT + "3",
                                 signText[2]);
        nbttagcompound.setString(BlockLib.NBT_SIGN_TEXT + "4",
                                 signText[3]);
        nbttagcompound.setInteger(BlockLib.NBT_SIGN_METADATA,
                                  this.metadata);
    }

    @Override
    public void readFromNBT(NBTTagCompound nbttagcompound) {
        mtsIsEditable = true;
        super.readFromNBT(nbttagcompound);
        for (int i = 0; i < 4; i++) {
            signText[i] = nbttagcompound.getString((new StringBuilder()).append(BlockLib.NBT_SIGN_TEXT).append(i + 1).toString());
            if (signText[i].length() > 15) {
                signText[i] = signText[i].substring(0,
                                                    15);
            }
        }
        this.metadata = nbttagcompound.getInteger(BlockLib.NBT_SIGN_METADATA);
    }

    public PacketPayload getPacketPayload() {
        PacketPayload p = new PacketPayload(1, 0, 4, 0);
        for (int i = 0; i < this.signText.length; i++) {
            p.setStringPayload(i,
                               this.signText[i]);
        }
        p.setIntPayload(0,
                        metadata);
        return p;
    }

    public void handleUpdatePacket(World world, PacketUpdate packet) {
        this.setMtSignText(((PacketUpdateSign) packet).getMtSignText());
        this.setTexture(((PacketUpdateSign) packet).getTexture());
        this.onInventoryChanged();
        world.markBlockForUpdate(this.xCoord,
                                 this.yCoord,
                                 this.zCoord);
    }

    @Override
    public int getExtendedBlockID() {
        return 0;
    }

    public int getTexture() {
        return this.metadata;
    }

    public void setTexture(int texture) {
        this.metadata = texture;
    }

    @Override
    public int getSizeInventory() {
        return 0;
    }

    @Override
    public ItemStack getStackInSlot(int var1) {
        return null;
    }

    @Override
    public ItemStack decrStackSize(int var1, int var2) {
        return null;
    }

    @Override
    public ItemStack getStackInSlotOnClosing(int var1) {
        return null;
    }

    @Override
    public void setInventorySlotContents(int var1, ItemStack var2) {
    }

    @Override
    public int getInventoryStackLimit() {
        return 0;
    }

    @Override
    public boolean isUseableByPlayer(EntityPlayer var1) {
        return false;
    }

    @Override
    public boolean isItemValidForSlot(int var1, ItemStack var2) {
        return false;
    }

    @Override
    public String getInvName() {
        return ItemLib.SIGN_PREFIX;
    }

    @Override
    protected void addHarvestContents(ArrayList<ItemStack> arg0) {
    }
}