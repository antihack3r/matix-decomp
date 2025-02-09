// 
// Decompiled by Procyon v0.6.0
// 

package de.paxii.clarinet.event.events.game;

import de.paxii.clarinet.event.events.Event;

public class RenderTickEvent implements Event
{
    private float renderPartialTicks;
    
    public RenderTickEvent(final float renderPartialTicks) {
        this.renderPartialTicks = renderPartialTicks;
    }
    
    public float getRenderPartialTicks() {
        return this.renderPartialTicks;
    }
}
