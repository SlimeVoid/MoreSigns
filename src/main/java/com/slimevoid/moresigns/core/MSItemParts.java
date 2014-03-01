package com.slimevoid.moresigns.core;

import net.minecraft.item.ItemStack;

public enum MSItemParts {
	ironCladPlating,
	ironCladPole,
	goldPlating,
	goldenPole,
	diamondPlating,
	diamondPole;

	public ItemStack	me;
	public int			stackID;
	public String		name;

	public static ItemStack getStack(int itemDamage) {
		for (MSItemParts item : MSItemParts.values()) {
			if (item.stackID == itemDamage) {
				return item.me;
			}
		}
		return null;
	}
}
