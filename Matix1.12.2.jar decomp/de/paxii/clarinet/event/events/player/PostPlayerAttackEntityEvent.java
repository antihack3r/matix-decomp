// 
// Decompiled by Procyon v0.6.0
// 

package de.paxii.clarinet.event.events.player;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import de.paxii.clarinet.event.events.Event;

public class PostPlayerAttackEntityEvent implements Event
{
    private EntityPlayer sourceEntity;
    private Entity targetEntity;
    
    public PostPlayerAttackEntityEvent(final EntityPlayer sourceEntity, final Entity targetEntity) {
        this.sourceEntity = sourceEntity;
        this.targetEntity = targetEntity;
    }
    
    public EntityPlayer getSourceEntity() {
        return this.sourceEntity;
    }
    
    public Entity getTargetEntity() {
        return this.targetEntity;
    }
}
