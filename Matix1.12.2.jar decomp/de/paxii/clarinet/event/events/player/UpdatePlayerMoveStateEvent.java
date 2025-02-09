// 
// Decompiled by Procyon v0.6.0
// 

package de.paxii.clarinet.event.events.player;

import de.paxii.clarinet.event.events.Event;

public class UpdatePlayerMoveStateEvent implements Event
{
    private float moveStrafe;
    private float moveForward;
    
    public UpdatePlayerMoveStateEvent(final float moveStrafe, final float moveForward) {
        this.moveStrafe = moveStrafe;
        this.moveForward = moveForward;
    }
    
    public float getMoveStrafe() {
        return this.moveStrafe;
    }
    
    public float getMoveForward() {
        return this.moveForward;
    }
    
    public void setMoveStrafe(final float moveStrafe) {
        this.moveStrafe = moveStrafe;
    }
    
    public void setMoveForward(final float moveForward) {
        this.moveForward = moveForward;
    }
    
    @Override
    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof UpdatePlayerMoveStateEvent)) {
            return false;
        }
        final UpdatePlayerMoveStateEvent other = (UpdatePlayerMoveStateEvent)o;
        return other.canEqual(this) && Float.compare(this.getMoveStrafe(), other.getMoveStrafe()) == 0 && Float.compare(this.getMoveForward(), other.getMoveForward()) == 0;
    }
    
    protected boolean canEqual(final Object other) {
        return other instanceof UpdatePlayerMoveStateEvent;
    }
    
    @Override
    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        result = result * 59 + Float.floatToIntBits(this.getMoveStrafe());
        result = result * 59 + Float.floatToIntBits(this.getMoveForward());
        return result;
    }
    
    @Override
    public String toString() {
        return "UpdatePlayerMoveStateEvent(moveStrafe=" + this.getMoveStrafe() + ", moveForward=" + this.getMoveForward() + ")";
    }
}
