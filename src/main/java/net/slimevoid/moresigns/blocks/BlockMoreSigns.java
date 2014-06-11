package net.slimevoid.moresigns.blocks;

import net.minecraft.block.BlockSign;
import net.minecraft.block.material.Material;
import net.slimevoid.moresigns.tileentities.TileEntitySign;

public class BlockMoreSigns extends BlockSign {

    protected Material material;

    public BlockMoreSigns(Material material, boolean isStanding) {
        super(TileEntitySign.class, isStanding);
        this.material = material;
    }

    @Override
    public Material getMaterial() {
        return this.material;
    }
}
