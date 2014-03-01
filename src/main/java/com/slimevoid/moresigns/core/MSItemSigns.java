package com.slimevoid.moresigns.core;

import net.minecraft.item.ItemStack;

public enum MSItemSigns {
	woodenSign, ironCladSign, goldPlatedSign, diamondLatheredSign;

	public ItemStack	me;
	public int			stackID;
	public String		name;
	public ItemStack	droppedItem;

	public static ItemStack getStack(int itemDamage) {
		for (MSItemSigns itemstack : MSItemSigns.values()) {
			if (itemstack != null && itemstack.stackID == itemDamage) {
				return itemstack.me;
			}
		}
		return null;
	}

	public static ItemStack getDroppedItem(int damageValue) {
		return MSItemSigns.values()[damageValue].droppedItem;
	}
}
