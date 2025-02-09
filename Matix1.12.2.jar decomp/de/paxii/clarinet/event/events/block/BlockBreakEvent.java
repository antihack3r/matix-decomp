// 
// Decompiled by Procyon v0.6.0
// 

package de.paxii.clarinet.event.events.block;

import net.minecraft.util.math.BlockPos;
import net.minecraft.block.Block;
import de.paxii.clarinet.event.events.Event;

public final class BlockBreakEvent implements Event
{
    private final Block block;
    private final BlockPos blockPos;
    
    public BlockBreakEvent(final Block block, final BlockPos blockPos) {
        this.block = block;
        this.blockPos = blockPos;
    }
    
    public Block getBlock() {
        return this.block;
    }
    
    public BlockPos getBlockPos() {
        return this.blockPos;
    }
    
    @Override
    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof BlockBreakEvent)) {
            return false;
        }
        final BlockBreakEvent other = (BlockBreakEvent)o;
        final Object this$block = this.getBlock();
        final Object other$block = other.getBlock();
        Label_0055: {
            if (this$block == null) {
                if (other$block == null) {
                    break Label_0055;
                }
            }
            else if (this$block.equals(other$block)) {
                break Label_0055;
            }
            return false;
        }
        final Object this$blockPos = this.getBlockPos();
        final Object other$blockPos = other.getBlockPos();
        if (this$blockPos == null) {
            if (other$blockPos == null) {
                return true;
            }
        }
        else if (this$blockPos.equals(other$blockPos)) {
            return true;
        }
        return false;
    }
    
    @Override
    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $block = this.getBlock();
        result = result * 59 + (($block == null) ? 43 : $block.hashCode());
        final Object $blockPos = this.getBlockPos();
        result = result * 59 + (($blockPos == null) ? 43 : $blockPos.hashCode());
        return result;
    }
    
    @Override
    public String toString() {
        return "BlockBreakEvent(block=" + this.getBlock() + ", blockPos=" + this.getBlockPos() + ")";
    }
}
