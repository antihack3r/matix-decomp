// 
// Decompiled by Procyon v0.6.0
// 

package de.paxii.clarinet.util.notifications;

import net.minecraft.client.gui.ScaledResolution;
import java.util.Collection;
import de.paxii.clarinet.util.objects.IntObject;
import de.paxii.clarinet.Wrapper;
import de.paxii.clarinet.event.events.gui.RenderGuiScreenEvent;
import de.paxii.clarinet.event.EventHandler;
import de.paxii.clarinet.event.events.game.IngameTickEvent;
import java.util.ArrayList;

public class NotificationRenderer
{
    private final NotificationManager notificationManager;
    private final ArrayList<Notification> oldNotifications;
    
    public NotificationRenderer(final NotificationManager notificationManager) {
        this.notificationManager = notificationManager;
        this.oldNotifications = new ArrayList<Notification>();
    }
    
    @EventHandler
    private void onIngameTick(final IngameTickEvent event) {
        this.renderNotifications();
    }
    
    @EventHandler
    private void onRenderGuiScreen(final RenderGuiScreenEvent event) {
        this.renderNotifications();
    }
    
    private void renderNotifications() {
        if (!this.notificationManager.getNotifications().isEmpty()) {
            final ScaledResolution scaledResolution = Wrapper.getScaledResolution();
            final IntObject posY = new IntObject(20);
            this.notificationManager.getNotifications().forEach(notification -> {
                if (notification.shouldDisplay()) {
                    final int textWidth = Wrapper.getFontRenderer().getStringWidth(notification.getText());
                    final int posX = scaledResolution.getScaledWidth() / 2 - textWidth / 2;
                    Wrapper.getFontRenderer().drawString(notification.getText(), posX, posY.getInteger(), notification.getPriority().getColor());
                    posY.add(15);
                }
                else {
                    this.oldNotifications.add(notification);
                }
                return;
            });
            this.notificationManager.getNotifications().removeAll(this.oldNotifications);
            this.oldNotifications.clear();
        }
    }
}
