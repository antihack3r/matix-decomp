// 
// Decompiled by Procyon v0.6.0
// 

package de.paxii.clarinet.util.notifications;

public class Notification
{
    private final long initialTime;
    private String text;
    private NotificationPriority priority;
    private long time;
    
    public Notification(final String text) {
        this(text, NotificationPriority.NORMAL, 3000L);
    }
    
    public Notification(final String text, final long time) {
        this(text, NotificationPriority.NORMAL, time);
    }
    
    public Notification(final String text, final NotificationPriority notificationPriority) {
        this(text, notificationPriority, 3000L);
    }
    
    public Notification(final String text, final NotificationPriority notificationPriority, final long time) {
        this.text = text;
        this.priority = notificationPriority;
        this.time = time;
        this.initialTime = System.currentTimeMillis();
    }
    
    public boolean shouldDisplay() {
        return System.currentTimeMillis() <= this.initialTime + this.time;
    }
    
    public String getText() {
        return this.text;
    }
    
    public NotificationPriority getPriority() {
        return this.priority;
    }
    
    public long getTime() {
        return this.time;
    }
}
