package eurymachus.mts.items;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import eurymachus.mts.core.MTSBlocks;
import eurymachus.mts.core.MTSInit;
import eurymachus.mts.tileentities.TileEntityMTSign;

public class ItemMTSigns extends Item {
	public ItemMTSigns(int i) {
		super(i);
		this.setHasSubtypes(true);
		this.setMaxStackSize(1);
		this.setMaxDamage(0);
		this.setNoRepair();
		this.setCreativeTab(CreativeTabs.tabDecorations);
	}

	private String[] signNames = new String[] {
			"IronSign",
			"GoldSign",
			"DiamondSign" };

	@Override
	public String getItemNameIS(ItemStack itemstack) {
		return (new StringBuilder())
				.append(super.getItemName())
					.append(".")
					.append(signNames[itemstack.getItemDamage()])
					.toString();
	}

	public int filterData(int i) {
		return i;
	}

	@Override
	public int getIconFromDamage(int i) {
		int index = i;
		switch (i) {
		case 0:
			return 16;
		case 1:
			return 17;
		case 2:
			return 18;
		default:
			return 19;
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
			world.setBlockAndMetadataWithNotify(i, j, k, signpost.blockID, i1);
		} else {
			world.setBlockAndMetadataWithNotify(i, j, k, wallsign.blockID, l);
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

	@Override
	public String getTextureFile() {
		return MTSInit.MTS.getItemSheet();
	}
	
	@Override
	public void getSubItems(int itemID, CreativeTabs creativeTabs, List list) {
		for (int i = 0; i < this.signNames.length; i++) {
			list.add(new ItemStack(itemID, 1, i));
		}
	}
}
