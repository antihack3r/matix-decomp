// 
// Decompiled by Procyon v0.6.0
// 

package de.paxii.clarinet.event.events.entity;

import net.minecraft.network.play.server.SPacketEntityVelocity;
import de.paxii.clarinet.event.events.type.EventCancellable;

public class EntityVelocityEvent extends EventCancellable
{
    private int entityID;
    private SPacketEntityVelocity velocityPacket;
    
    public EntityVelocityEvent(final SPacketEntityVelocity velocityPacket) {
        this.entityID = velocityPacket.getEntityID();
        this.velocityPacket = velocityPacket;
    }
    
    public int getEntityID() {
        return this.entityID;
    }
    
    public void setEntityID(final int entityID) {
        this.entityID = entityID;
    }
    
    public SPacketEntityVelocity getVelocityPacket() {
        return this.velocityPacket;
    }
    
    public void setVelocityPacket(final SPacketEntityVelocity velocityPacket) {
        this.velocityPacket = velocityPacket;
    }
}
