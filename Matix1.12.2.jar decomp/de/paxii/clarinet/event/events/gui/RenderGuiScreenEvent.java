// 
// Decompiled by Procyon v0.6.0
// 

package de.paxii.clarinet.event.events.gui;

import de.paxii.clarinet.event.events.Event;

public class RenderGuiScreenEvent implements Event
{
    int mouseX;
    int mouseY;
    private float renderPartialTicks;
    
    public RenderGuiScreenEvent(final int mouseX, final int mouseY, final float renderPartialTicks) {
        this.mouseX = mouseX;
        this.mouseY = mouseY;
        this.renderPartialTicks = renderPartialTicks;
    }
    
    public int getMouseX() {
        return this.mouseX;
    }
    
    public int getMouseY() {
        return this.mouseY;
    }
    
    public void setMouseX(final int mouseX) {
        this.mouseX = mouseX;
    }
    
    public void setMouseY(final int mouseY) {
        this.mouseY = mouseY;
    }
    
    public float getRenderPartialTicks() {
        return this.renderPartialTicks;
    }
}
