// 
// Decompiled by Procyon v0.6.0
// 

package de.paxii.clarinet.gui.ingame.panel.element;

import de.paxii.clarinet.gui.ingame.panel.theme.layout.ElementSpacing;
import net.minecraft.util.EnumActionResult;

public class PanelElement
{
    private int elementX;
    private int elementY;
    
    public PanelElement() {
    }
    
    public PanelElement(final int elementX, final int elementY) {
        this.elementX = elementX;
        this.elementY = elementY;
    }
    
    public void drawElement(final int elementX, final int elementY, final int mouseX, final int mouseY) {
        this.elementX = elementX;
        this.elementY = elementY;
    }
    
    public void mouseClicked(final int mouseX, final int mouseY, final int buttonClicked) {
    }
    
    public void mouseMovedOrUp(final int mouseX, final int mouseY, final int buttonClicked) {
    }
    
    public EnumActionResult keyPressed(final int keyCode) {
        return EnumActionResult.PASS;
    }
    
    public boolean isMouseOverButton(final int mouseX, final int mouseY) {
        final boolean rightX = mouseX > this.getElementX() && mouseX <= this.getElementX() + this.getWidth();
        final boolean rightY = mouseY > this.getElementY() && mouseY <= this.getElementY() + this.getHeight();
        return rightX && rightY;
    }
    
    public int getWidth() {
        return this.getElementSpacing().getWidth();
    }
    
    public int getHeight() {
        return this.getElementSpacing().getHeight();
    }
    
    public ElementSpacing getElementSpacing() {
        return new ElementSpacing(0, 0);
    }
    
    public int getElementX() {
        return this.elementX;
    }
    
    public int getElementY() {
        return this.elementY;
    }
    
    public void setElementX(final int elementX) {
        this.elementX = elementX;
    }
    
    public void setElementY(final int elementY) {
        this.elementY = elementY;
    }
}
