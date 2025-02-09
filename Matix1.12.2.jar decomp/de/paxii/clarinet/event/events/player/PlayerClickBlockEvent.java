// 
// Decompiled by Procyon v0.6.0
// 

package de.paxii.clarinet.event.events.player;

import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import de.paxii.clarinet.event.events.type.EventCancellable;

public class PlayerClickBlockEvent extends EventCancellable
{
    private final BlockPos blockPos;
    private final EnumFacing enumFacing;
    
    public PlayerClickBlockEvent(final BlockPos blockPos, final EnumFacing enumFacing) {
        this.blockPos = blockPos;
        this.enumFacing = enumFacing;
    }
    
    public PlayerClickBlockEvent(final int x, final int y, final int z, final EnumFacing enumFacing) {
        this.blockPos = new BlockPos(x, y, z);
        this.enumFacing = enumFacing;
    }
    
    public BlockPos getBlockPos() {
        return this.blockPos;
    }
    
    public int getX() {
        return this.blockPos.getX();
    }
    
    public int getY() {
        return this.blockPos.getY();
    }
    
    public int getZ() {
        return this.blockPos.getZ();
    }
    
    public EnumFacing getEnumFacing() {
        return this.enumFacing;
    }
}
