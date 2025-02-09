// 
// Decompiled by Procyon v0.6.0
// 

package de.paxii.clarinet.event.events.entity;

import net.minecraft.entity.Entity;
import de.paxii.clarinet.event.events.Event;

public class EntityMoveEvent implements Event
{
    private Entity entity;
    private double x;
    private double y;
    private double z;
    private boolean noClip;
    
    public EntityMoveEvent(final Entity entity, final double x, final double y, final double z, final boolean noClip) {
        this.entity = entity;
        this.x = x;
        this.y = y;
        this.z = z;
        this.noClip = noClip;
    }
    
    public Entity getEntity() {
        return this.entity;
    }
    
    public double getX() {
        return this.x;
    }
    
    public double getY() {
        return this.y;
    }
    
    public double getZ() {
        return this.z;
    }
    
    public boolean isNoClip() {
        return this.noClip;
    }
    
    public void setEntity(final Entity entity) {
        this.entity = entity;
    }
    
    public void setX(final double x) {
        this.x = x;
    }
    
    public void setY(final double y) {
        this.y = y;
    }
    
    public void setZ(final double z) {
        this.z = z;
    }
    
    public void setNoClip(final boolean noClip) {
        this.noClip = noClip;
    }
    
    @Override
    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof EntityMoveEvent)) {
            return false;
        }
        final EntityMoveEvent other = (EntityMoveEvent)o;
        if (!other.canEqual(this)) {
            return false;
        }
        final Object this$entity = this.getEntity();
        final Object other$entity = other.getEntity();
        if (this$entity == null) {
            if (other$entity == null) {
                return Double.compare(this.getX(), other.getX()) == 0 && Double.compare(this.getY(), other.getY()) == 0 && Double.compare(this.getZ(), other.getZ()) == 0 && this.isNoClip() == other.isNoClip();
            }
        }
        else if (this$entity.equals(other$entity)) {
            return Double.compare(this.getX(), other.getX()) == 0 && Double.compare(this.getY(), other.getY()) == 0 && Double.compare(this.getZ(), other.getZ()) == 0 && this.isNoClip() == other.isNoClip();
        }
        return false;
    }
    
    protected boolean canEqual(final Object other) {
        return other instanceof EntityMoveEvent;
    }
    
    @Override
    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $entity = this.getEntity();
        result = result * 59 + (($entity == null) ? 43 : $entity.hashCode());
        final long $x = Double.doubleToLongBits(this.getX());
        result = result * 59 + (int)($x >>> 32 ^ $x);
        final long $y = Double.doubleToLongBits(this.getY());
        result = result * 59 + (int)($y >>> 32 ^ $y);
        final long $z = Double.doubleToLongBits(this.getZ());
        result = result * 59 + (int)($z >>> 32 ^ $z);
        result = result * 59 + (this.isNoClip() ? 79 : 97);
        return result;
    }
    
    @Override
    public String toString() {
        return "EntityMoveEvent(entity=" + this.getEntity() + ", x=" + this.getX() + ", y=" + this.getY() + ", z=" + this.getZ() + ", noClip=" + this.isNoClip() + ")";
    }
}
