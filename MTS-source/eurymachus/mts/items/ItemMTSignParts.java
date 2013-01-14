package eurymachus.mts.items;

import java.util.List;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import eurymachus.mts.core.MTSInit;

public class ItemMTSignParts extends Item {
	public ItemMTSignParts(int i) {
		super(i);
		this.setHasSubtypes(true);
		this.setMaxDamage(0);
		this.setNoRepair();
		this.setCreativeTab(CreativeTabs.tabMaterials);
	}

	private String[] signParts = new String[] {
			"IronPlating",
			"IronPole",
			"GoldPlating",
			"GoldenPole",
			"DiamondPlating",
			"DiamondPole" };

	@Override
	public String getItemNameIS(ItemStack itemstack) {
		return (new StringBuilder())
				.append(super.getItemName())
					.append(".")
					.append(signParts[itemstack.getItemDamage()])
					.toString();
	}

	public int filterData(int i) {
		return i;
	}

	@Override
	public int getIconFromDamage(int i) {
		if (i == 0 || i == 2 || i == 4) {
			setMaxStackSize(16);
		}
		if (i == 1 || i == 3 || i == 5) {
			setMaxStackSize(8);
		}
		return i;
	}

	@Override
	public String getTextureFile() {
		return MTSInit.MTS.getItemSheet();
	}
	
	@Override
	public void getSubItems(int itemID, CreativeTabs creativeTabs, List list) {
		for (int i = 0; i < this.signParts.length; i++) {
			list.add(new ItemStack(itemID, 1, i));
		}
	}
}
