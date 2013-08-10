package eurymachus.mts.proxy;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.NetHandler;
import net.minecraft.network.packet.Packet1Login;
import net.minecraft.network.packet.Packet250CustomPayload;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import slimevoidlib.ICommonProxy;
import slimevoidlib.IPacketHandling;
import slimevoidlib.network.PacketIds;
import cpw.mods.fml.common.network.Player;
import eurymachus.mts.core.MTSInit;
import eurymachus.mts.network.ServerPacketHandler;
import eurymachus.mts.network.packets.PacketOpenGui;
import eurymachus.mts.network.packets.PacketUpdateMTSign;

public class CommonProxy implements ICommonProxy {

	@Override
	public void registerRenderInformation() {
	}

	@Override
	public void registerTileEntitySpecialRenderer(Class<? extends TileEntity> clazz) {

	}

	@Override
	public void displayTileEntityGui(EntityPlayer entityplayer, TileEntity tileentity) {
		EntityPlayerMP entityplayermp = (EntityPlayerMP) entityplayer;
		PacketOpenGui gui = new PacketOpenGui(
				tileentity.xCoord,
					tileentity.yCoord,
					tileentity.zCoord);
		entityplayermp.playerNetServerHandler.netManager.addToSendQueue(gui
				.getPacket());
	}

	@Override
	public String getMinecraftDir() {
		return "./";
	}

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
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
	public int getBlockTextureFromMetadata(int meta) {
		return 0;
	}

	@Override
	public int getBlockTextureFromSideAndMetadata(int side, int meta) {
		return getBlockTextureFromMetadata(meta);
	}

	@Override
	public void onPacketData(INetworkManager manager, Packet250CustomPayload packet, Player player) {
		DataInputStream data = new DataInputStream(new ByteArrayInputStream(
				packet.data));
		try {
			EntityPlayer entityplayer = (EntityPlayer) player;
			World world = entityplayer.worldObj;
			int packetID = data.read();
			switch (packetID) {
			case PacketIds.TILE:
				PacketUpdateMTSign packetSign = new PacketUpdateMTSign();
				packetSign.readData(data);
				this.getPacketHandler().handleTileEntityPacket(
						packetSign,
						entityplayer,
						world);
				break;
			case PacketIds.GUI:
				PacketOpenGui packetGui = new PacketOpenGui();
				packetGui.readData(data);
				this.getPacketHandler().handleGuiPacket(
						packetGui,
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
	public World getWorld() {
		return null;
	}

	@Override
	public World getWorld(NetHandler handler) {
		return null;
	}

	@Override
	public EntityPlayer getPlayer() {
		return null;
	}

	@Override
	public void login(NetHandler handler, INetworkManager manager, Packet1Login login) {
	}

	@Override
	public void registerTickHandler() {
	}

	@Override
	public void preInit() {
	}
}
