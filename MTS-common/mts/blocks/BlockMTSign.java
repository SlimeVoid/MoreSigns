package mts.blocks;

import java.util.Random;

import mts.core.MTSInit;
import mts.core.MTSItemParts;
import net.minecraft.src.BlockSign;
import net.minecraft.src.EntityItem;
import net.minecraft.src.EnumGameType;
import net.minecraft.src.IBlockAccess;
import net.minecraft.src.ItemStack;
import net.minecraft.src.World;

public class BlockMTSign extends BlockSign {

	public BlockMTSign(int i, Class class1, boolean flag, float hardness, float resistance, boolean disableStats, boolean requiresSelfNotify) {
		super(i, class1, flag);

		setHardness(hardness);
		setResistance(resistance);
		if (disableStats)
			disableStats();
		if (requiresSelfNotify)
			setRequiresSelfNotify();
	}

	public int getBlockTexture(IBlockAccess par1IBlockAccess, int par2, int par3, int par4, int par5) {
		return MTSInit.getDamageValue(par1IBlockAccess, par2, par3, par4);
	}

	@Override
	public int getBlockTextureFromSideAndMetadata(int par1, int par2) {
		return MTSInit.MTS.getProxy().getBlockTextureFromMetadata(par2);
	}

	@Override
	public int quantityDropped(Random rand) {
		return 0;
	}

	@Override
	public void breakBlock(World world, int i, int j, int k, int l, int m) {
		int itemDamage = -1;
		switch (MTSInit.getDamageValue(world, i, j, k)) {
		case 0:
			itemDamage = 0;
			break;
		case 1:
			itemDamage = 2;
			break;
		case 2:
			itemDamage = 4;
			break;
		}
		if (itemDamage > -1) {
			if(world.getWorldInfo().getGameType() != EnumGameType.CREATIVE) {
				ItemStack itemstack = MTSItemParts.getStack(itemDamage);
				if (itemstack != null) {
					EntityItem entityitem = new EntityItem(
							world,
							i,
							j,
							k,
							new ItemStack(
									itemstack.itemID,
									1,
									itemstack.getItemDamage()));
					world.spawnEntityInWorld(entityitem);
				}
			} else {
				return;
			}
		}
		super.breakBlock(world, i, j, k, l, m);
	}

	@Override
	public String getTextureFile() {
		return MTSInit.MTS.getBlockSheet();
	}
}
