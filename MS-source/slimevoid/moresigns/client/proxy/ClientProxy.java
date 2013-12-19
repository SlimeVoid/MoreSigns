package slimevoid.moresigns.client.proxy;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.src.ModLoader;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import slimevoid.moresigns.client.guis.GuiEditSign;
import slimevoid.moresigns.client.network.ClientPacketHandler;
import slimevoid.moresigns.client.render.TileEntitySignRenderer;
import slimevoid.moresigns.core.MSInit;
import slimevoid.moresigns.core.lib.GuiLib;
import slimevoid.moresigns.proxy.CommonProxy;
import slimevoid.moresigns.tileentities.TileEntitySign;
import slimevoidlib.IPacketHandling;
import slimevoidlib.util.helpers.BlockHelper;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ClientProxy extends CommonProxy {

	@Override
	public String getMinecraftDir() {
		return Minecraft.getMinecraft().mcDataDir.getPath();
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		if (ID == GuiLib.GUI_SIGNS) {
			TileEntitySign tileentity = (TileEntitySign) BlockHelper.getTileEntity(	world,
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
		ClientRegistry.bindTileEntitySpecialRenderer(	clazz,
														new TileEntitySignRenderer());
	}

	@SideOnly(Side.CLIENT)
	private static Minecraft	mc	= ModLoader.getMinecraftInstance();

	@Override
	public int getMouseOver() {
		if (mc.objectMouseOver != null) {
			int xPosition = mc.objectMouseOver.blockX;
			int yPosition = mc.objectMouseOver.blockY;
			int zPosition = mc.objectMouseOver.blockZ;
			return MSInit.getDamageValue(	mc.theWorld,
											xPosition,
											yPosition,
											zPosition);
		}
		return 0;
	}

	@Override
	public int getBelowPlayer(EntityPlayer player) {
		int playerX = (int) player.posX;
		int playerY = (int) player.posY;
		int playerZ = (int) player.posZ;
		return MSInit.getDamageValue(	mc.theWorld,
										playerX,
										playerY - 1,
										playerZ);
	}

	@Override
	public int getAtPlayer(EntityPlayer player) {
		int playerX = (int) player.posX;
		int playerY = (int) player.posY;
		int playerZ = (int) player.posZ;
		return MSInit.getDamageValue(	mc.theWorld,
										playerX,
										playerY,
										playerZ);
	}

	@Override
	public IPacketHandling getPacketHandler() {
		return new ClientPacketHandler();
	}
}
