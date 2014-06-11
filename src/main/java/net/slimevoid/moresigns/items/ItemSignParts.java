package net.slimevoid.moresigns.items;

import java.util.List;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.slimevoid.moresigns.core.lib.IconLib;
import net.slimevoid.moresigns.core.lib.ItemLib;

public class ItemSignParts extends Item {

    protected IIcon[] iconList;

    @Override
    public void registerIcons(IIconRegister iconRegister) {
        iconList = new IIcon[6];
        iconList[0] = iconRegister.registerIcon(IconLib.ICON_PART_PLATE_IRON);
        iconList[2] = iconRegister.registerIcon(IconLib.ICON_PART_PLATE_GOLD);
        iconList[4] = iconRegister.registerIcon(IconLib.ICON_PART_PLATE_DIAMOND);
        iconList[1] = iconRegister.registerIcon(IconLib.ICON_PART_POLE_IRON);
        iconList[3] = iconRegister.registerIcon(IconLib.ICON_PART_POLE_GOLD);
        iconList[5] = iconRegister.registerIcon(IconLib.ICON_PART_POLE_DIAMOND);
    }

    public ItemSignParts() {
        super();
        this.setHasSubtypes(true);
        this.setMaxDamage(0);
        this.setNoRepair();
        this.setCreativeTab(CreativeTabs.tabMaterials);
    }

    private String[] signParts = new String[] {
            ItemLib.PLATE_IRON,
            ItemLib.POLE_IRON,
            ItemLib.PLATE_GOLD,
            ItemLib.POLE_IRON,
            ItemLib.PLATE_DIAMOND,
            ItemLib.POLE_DIAMOND };

    @Override
    public String getUnlocalizedName(ItemStack itemstack) {
        return (new StringBuilder()).append(super.getUnlocalizedName()).append(".").append(signParts[itemstack.getItemDamage()]).toString();
    }

    public int filterData(int i) {
        return i;
    }

    @Override
    public IIcon getIconFromDamage(int i) {
        if (i == 0 || i == 2 || i == 4) {
            setMaxStackSize(16);
        }
        if (i == 1 || i == 3 || i == 5) {
            setMaxStackSize(8);
        }
        return iconList[i];
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    @Override
    public void getSubItems(Item item, CreativeTabs creativeTabs, List list) {
        for (int i = 0; i < this.signParts.length; i++) {
            list.add(new ItemStack(item, 1, i));
        }
    }
}
