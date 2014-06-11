package net.slimevoid.moresigns.core.lib;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.slimevoid.moresigns.items.ItemSigns;
import cpw.mods.fml.common.registry.GameRegistry;

public enum EnumItemSigns {

    IRON(ItemLib.SIGN_IRON),
    GOLD(ItemLib.SIGN_GOLD),
    DIAMOND(ItemLib.SIGN_DIAMOND);

    private Item   instance;
    private String name;
    private Block  blockRef_Standing;
    private Block  blockRef_Wall;

    EnumItemSigns(String name) {
        this.name = name;
    }

    public ItemStack getItemStack(int stackSize) {
        return new ItemStack(this.instance, stackSize, 0);
    }

    public void setBlockReference(Block blockRef_Standing, Block blockRef_Wall) {
        this.blockRef_Standing = blockRef_Standing;
        this.blockRef_Wall = blockRef_Wall;
    }

    public Block getBlockReference(boolean isStanding) {
        return isStanding ? this.blockRef_Standing : this.blockRef_Wall;
    }

    public static void registerItemSigns() {
        for (EnumItemSigns sign : EnumItemSigns.values()) {
            sign.instance = new ItemSigns(sign.blockRef_Standing, sign.blockRef_Wall).setUnlocalizedName(sign.name);
            GameRegistry.registerItem(sign.instance,
                                      sign.name);
        }
    }
}
