package net.slimevoid.moresigns.core.lib;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemStack;
import net.slimevoid.library.core.SlimevoidCore;
import net.slimevoid.moresigns.blocks.BlockMoreSigns;
import cpw.mods.fml.common.registry.GameRegistry;

public enum EnumBlockSigns {

    IRON_STANDING(
            BlockLib.BLOCK_SIGN_IRON + BlockLib.BLOCK_SIGN_STANDING,
            Material.iron,
            true),
    IRON_WALL(
            BlockLib.BLOCK_SIGN_IRON + BlockLib.BLOCK_SIGN_WALL,
            Material.iron),
    GOLD_STANDING(
            BlockLib.BLOCK_SIGN_GOLD + BlockLib.BLOCK_SIGN_STANDING,
            Material.iron,
            true),
    GOLD_WALL(
            BlockLib.BLOCK_SIGN_GOLD + BlockLib.BLOCK_SIGN_WALL,
            Material.iron),
    DIAMOND_STANDING(
            BlockLib.BLOCK_SIGN_DIAMOND + BlockLib.BLOCK_SIGN_STANDING,
            Material.iron,
            true),
    DIAMOND_WALL(
            BlockLib.BLOCK_SIGN_DIAMOND + BlockLib.BLOCK_SIGN_WALL,
            Material.iron);

    private Block     instance;
    private Material  material;
    private String    name;
    private boolean   isStanding = false;
    private ItemStack droppedItem;

    EnumBlockSigns(String name, Material material, boolean isStanding) {
        this.name = name;
        this.material = material;
        this.isStanding = isStanding;
    }

    EnumBlockSigns(String name, Material material) {
        this.name = name;
        this.material = material;
    }

    public void setDroppedItem(ItemStack itemstack) {
        this.droppedItem = itemstack;
    }

    public Block getInstance() {
        return this.instance;
    }

    private boolean isStanding() {
        return this.isStanding;
    }

    public static ItemStack getDroppedItem(Block instance) {
        for (EnumBlockSigns sign : EnumBlockSigns.values()) {
            if (instance.isAssociatedBlock(sign.instance)) {
                return sign.droppedItem;
            }
        }
        SlimevoidCore.console(CoreLib.MOD_ID,
                              "Unknown Block Type no harvested stack returned",
                              1);
        return null;
    }

    public static void registerBlockSigns() {
        for (EnumBlockSigns sign : EnumBlockSigns.values()) {
            sign.instance = new BlockMoreSigns(sign.material, sign.isStanding()).setBlockName(sign.name);
            GameRegistry.registerBlock(sign.instance,
                                       sign.name);
        }
    }

}
