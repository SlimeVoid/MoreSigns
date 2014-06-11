package net.slimevoid.moresigns.core.lib;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.slimevoid.moresigns.items.ItemSignParts;
import cpw.mods.fml.common.registry.GameRegistry;

public enum EnumItemSignParts {

    SIGN_TOOL(ItemLib.TOOL_PREFIX),

    IRON_POLE(ItemLib.POLE_IRON),
    IRON_PLATING(ItemLib.PLATE_IRON),
    GOLD_POLE(ItemLib.POLE_GOLD),
    GOLD_PLATING(ItemLib.PLATE_GOLD),
    DIAMOND_POLE(ItemLib.POLE_DIAMOND),
    DIAMOND_PLATING(ItemLib.PLATE_DIAMOND);

    public Item   instance;
    public String name;

    EnumItemSignParts(String name) {
        this.name = name;
    }

    public ItemStack getItemStack(int stackSize) {
        return new ItemStack(this.instance, stackSize, 0);
    }

    public static void registerItemSignParts() {
        for (EnumItemSignParts part : EnumItemSignParts.values()) {
            part.instance = new ItemSignParts().setUnlocalizedName(part.name);
            GameRegistry.registerItem(part.instance,
                                      part.name);
        }
    }
}
