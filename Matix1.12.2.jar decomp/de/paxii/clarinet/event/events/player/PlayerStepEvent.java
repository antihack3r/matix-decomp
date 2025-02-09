// 
// Decompiled by Procyon v0.6.0
// 

package de.paxii.clarinet.event.events.player;

import de.paxii.clarinet.event.events.Event;

public class PlayerStepEvent implements Event
{
    private double stepHeight;
    
    public PlayerStepEvent(final double stepHeight) {
        this.stepHeight = stepHeight;
    }
    
    public double getStepHeight() {
        return this.stepHeight;
    }
}
