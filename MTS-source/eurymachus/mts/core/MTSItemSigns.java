package eurymachus.mts.core;

import net.minecraft.item.ItemStack;

public enum MTSItemSigns {
	woodenSign, ironCladSign, goldPlatedSign, diamondLatheredSign;

	public ItemStack me;
	public int stackID;
	public String name;
	public ItemStack droppedItem;

	public static ItemStack getStack(int itemDamage) {
		for (MTSItemSigns itemstack : MTSItemSigns.values()) {
			if (itemstack != null && itemstack.stackID == itemDamage) {
				return itemstack.me;
			}
		}
		return null;
	}

	public static ItemStack getDroppedItem(int damageValue) {
		return MTSItemSigns.values()[damageValue].droppedItem;
	}
}
