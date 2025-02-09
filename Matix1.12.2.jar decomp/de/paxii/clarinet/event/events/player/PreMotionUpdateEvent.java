// 
// Decompiled by Procyon v0.6.0
// 

package de.paxii.clarinet.event.events.player;

import net.minecraft.entity.player.EntityPlayer;
import de.paxii.clarinet.event.events.type.EventCancellable;

public class PreMotionUpdateEvent extends EventCancellable
{
    private EntityPlayer player;
    
    public PreMotionUpdateEvent(final EntityPlayer entityPlayer) {
        this.player = entityPlayer;
    }
    
    public EntityPlayer getPlayer() {
        return this.player;
    }
}
