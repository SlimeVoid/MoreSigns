package net.slimevoid.moresigns.items;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.slimevoid.moresigns.core.MoreSigns;
import net.slimevoid.moresigns.core.lib.GuiLib;
import net.slimevoid.moresigns.core.lib.IconLib;
import net.slimevoid.moresigns.tileentities.TileEntitySign;

public class ItemSignTool extends Item {

    @Override
    public void registerIcons(IIconRegister iconRegister) {
        this.itemIcon = iconRegister.registerIcon(IconLib.ICON_SIGN_TOOL);
    }

    public ItemSignTool(int i) {
        super();
        this.setFull3D();
        this.setMaxStackSize(1);
        this.setMaxDamage(100);
        this.setCreativeTab(CreativeTabs.tabTools);
    }

    @Override
    public boolean onItemUse(ItemStack itemstack, EntityPlayer entityplayer, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ) {
        if (!entityplayer.canPlayerEdit(x,
                                        y,
                                        z,
                                        side,
                                        itemstack)) {
            return false;
        } else {
            if (world.isRemote) {
                TileEntity tileentity = world.getTileEntity(x,
                                                            y,
                                                            z);
                if (tileentity != null && tileentity instanceof TileEntitySign) {
                    entityplayer.openGui(MoreSigns.instance,
                                         GuiLib.GUI_SIGNS,
                                         world,
                                         x,
                                         y,
                                         z);
                    itemstack.damageItem(5,
                                         entityplayer);
                }
            }
        }
        return true;
    }
}
