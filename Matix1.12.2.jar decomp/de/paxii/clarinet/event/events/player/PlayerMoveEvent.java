// 
// Decompiled by Procyon v0.6.0
// 

package de.paxii.clarinet.event.events.player;

import net.minecraft.client.entity.EntityPlayerSP;
import de.paxii.clarinet.event.events.type.EventCancellable;

public class PlayerMoveEvent extends EventCancellable
{
    private EntityPlayerSP player;
    private double motionX;
    private double motionY;
    private double motionZ;
    private double posX;
    private double posY;
    private double posZ;
    
    public PlayerMoveEvent(final EntityPlayerSP player) {
        this.player = player;
        this.motionX = this.player.motionX;
        this.motionY = this.player.motionY;
        this.motionZ = this.player.motionZ;
        this.posX = this.player.posX;
        this.posY = this.player.posY;
        this.posZ = this.player.posZ;
    }
    
    public EntityPlayerSP getPlayer() {
        return this.player;
    }
    
    public double getMotionX() {
        return this.motionX;
    }
    
    public double getMotionY() {
        return this.motionY;
    }
    
    public double getMotionZ() {
        return this.motionZ;
    }
    
    public double getPosX() {
        return this.posX;
    }
    
    public double getPosY() {
        return this.posY;
    }
    
    public double getPosZ() {
        return this.posZ;
    }
    
    public void setPlayer(final EntityPlayerSP player) {
        this.player = player;
    }
    
    public void setMotionX(final double motionX) {
        this.motionX = motionX;
    }
    
    public void setMotionY(final double motionY) {
        this.motionY = motionY;
    }
    
    public void setMotionZ(final double motionZ) {
        this.motionZ = motionZ;
    }
    
    public void setPosX(final double posX) {
        this.posX = posX;
    }
    
    public void setPosY(final double posY) {
        this.posY = posY;
    }
    
    public void setPosZ(final double posZ) {
        this.posZ = posZ;
    }
    
    @Override
    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof PlayerMoveEvent)) {
            return false;
        }
        final PlayerMoveEvent other = (PlayerMoveEvent)o;
        if (!other.canEqual(this)) {
            return false;
        }
        final Object this$player = this.getPlayer();
        final Object other$player = other.getPlayer();
        if (this$player == null) {
            if (other$player == null) {
                return Double.compare(this.getMotionX(), other.getMotionX()) == 0 && Double.compare(this.getMotionY(), other.getMotionY()) == 0 && Double.compare(this.getMotionZ(), other.getMotionZ()) == 0 && Double.compare(this.getPosX(), other.getPosX()) == 0 && Double.compare(this.getPosY(), other.getPosY()) == 0 && Double.compare(this.getPosZ(), other.getPosZ()) == 0;
            }
        }
        else if (this$player.equals(other$player)) {
            return Double.compare(this.getMotionX(), other.getMotionX()) == 0 && Double.compare(this.getMotionY(), other.getMotionY()) == 0 && Double.compare(this.getMotionZ(), other.getMotionZ()) == 0 && Double.compare(this.getPosX(), other.getPosX()) == 0 && Double.compare(this.getPosY(), other.getPosY()) == 0 && Double.compare(this.getPosZ(), other.getPosZ()) == 0;
        }
        return false;
    }
    
    protected boolean canEqual(final Object other) {
        return other instanceof PlayerMoveEvent;
    }
    
    @Override
    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $player = this.getPlayer();
        result = result * 59 + (($player == null) ? 43 : $player.hashCode());
        final long $motionX = Double.doubleToLongBits(this.getMotionX());
        result = result * 59 + (int)($motionX >>> 32 ^ $motionX);
        final long $motionY = Double.doubleToLongBits(this.getMotionY());
        result = result * 59 + (int)($motionY >>> 32 ^ $motionY);
        final long $motionZ = Double.doubleToLongBits(this.getMotionZ());
        result = result * 59 + (int)($motionZ >>> 32 ^ $motionZ);
        final long $posX = Double.doubleToLongBits(this.getPosX());
        result = result * 59 + (int)($posX >>> 32 ^ $posX);
        final long $posY = Double.doubleToLongBits(this.getPosY());
        result = result * 59 + (int)($posY >>> 32 ^ $posY);
        final long $posZ = Double.doubleToLongBits(this.getPosZ());
        result = result * 59 + (int)($posZ >>> 32 ^ $posZ);
        return result;
    }
    
    @Override
    public String toString() {
        return "PlayerMoveEvent(player=" + this.getPlayer() + ", motionX=" + this.getMotionX() + ", motionY=" + this.getMotionY() + ", motionZ=" + this.getMotionZ() + ", posX=" + this.getPosX() + ", posY=" + this.getPosY() + ", posZ=" + this.getPosZ() + ")";
    }
}
