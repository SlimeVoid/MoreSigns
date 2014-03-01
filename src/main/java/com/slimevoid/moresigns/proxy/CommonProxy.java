package com.slimevoid.moresigns.proxy;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.File;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.NetLoginHandler;
import net.minecraft.network.packet.NetHandler;
import net.minecraft.network.packet.Packet1Login;
import net.minecraft.network.packet.Packet250CustomPayload;
import net.minecraft.server.MinecraftServer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import com.slimevoid.library.ICommonProxy;
import com.slimevoid.library.IPacketHandling;
import com.slimevoid.library.network.PacketIds;
import com.slimevoid.library.util.helpers.BlockHelper;
import com.slimevoid.moresigns.container.ContainerSign;
import com.slimevoid.moresigns.core.MoreSigns;
import com.slimevoid.moresigns.core.lib.GuiLib;
import com.slimevoid.moresigns.network.ServerPacketHandler;
import com.slimevoid.moresigns.network.packets.PacketUpdateSign;
import com.slimevoid.moresigns.tileentities.TileEntitySign;

import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.Player;

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
			TileEntitySign tileentity = (TileEntitySign) BlockHelper.getTileEntity(	world,
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

	public int getMouseOver() {
		return 0;
	}

	public int getBelowPlayer(EntityPlayer player) {
		return 0;
	}

	public int getAtPlayer(EntityPlayer player) {
		return 0;
	}

	@Override
	public void onPacketData(INetworkManager manager, Packet250CustomPayload packet, Player player) {
		DataInputStream data = new DataInputStream(new ByteArrayInputStream(packet.data));
		try {
			EntityPlayer entityplayer = (EntityPlayer) player;
			World world = entityplayer.worldObj;
			int packetID = data.read();
			switch (packetID) {
			case PacketIds.TILE:
				PacketUpdateSign packetSign = new PacketUpdateSign();
				packetSign.readData(data);
				this.getPacketHandler().handleTileEntityPacket(	packetSign,
																entityplayer,
																world);
				break;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	@Override
	public IPacketHandling getPacketHandler() {
		return new ServerPacketHandler();
	}

	@Override
	public void preInit() {
		NetworkRegistry.instance().registerGuiHandler(	MoreSigns.instance,
														MoreSigns.proxy);
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

	@Override
	public void playerLoggedIn(Player player, NetHandler netHandler, INetworkManager manager) {
		// TODO Auto-generated method stub

	}

	@Override
	public String connectionReceived(NetLoginHandler netHandler, INetworkManager manager) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void connectionOpened(NetHandler netClientHandler, String server, int port, INetworkManager manager) {
		// TODO Auto-generated method stub

	}

	@Override
	public void connectionOpened(NetHandler netClientHandler, MinecraftServer server, INetworkManager manager) {
		// TODO Auto-generated method stub

	}

	@Override
	public void connectionClosed(INetworkManager manager) {
		// TODO Auto-generated method stub

	}

	@Override
	public void clientLoggedIn(NetHandler clientHandler, INetworkManager manager, Packet1Login login) {
		// TODO Auto-generated method stub

	}
}
