package net.slimevoid.moresigns.items;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import net.slimevoid.moresigns.core.MSBlocks;
import net.slimevoid.moresigns.core.MoreSigns;
import net.slimevoid.moresigns.core.lib.GuiLib;
import net.slimevoid.moresigns.core.lib.IconLib;
import net.slimevoid.moresigns.core.lib.ItemLib;
import net.slimevoid.moresigns.tileentities.TileEntitySign;
import cpw.mods.fml.common.FMLCommonHandler;

public class ItemSigns extends Item {

    protected IIcon[] iconList;

    @Override
    public void registerIcons(IIconRegister iconRegister) {
        iconList = new IIcon[4];
        iconList[0] = iconRegister.registerIcon(IconLib.ICON_SIGN_WOOD);
        iconList[1] = iconRegister.registerIcon(IconLib.ICON_SIGN_IRON);
        iconList[2] = iconRegister.registerIcon(IconLib.ICON_SIGN_GOLD);
        iconList[3] = iconRegister.registerIcon(IconLib.ICON_SIGN_DIAMOND);
    }

    public ItemSigns(int i) {
        super();
        this.setHasSubtypes(true);
        this.setMaxStackSize(16);
        this.setMaxDamage(0);
        this.setNoRepair();
        this.setCreativeTab(CreativeTabs.tabDecorations);
    }

    private String[] signNames = new String[] {
            ItemLib.SIGN_WOODEN,
            ItemLib.SIGN_IRON,
            ItemLib.SIGN_GOLD,
            ItemLib.SIGN_DIAMOND };

    @Override
    public String getUnlocalizedName(ItemStack itemstack) {
        return (new StringBuilder()).append(super.getUnlocalizedName()).append(".").append(signNames[itemstack.getItemDamage()]).toString();
    }

    public int filterData(int i) {
        return i;
    }

    @Override
    public IIcon getIconFromDamage(int i) {
        try {
            return iconList[i];
        } catch (Exception e) {
            FMLCommonHandler.instance().getFMLLogger().fatal("No Such Sign");
            return this.itemIcon;
        }
    }

    @Override
    public boolean onItemUse(ItemStack itemstack, EntityPlayer entityplayer, World world, int i, int j, int k, int l, float a, float b, float c) {
        Block signpost = MSBlocks.mtSignPost.me;
        Block wallsign = MSBlocks.mtSignWall.me;
        if (l == 0) {
            return false;
        }
        if (!world.isSideSolid(i,
                               j,
                               k,
                               ForgeDirection.getOrientation(l))) {
            return false;
        }
        if (l == 1) {
            j++;
        }
        if (l == 2) {
            k--;
        }
        if (l == 3) {
            k++;
        }
        if (l == 4) {
            i--;
        }
        if (l == 5) {
            i++;
        }
        if (!entityplayer.canPlayerEdit(i,
                                        j,
                                        k,
                                        l,
                                        itemstack)) {
            return false;
        }
        if (!signpost.canPlaceBlockAt(world,
                                      i,
                                      j,
                                      k)) {
            return false;
        }
        if (l == 1) {
            int i1 = MathHelper.floor_double((((entityplayer.rotationYaw + 180F) * 16F) / 360F) + 0.5D) & 0xf;
            world.setBlock(i,
                           j,
                           k,
                           signpost,
                           i1,
                           0x02);
        } else {
            world.setBlock(i,
                           j,
                           k,
                           wallsign,
                           l,
                           0x02);
        }
        itemstack.stackSize--;
        TileEntity tileentity = world.getTileEntity(i,
                                                    j,
                                                    k);
        if (tileentity != null && tileentity instanceof TileEntitySign) {
            TileEntitySign tileentitymtsign = (TileEntitySign) tileentity;
            tileentitymtsign.setTexture(itemstack.getItemDamage());
            tileentitymtsign.onInventoryChanged();
            if (world.isRemote) {
                entityplayer.openGui(MoreSigns.instance,
                                     GuiLib.GUI_SIGNS,
                                     world,
                                     i,
                                     j,
                                     k);
            }
            return true;
        }
        return false;
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    @Override
    public void getSubItems(Item item, CreativeTabs creativeTabs, List list) {
        for (int i = 0; i < this.signNames.length; i++) {
            list.add(new ItemStack(item, 1, i));
        }
    }
}
