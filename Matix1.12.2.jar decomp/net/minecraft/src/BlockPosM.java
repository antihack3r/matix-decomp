// 
// Decompiled by Procyon v0.6.0
// 

package net.minecraft.src;

import com.google.common.collect.AbstractIterator;
import java.util.Iterator;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.BlockPos;

public class BlockPosM extends BlockPos
{
    private int mx;
    private int my;
    private int mz;
    private int level;
    private BlockPosM[] facings;
    private boolean needsUpdate;
    
    public BlockPosM(final int p_i8_1_, final int p_i8_2_, final int p_i8_3_) {
        this(p_i8_1_, p_i8_2_, p_i8_3_, 0);
    }
    
    public BlockPosM(final double p_i9_1_, final double p_i9_3_, final double p_i9_5_) {
        this(MathHelper.floor(p_i9_1_), MathHelper.floor(p_i9_3_), MathHelper.floor(p_i9_5_));
    }
    
    public BlockPosM(final int p_i10_1_, final int p_i10_2_, final int p_i10_3_, final int p_i10_4_) {
        super(0, 0, 0);
        this.mx = p_i10_1_;
        this.my = p_i10_2_;
        this.mz = p_i10_3_;
        this.level = p_i10_4_;
    }
    
    @Override
    public int getX() {
        return this.mx;
    }
    
    @Override
    public int getY() {
        return this.my;
    }
    
    @Override
    public int getZ() {
        return this.mz;
    }
    
    public void setXyz(final int p_setXyz_1_, final int p_setXyz_2_, final int p_setXyz_3_) {
        this.mx = p_setXyz_1_;
        this.my = p_setXyz_2_;
        this.mz = p_setXyz_3_;
        this.needsUpdate = true;
    }
    
    public void setXyz(final double p_setXyz_1_, final double p_setXyz_3_, final double p_setXyz_5_) {
        this.setXyz(MathHelper.floor(p_setXyz_1_), MathHelper.floor(p_setXyz_3_), MathHelper.floor(p_setXyz_5_));
    }
    
    @Override
    public BlockPos offset(final EnumFacing facing) {
        if (this.level <= 0) {
            return super.offset(facing, 1).toImmutable();
        }
        if (this.facings == null) {
            this.facings = new BlockPosM[EnumFacing.VALUES.length];
        }
        if (this.needsUpdate) {
            this.update();
        }
        final int i = facing.getIndex();
        BlockPosM blockposm = this.facings[i];
        if (blockposm == null) {
            final int j = this.mx + facing.getFrontOffsetX();
            final int k = this.my + facing.getFrontOffsetY();
            final int l = this.mz + facing.getFrontOffsetZ();
            blockposm = new BlockPosM(j, k, l, this.level - 1);
            this.facings[i] = blockposm;
        }
        return blockposm;
    }
    
    @Override
    public BlockPos offset(final EnumFacing facing, final int n) {
        return (n == 1) ? this.offset(facing) : super.offset(facing, n).toImmutable();
    }
    
    private void update() {
        for (int i = 0; i < 6; ++i) {
            final BlockPosM blockposm = this.facings[i];
            if (blockposm != null) {
                final EnumFacing enumfacing = EnumFacing.VALUES[i];
                final int j = this.mx + enumfacing.getFrontOffsetX();
                final int k = this.my + enumfacing.getFrontOffsetY();
                final int l = this.mz + enumfacing.getFrontOffsetZ();
                blockposm.setXyz(j, k, l);
            }
        }
        this.needsUpdate = false;
    }
    
    @Override
    public BlockPos toImmutable() {
        return new BlockPos(this.mx, this.my, this.mz);
    }
    
    public static Iterable<BlockPosM> getAllInBoxMutableM(final BlockPos p_getAllInBoxMutable_0_, final BlockPos p_getAllInBoxMutable_1_) {
        final BlockPos blockpos = new BlockPos(Math.min(p_getAllInBoxMutable_0_.getX(), p_getAllInBoxMutable_1_.getX()), Math.min(p_getAllInBoxMutable_0_.getY(), p_getAllInBoxMutable_1_.getY()), Math.min(p_getAllInBoxMutable_0_.getZ(), p_getAllInBoxMutable_1_.getZ()));
        final BlockPos blockpos2 = new BlockPos(Math.max(p_getAllInBoxMutable_0_.getX(), p_getAllInBoxMutable_1_.getX()), Math.max(p_getAllInBoxMutable_0_.getY(), p_getAllInBoxMutable_1_.getY()), Math.max(p_getAllInBoxMutable_0_.getZ(), p_getAllInBoxMutable_1_.getZ()));
        return new Iterable<BlockPosM>() {
            @Override
            public Iterator iterator() {
                return (Iterator)new AbstractIterator() {
                    private BlockPosM theBlockPosM = null;
                    
                    protected BlockPosM computeNext0() {
                        if (this.theBlockPosM == null) {
                            return this.theBlockPosM = new BlockPosM(blockpos.getX(), blockpos.getY(), blockpos.getZ(), 3);
                        }
                        if (this.theBlockPosM.equals(blockpos2)) {
                            return (BlockPosM)this.endOfData();
                        }
                        int i = this.theBlockPosM.getX();
                        int j = this.theBlockPosM.getY();
                        int k = this.theBlockPosM.getZ();
                        if (i < blockpos2.getX()) {
                            ++i;
                        }
                        else if (j < blockpos2.getY()) {
                            i = blockpos.getX();
                            ++j;
                        }
                        else if (k < blockpos2.getZ()) {
                            i = blockpos.getX();
                            j = blockpos.getY();
                            ++k;
                        }
                        this.theBlockPosM.setXyz(i, j, k);
                        return this.theBlockPosM;
                    }
                    
                    protected Object computeNext() {
                        return this.computeNext0();
                    }
                };
            }
        };
    }
}
