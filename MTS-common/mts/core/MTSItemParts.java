package mts.core;

import net.minecraft.src.ItemStack;

public enum MTSItemParts {
	ironCladPlating,
	ironCladPole,
	goldPlating,
	goldenPole,
	diamondPlating,
	diamondPole;

	public ItemStack me;
	public int stackID;
	public String name;

	public static ItemStack getStack(int itemDamage) {
		for (MTSItemParts item : MTSItemParts.values()) {
			if (item.stackID == itemDamage) {
				return item.me;
			}
		}
		return null;
	}
}
