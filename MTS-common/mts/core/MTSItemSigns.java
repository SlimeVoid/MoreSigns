package mts.core;

import net.minecraft.item.ItemStack;

public enum MTSItemSigns {
	ironCladSign, goldPlatedSign, diamondLatheredSign;

	public ItemStack me;
	public int stackID;
	public String name;

	public static ItemStack getStack(int itemDamage) {
		for (MTSItemSigns itemstack : MTSItemSigns.values()) {
			if (itemstack != null && itemstack.stackID == itemDamage) {
				return itemstack.me;
			}
		}
		return null;
	}
}
