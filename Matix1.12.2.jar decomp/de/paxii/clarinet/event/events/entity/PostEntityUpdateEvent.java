// 
// Decompiled by Procyon v0.6.0
// 

package de.paxii.clarinet.event.events.entity;

import net.minecraft.entity.Entity;
import de.paxii.clarinet.event.events.Event;

public class PostEntityUpdateEvent implements Event
{
    private Entity entity;
    
    public PostEntityUpdateEvent(final Entity entity) {
        this.entity = entity;
    }
    
    public Entity getEntity() {
        return this.entity;
    }
    
    public void setEntity(final Entity entity) {
        this.entity = entity;
    }
    
    @Override
    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof PostEntityUpdateEvent)) {
            return false;
        }
        final PostEntityUpdateEvent other = (PostEntityUpdateEvent)o;
        if (!other.canEqual(this)) {
            return false;
        }
        final Object this$entity = this.getEntity();
        final Object other$entity = other.getEntity();
        if (this$entity == null) {
            if (other$entity == null) {
                return true;
            }
        }
        else if (this$entity.equals(other$entity)) {
            return true;
        }
        return false;
    }
    
    protected boolean canEqual(final Object other) {
        return other instanceof PostEntityUpdateEvent;
    }
    
    @Override
    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $entity = this.getEntity();
        result = result * 59 + (($entity == null) ? 43 : $entity.hashCode());
        return result;
    }
    
    @Override
    public String toString() {
        return "PostEntityUpdateEvent(entity=" + this.getEntity() + ")";
    }
}
