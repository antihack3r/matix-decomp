// 
// Decompiled by Procyon v0.6.0
// 

package net.minecraft.block;

import net.minecraft.block.material.MapColor;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.block.material.Material;

public class BlockNetherrack extends Block
{
    public BlockNetherrack() {
        super(Material.ROCK);
        this.setCreativeTab(CreativeTabs.BUILDING_BLOCKS);
    }
    
    @Override
    public MapColor getMapColor(final IBlockState state, final IBlockAccess worldIn, final BlockPos pos) {
        return MapColor.NETHERRACK;
    }
}
