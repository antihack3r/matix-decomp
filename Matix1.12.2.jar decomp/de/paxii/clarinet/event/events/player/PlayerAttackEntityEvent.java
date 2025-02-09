// 
// Decompiled by Procyon v0.6.0
// 

package de.paxii.clarinet.event.events.player;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import de.paxii.clarinet.event.events.type.EventCancellable;

public class PlayerAttackEntityEvent extends EventCancellable
{
    private final EntityPlayer sourceEntity;
    private Entity targetEntity;
    
    public PlayerAttackEntityEvent(final EntityPlayer sourceEntity, final Entity targetEntity) {
        this.sourceEntity = sourceEntity;
        this.targetEntity = targetEntity;
    }
    
    public EntityPlayer getSource() {
        return this.sourceEntity;
    }
    
    public Entity getTarget() {
        return this.targetEntity;
    }
    
    public void setTarget(final Entity targetEntity) {
        this.targetEntity = targetEntity;
    }
}
