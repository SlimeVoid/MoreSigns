package eurymachus.mts.items;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import eurymachus.mts.core.MTSInit;
import eurymachus.mts.tileentities.TileEntityMTSign;

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
		if (!entityplayer.canPlayerEdit(i, j, k, l, itemstack)) {
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
