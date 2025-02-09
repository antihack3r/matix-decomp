// 
// Decompiled by Procyon v0.6.0
// 

package de.paxii.clarinet.event.events.type;

import de.paxii.clarinet.event.events.Event;

public class EventCancellable implements Event
{
    private boolean cancelled;
    
    public boolean isCancelled() {
        return this.cancelled;
    }
    
    public void setCancelled(final boolean par1) {
        this.cancelled = par1;
    }
}
