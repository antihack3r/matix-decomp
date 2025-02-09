// 
// Decompiled by Procyon v0.6.0
// 

package de.paxii.clarinet.event.events.entity;

import net.minecraft.entity.Entity;
import de.paxii.clarinet.event.events.type.EventCancellable;

public class PostRenderEntityEvent extends EventCancellable
{
    private Entity renderedEntity;
    private float renderPartialTicks;
    private double x;
    private double y;
    private double z;
    private boolean debugRendered;
    
    public PostRenderEntityEvent(final Entity renderedEntity, final double x, final double y, final double z, final float renderPartialTicks) {
        this.renderedEntity = renderedEntity;
        this.x = x;
        this.y = y;
        this.z = z;
        this.renderPartialTicks = renderPartialTicks;
    }
    
    public Entity getRenderedEntity() {
        return this.renderedEntity;
    }
    
    public float getRenderPartialTicks() {
        return this.renderPartialTicks;
    }
    
    public double getX() {
        return this.x;
    }
    
    public double getY() {
        return this.y;
    }
    
    public double getZ() {
        return this.z;
    }
    
    public boolean isDebugRendered() {
        return this.debugRendered;
    }
    
    public void setDebugRendered(final boolean debugRendered) {
        this.debugRendered = debugRendered;
    }
}
