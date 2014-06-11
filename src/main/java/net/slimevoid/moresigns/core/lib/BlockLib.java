package net.slimevoid.moresigns.core.lib;

import net.minecraft.util.ResourceLocation;

public class BlockLib {

    private static final String    BLOCK_PREFIX        = "ms.";
    public static final String     SIGN_PREFIX         = BLOCK_PREFIX + "sign.";
    public static final String     BLOCK_SIGN          = SIGN_PREFIX
                                                         + "default";
    public static final String     BLOCK_SIGN_STANDING = ".post";
    public static final String     BLOCK_SIGN_WALL     = ".wall";
    public static final String     BLOCK_SIGN_IRON     = SIGN_PREFIX + "iron";
    public static final String     BLOCK_SIGN_GOLD     = SIGN_PREFIX + "gold";
    public static final String     BLOCK_SIGN_DIAMOND  = SIGN_PREFIX
                                                         + "diamond";
    public static ResourceLocation woodenSign          = new ResourceLocation("textures/entity/sign.png");
    public static ResourceLocation ironSign            = new ResourceLocation(CoreLib.MOD_RESOURCES, BLOCK_PREFIX
                                                                                                     + "mtsIronSign.png");
    public static ResourceLocation goldSign            = new ResourceLocation(CoreLib.MOD_RESOURCES, BLOCK_PREFIX
                                                                                                     + "mtsGoldSign.png");
    public static ResourceLocation diamondSign         = new ResourceLocation(CoreLib.MOD_RESOURCES, BLOCK_PREFIX
                                                                                                     + "mtsDiamondSign.png");
    public static final String     NBT_SIGN_METADATA   = "metadata";
    public static final String     NBT_SIGN_TEXT       = "line";

    public static ResourceLocation getSignTexture(int textureData) {
        switch (textureData) {
        case 0:
            return woodenSign;
        case 1:
            return ironSign;
        case 2:
            return goldSign;
        case 3:
            return diamondSign;
        default:
            return ironSign;
        }
    }
}
