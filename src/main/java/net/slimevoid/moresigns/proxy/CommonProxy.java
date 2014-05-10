package net.slimevoid.moresigns.proxy;

import java.io.File;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.slimevoid.library.ICommonProxy;
import net.slimevoid.library.util.helpers.BlockHelper;
import net.slimevoid.moresigns.container.ContainerSign;
import net.slimevoid.moresigns.core.MSCore;
import net.slimevoid.moresigns.core.MoreSigns;
import net.slimevoid.moresigns.core.lib.GuiLib;
import net.slimevoid.moresigns.tileentities.TileEntitySign;
import cpw.mods.fml.common.network.NetworkRegistry;

public class CommonProxy implements ICommonProxy {

    @Override
    public void registerRenderInformation() {
    }

    @Override
    public void registerTileEntitySpecialRenderer(Class<? extends TileEntity> clazz) {

    }

    @Override
    public String getMinecraftDir() {
        return "./";
    }

    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        if (ID == GuiLib.GUI_SIGNS) {
            TileEntitySign tileentity = (TileEntitySign) BlockHelper.getTileEntity(world,
                                                                                   x,
                                                                                   y,
                                                                                   z,
                                                                                   TileEntitySign.class);
            return new ContainerSign(tileentity);
        }
        return null;
    }

    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        return null;
    }

    @Override
    public void preInit() {
        MSCore.preInit();
        NetworkRegistry.INSTANCE.registerGuiHandler(MoreSigns.instance,
                                                    MoreSigns.proxy);
    }

    @Override
    public void init() {
    }

    @Override
    public void postInit() {
    }

    @Override
    public void registerConfigurationProperties(File configFile) {
        // TODO Auto-generated method stub

    }

    @Override
    public void registerTickHandlers() {
        // TODO Auto-generated method stub

    }

    @Override
    public void registerEventHandlers() {
        // TODO Auto-generated method stub

    }

    @Override
    public boolean isClient(World world) {
        // TODO Auto-generated method stub
        return false;
    }
}
