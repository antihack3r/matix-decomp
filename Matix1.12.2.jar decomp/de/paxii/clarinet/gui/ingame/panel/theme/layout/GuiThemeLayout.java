// 
// Decompiled by Procyon v0.6.0
// 

package de.paxii.clarinet.gui.ingame.panel.theme.layout;

import de.paxii.clarinet.gui.ingame.panel.element.PanelElement;

public interface GuiThemeLayout
{
    ElementSpacing getPanelLayout();
    
    ElementSpacing getButtonLayout();
    
    default ElementSpacing getModuleButtonLayout() {
        return this.getButtonLayout();
    }
    
    default ElementSpacing getCheckboxLayout() {
        return this.getButtonLayout();
    }
    
    default ElementSpacing getDropdownLayout() {
        return this.getButtonLayout();
    }
    
    ElementSpacing getBlockButtonLayout();
    
    ElementSpacing getSliderLayout();
    
    default boolean isMouseOverElement(final PanelElement panelElement, final int mouseX, final int mouseY) {
        final boolean rightX = mouseX > panelElement.getElementX() && mouseX <= panelElement.getElementX() + panelElement.getWidth();
        final boolean rightY = mouseY > panelElement.getElementY() && mouseY <= panelElement.getElementY() + panelElement.getHeight();
        return rightX && rightY;
    }
}
