// 
// Decompiled by Procyon v0.6.0
// 

package de.paxii.clarinet.util.notifications;

public enum NotificationPriority
{
    NORMAL(0, -1), 
    GOOD(1, -8323328), 
    WARNING(2, -8960), 
    DANGER(3, -65536);
    
    private int priority;
    private int color;
    
    private NotificationPriority(final int priority, final int color) {
        this.priority = priority;
        this.color = color;
    }
    
    public int getPriority() {
        return this.priority;
    }
    
    public int getColor() {
        return this.color;
    }
}
