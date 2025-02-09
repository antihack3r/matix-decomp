// 
// Decompiled by Procyon v0.6.0
// 

package de.paxii.clarinet.util.notifications;

import de.paxii.clarinet.Wrapper;
import java.util.ArrayList;

public class NotificationManager
{
    private final NotificationRenderer notificationRenderer;
    private ArrayList<Notification> notifications;
    
    public NotificationManager() {
        this.notifications = new ArrayList<Notification>();
        this.notificationRenderer = new NotificationRenderer(this);
        Wrapper.getEventManager().register(this.notificationRenderer);
    }
    
    public void addNotification(final String text) {
        this.getNotifications().add(new Notification(text));
    }
    
    public void addNotification(final String text, final long time) {
        this.getNotifications().add(new Notification(text, time));
    }
    
    public void addNotification(final String text, final NotificationPriority notificationPriority) {
        this.getNotifications().add(new Notification(text, notificationPriority));
    }
    
    public void addNotification(final String text, final NotificationPriority notificationPriority, final long time) {
        this.getNotifications().add(new Notification(text, notificationPriority, time));
    }
    
    public ArrayList<Notification> getNotifications() {
        return this.notifications;
    }
}
