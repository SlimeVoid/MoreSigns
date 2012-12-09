package mts.items;

import mts.core.MTSInit;
import mts.tileentities.TileEntityMTSign;
import net.minecraft.src.CreativeTabs;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.Item;
import net.minecraft.src.ItemStack;
import net.minecraft.src.TileEntity;
import net.minecraft.src.World;

public class ItemMTSignTool extends Item {
	public ItemMTSignTool(int i) {
		super(i);
		this.setMaxStackSize(1);
		this.setMaxDamage(100);
		this.iconIndex = 32;
		this.setCreativeTab(CreativeTabs.tabTools);
	}

	@Override
	public boolean onItemUse(ItemStack itemstack, EntityPlayer entityplayer, World world, int i, int j, int k, int l, float a, float b, float c) {
		if (!entityplayer.func_82247_a(i, j, k, l, itemstack)) {
			return false;
		} else {
			TileEntity tileentity = world.getBlockTileEntity(i, j, k);
			if (tileentity != null && tileentity instanceof TileEntityMTSign) {
				TileEntityMTSign tileentitymtsign = (TileEntityMTSign) tileentity;
				MTSInit.MTS.getProxy().displayTileEntityGui(
						entityplayer,
						tileentitymtsign);
				itemstack.damageItem(5, entityplayer);
				return true;
			} else
				return false;
		}
	}

	@Override
	public String getTextureFile() {
		return MTSInit.MTS.getItemSheet();
	}
}
