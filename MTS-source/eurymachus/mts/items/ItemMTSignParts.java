package eurymachus.mts.items;

import java.util.List;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import eurymachus.mts.core.MTSInit;
import eurymachus.mts.core.lib.IconLib;
import eurymachus.mts.core.lib.ItemLib;

public class ItemMTSignParts extends Item {
	
	protected Icon[] iconList;
	
	@Override
	public void registerIcons(IconRegister iconRegister) {
		iconList = new Icon[6];
		iconList[0] = iconRegister.registerIcon(IconLib.ICON_PART_PLATE_IRON);
		iconList[2] = iconRegister.registerIcon(IconLib.ICON_PART_PLATE_GOLD);
		iconList[4] = iconRegister.registerIcon(IconLib.ICON_PART_PLATE_DIAMOND);
		iconList[1] = iconRegister.registerIcon(IconLib.ICON_PART_POLE_IRON);
		iconList[3] = iconRegister.registerIcon(IconLib.ICON_PART_POLE_GOLD);
		iconList[5] = iconRegister.registerIcon(IconLib.ICON_PART_POLE_DIAMOND);
	}
	
	public ItemMTSignParts(int i) {
		super(i);
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
	public String getItemDisplayName(ItemStack itemstack) {
		return (new StringBuilder())
				.append(super.getUnlocalizedName())
					.append(".")
					.append(signParts[itemstack.getItemDamage()])
					.toString();
	}

	public int filterData(int i) {
		return i;
	}

	@Override
	public Icon getIconFromDamage(int i) {
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
	public void getSubItems(int itemID, CreativeTabs creativeTabs, List list) {
		for (int i = 0; i < this.signParts.length; i++) {
			list.add(new ItemStack(itemID, 1, i));
		}
	}
}
