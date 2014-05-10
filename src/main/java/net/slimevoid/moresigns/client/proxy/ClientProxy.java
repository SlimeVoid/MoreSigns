package net.slimevoid.moresigns.client.proxy;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.slimevoid.library.util.helpers.BlockHelper;
import net.slimevoid.moresigns.client.guis.GuiEditSign;
import net.slimevoid.moresigns.client.render.TileEntitySignRenderer;
import net.slimevoid.moresigns.core.lib.GuiLib;
import net.slimevoid.moresigns.proxy.CommonProxy;
import net.slimevoid.moresigns.tileentities.TileEntitySign;
import cpw.mods.fml.client.registry.ClientRegistry;

public class ClientProxy extends CommonProxy {

    @Override
    public String getMinecraftDir() {
        return Minecraft.getMinecraft().mcDataDir.getPath();
    }

    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        if (ID == GuiLib.GUI_SIGNS) {
            TileEntitySign tileentity = (TileEntitySign) BlockHelper.getTileEntity(world,
                                                                                   x,
                                                                                   y,
                                                                                   z,
                                                                                   TileEntitySign.class);
            if (tileentity != null) {
                GuiEditSign gui = new GuiEditSign(tileentity);
                return gui;
            }
        }
        return null;
    }

    @Override
    public void registerRenderInformation() {
        /*
         * MinecraftForgeClient.preloadTexture(MTSInit.MTS.getBlockSheet());
         * MinecraftForgeClient.preloadTexture(MTSInit.MTS.getItemSheet());
         * MinecraftForgeClient.preloadTexture(MTSInit.getSignTexture(0));
         * MinecraftForgeClient.preloadTexture(MTSInit.getSignTexture(1));
         * MinecraftForgeClient.preloadTexture(MTSInit.getSignTexture(2));
         * MinecraftForgeClient.preloadTexture(MTSInit.getSignTexture(3));
         */
    }

    @Override
    public void registerTileEntitySpecialRenderer(Class<? extends TileEntity> clazz) {
        ClientRegistry.bindTileEntitySpecialRenderer(clazz,
                                                     new TileEntitySignRenderer());
    }
}
