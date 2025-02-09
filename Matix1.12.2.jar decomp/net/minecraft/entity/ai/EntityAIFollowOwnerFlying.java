// 
// Decompiled by Procyon v0.6.0
// 

package net.minecraft.entity.ai;

import net.minecraft.block.state.IBlockState;
import net.minecraft.block.material.Material;
import net.minecraft.util.math.BlockPos;
import net.minecraft.entity.passive.EntityTameable;

public class EntityAIFollowOwnerFlying extends EntityAIFollowOwner
{
    public EntityAIFollowOwnerFlying(final EntityTameable p_i47416_1_, final double p_i47416_2_, final float p_i47416_4_, final float p_i47416_5_) {
        super(p_i47416_1_, p_i47416_2_, p_i47416_4_, p_i47416_5_);
    }
    
    @Override
    protected boolean isTeleportFriendlyBlock(final int x, final int p_192381_2_, final int y, final int p_192381_4_, final int p_192381_5_) {
        final IBlockState iblockstate = this.world.getBlockState(new BlockPos(x + p_192381_4_, y - 1, p_192381_2_ + p_192381_5_));
        return (iblockstate.isTopSolid() || iblockstate.getMaterial() == Material.LEAVES) && this.world.isAirBlock(new BlockPos(x + p_192381_4_, y, p_192381_2_ + p_192381_5_)) && this.world.isAirBlock(new BlockPos(x + p_192381_4_, y + 1, p_192381_2_ + p_192381_5_));
    }
}
