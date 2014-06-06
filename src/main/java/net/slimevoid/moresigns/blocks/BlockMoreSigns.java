package net.slimevoid.moresigns.blocks;

import java.util.Random;

import net.minecraft.block.BlockSign;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.slimevoid.moresigns.core.MSInit;

public class BlockMoreSigns extends BlockSign {

    /*
     * protected Icon[] iconList;
     * @Override public void registerIcons(IconRegister iconRegister) { iconList
     * = new Icon[4]; }
     */

    public BlockMoreSigns(int i, Class<? extends TileEntity> signClass, boolean flag, float hardness, float resistance, boolean disableStats, boolean requiresSelfNotify) {
        super(signClass, flag);

        setHardness(hardness);
        setResistance(resistance);
        if (disableStats) disableStats();
        // if (requiresSelfNotify)
        // setRequiresSelfNotify();
    }

    /*
     * @Override public Icon getBlockTexture(IBlockAccess par1IBlockAccess, int
     * par2, int par3, int par4, int par5) { return
     * iconList[MTSInit.getDamageValue(par1IBlockAccess, par2, par3, par4)]; }
     * @Override public Icon getBlockTextureFromSideAndMetadata(int par1, int
     * par2) { return
     * iconList[MTSInit.MTS.getProxy().getBlockTextureFromMetadata(par2)]; }
     */

    @Override
    public int quantityDropped(Random rand) {
        return 0;
    }

    @Override
    public boolean removedByPlayer(World world, EntityPlayer player, int x, int y, int z) {
        if (!world.isRemote) {
            if (!player.capabilities.isCreativeMode) {
                ItemStack itemstack = MSItemSigns.getDroppedItem(MSInit.getDamageValue(world,
                                                                                       x,
                                                                                       y,
                                                                                       z));
                if (itemstack != null) {
                    EntityItem entityitem = new EntityItem(world, x, y, z, new ItemStack(itemstack.getItem(), 1, itemstack.getItemDamage()));
                    world.spawnEntityInWorld(entityitem);
                }
            }
        }
        return super.removedByPlayer(world,
                                     player,
                                     x,
                                     y,
                                     z);
    }
}
