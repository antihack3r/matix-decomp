// 
// Decompiled by Procyon v0.6.0
// 

package de.paxii.clarinet.event.events.game;

import de.paxii.clarinet.event.events.Event;

public class KeyPressedEvent implements Event
{
    private final int keyPressed;
    
    public KeyPressedEvent(final int keyPressed) {
        this.keyPressed = keyPressed;
    }
    
    public int getKey() {
        return this.keyPressed;
    }
}
