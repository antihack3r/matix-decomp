// 
// Decompiled by Procyon v0.6.0
// 

package net.minecraft.world.gen.feature;

import net.minecraft.block.BlockOldLeaf;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.BlockPlanks;
import net.minecraft.block.BlockOldLog;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import java.util.Random;
import net.minecraft.world.World;
import net.minecraft.block.state.IBlockState;

public class WorldGenBirchTree extends WorldGenAbstractTree
{
    private static final IBlockState LOG;
    private static final IBlockState LEAF;
    private final boolean useExtraRandomHeight;
    
    public WorldGenBirchTree(final boolean notify, final boolean useExtraRandomHeightIn) {
        super(notify);
        this.useExtraRandomHeight = useExtraRandomHeightIn;
    }
    
    @Override
    public boolean generate(final World worldIn, final Random rand, final BlockPos position) {
        int i = rand.nextInt(3) + 5;
        if (this.useExtraRandomHeight) {
            i += rand.nextInt(7);
        }
        boolean flag = true;
        if (position.getY() < 1 || position.getY() + i + 1 > 256) {
            return false;
        }
        for (int j = position.getY(); j <= position.getY() + 1 + i; ++j) {
            int k = 1;
            if (j == position.getY()) {
                k = 0;
            }
            if (j >= position.getY() + 1 + i - 2) {
                k = 2;
            }
            final BlockPos.MutableBlockPos blockpos$mutableblockpos = new BlockPos.MutableBlockPos();
            for (int l = position.getX() - k; l <= position.getX() + k && flag; ++l) {
                for (int i2 = position.getZ() - k; i2 <= position.getZ() + k && flag; ++i2) {
                    if (j >= 0 && j < 256) {
                        if (!this.canGrowInto(worldIn.getBlockState(blockpos$mutableblockpos.setPos(l, j, i2)).getBlock())) {
                            flag = false;
                        }
                    }
                    else {
                        flag = false;
                    }
                }
            }
        }
        if (!flag) {
            return false;
        }
        final Block block = worldIn.getBlockState(position.down()).getBlock();
        if ((block == Blocks.GRASS || block == Blocks.DIRT || block == Blocks.FARMLAND) && position.getY() < 256 - i - 1) {
            this.setDirtAt(worldIn, position.down());
            for (int i3 = position.getY() - 3 + i; i3 <= position.getY() + i; ++i3) {
                final int k2 = i3 - (position.getY() + i);
                for (int l2 = 1 - k2 / 2, i4 = position.getX() - l2; i4 <= position.getX() + l2; ++i4) {
                    final int j2 = i4 - position.getX();
                    for (int k3 = position.getZ() - l2; k3 <= position.getZ() + l2; ++k3) {
                        final int l3 = k3 - position.getZ();
                        if (Math.abs(j2) != l2 || Math.abs(l3) != l2 || (rand.nextInt(2) != 0 && k2 != 0)) {
                            final BlockPos blockpos = new BlockPos(i4, i3, k3);
                            final Material material = worldIn.getBlockState(blockpos).getMaterial();
                            if (material == Material.AIR || material == Material.LEAVES) {
                                this.setBlockAndNotifyAdequately(worldIn, blockpos, WorldGenBirchTree.LEAF);
                            }
                        }
                    }
                }
            }
            for (int j3 = 0; j3 < i; ++j3) {
                final Material material2 = worldIn.getBlockState(position.up(j3)).getMaterial();
                if (material2 == Material.AIR || material2 == Material.LEAVES) {
                    this.setBlockAndNotifyAdequately(worldIn, position.up(j3), WorldGenBirchTree.LOG);
                }
            }
            return true;
        }
        return false;
    }
    
    static {
        LOG = Blocks.LOG.getDefaultState().withProperty(BlockOldLog.VARIANT, BlockPlanks.EnumType.BIRCH);
        LEAF = Blocks.LEAVES.getDefaultState().withProperty(BlockOldLeaf.VARIANT, BlockPlanks.EnumType.BIRCH).withProperty((IProperty<Comparable>)BlockOldLeaf.CHECK_DECAY, false);
    }
}
