package eurymachus.mts.items;

import java.util.List;

import cpw.mods.fml.common.FMLCommonHandler;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Icon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import eurymachus.mts.core.MTSBlocks;
import eurymachus.mts.core.MTSInit;
import eurymachus.mts.core.lib.IconLib;
import eurymachus.mts.tileentities.TileEntityMTSign;

public class ItemMTSigns extends Item {
	
	protected Icon[] iconList;
	
	public void updateIcons(IconRegister iconRegister) {
		iconList = new Icon[4];
		iconList[0] = iconRegister.registerIcon(IconLib.ICON_SIGN_WOOD);
		iconList[1] = iconRegister.registerIcon(IconLib.ICON_SIGN_IRON);
		iconList[2] = iconRegister.registerIcon(IconLib.ICON_SIGN_GOLD);
		iconList[3] = iconRegister.registerIcon(IconLib.ICON_SIGN_DIAMOND);
	}
	
	public ItemMTSigns(int i) {
		super(i);
		this.setHasSubtypes(true);
		this.setMaxStackSize(1);
		this.setMaxDamage(0);
		this.setNoRepair();
		this.setCreativeTab(CreativeTabs.tabDecorations);
	}

	private String[] signNames = new String[] {
			"WoodenSign",
			"IronSign",
			"GoldSign",
			"DiamondSign" };

	@Override
	public String getItemDisplayName(ItemStack itemstack) {
		return (new StringBuilder())
				.append(super.getUnlocalizedName())
					.append(".")
					.append(signNames[itemstack.getItemDamage()])
					.toString();
	}

	public int filterData(int i) {
		return i;
	}

	@Override
	public Icon getIconFromDamage(int i) {
		try {
			return iconList[i];
		} catch (Exception e) {
			FMLCommonHandler.instance().getFMLLogger().severe("No Such Sign");
			return this.itemIcon;
		}
	}

	@Override
	public boolean onItemUse(ItemStack itemstack, EntityPlayer entityplayer, World world, int i, int j, int k, int l, float a, float b, float c) {
		Block signpost = MTSBlocks.mtSignPost.me;
		Block wallsign = MTSBlocks.mtSignWall.me;
		if (l == 0) {
			return false;
		}
		if (!world.getBlockMaterial(i, j, k).isSolid()) {
			return false;
		}
		if (l == 1) {
			j++;
		}
		if (l == 2) {
			k--;
		}
		if (l == 3) {
			k++;
		}
		if (l == 4) {
			i--;
		}
		if (l == 5) {
			i++;
		}
		if (!entityplayer.canPlayerEdit(i, j, k, l, itemstack)) {
			return false;
		}
		if (!signpost.canPlaceBlockAt(world, i, j, k)) {
			return false;
		}
		if (l == 1) {
			int i1 = MathHelper
					.floor_double((((entityplayer.rotationYaw + 180F) * 16F) / 360F) + 0.5D) & 0xf;
			world.setBlock(i, j, k, signpost.blockID, i1, 0x02);
		} else {
			world.setBlock(i, j, k, wallsign.blockID, l, 0x02);
		}
		itemstack.stackSize--;
		TileEntity tileentity = world.getBlockTileEntity(i, j, k);
		if (tileentity != null && tileentity instanceof TileEntityMTSign) {
			TileEntityMTSign tileentitymtsign = (TileEntityMTSign) tileentity;
			tileentitymtsign.setTextureValue(itemstack.getItemDamage());
			tileentitymtsign.onInventoryChanged();
			MTSInit.MTS.getProxy().displayTileEntityGui(
					entityplayer,
					tileentitymtsign);
			return true;
		}
		return false;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public void getSubItems(int itemID, CreativeTabs creativeTabs, List list) {
		for (int i = 0; i < this.signNames.length; i++) {
			list.add(new ItemStack(itemID, 1, i));
		}
	}
}
