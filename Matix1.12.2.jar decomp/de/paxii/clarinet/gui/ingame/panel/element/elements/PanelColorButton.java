// 
// Decompiled by Procyon v0.6.0
// 

package de.paxii.clarinet.gui.ingame.panel.element.elements;

import de.paxii.clarinet.gui.ingame.panel.theme.layout.ElementSpacing;
import de.paxii.clarinet.Wrapper;
import de.paxii.clarinet.gui.ingame.panel.theme.themes.LegacyTheme;
import de.paxii.clarinet.gui.ingame.panel.element.PanelElement;

public class PanelColorButton extends PanelElement
{
    private final LegacyTheme.DefaultThemeColorObject colorObject;
    
    public PanelColorButton(final LegacyTheme.DefaultThemeColorObject colorObject) {
        super(90, 12);
        this.colorObject = colorObject;
    }
    
    @Override
    public void drawElement(final int elementX, final int elementY, final int mouseX, final int mouseY) {
        final boolean buttonHovered = this.isMouseOverButton(mouseX, mouseY);
        Wrapper.getClickableGui().getCurrentTheme().drawColorButton(this.colorObject.getColorName(), elementX, elementY, this.getWidth(), this.getHeight(), buttonHovered);
        super.drawElement(elementX, elementY, mouseX, mouseY);
    }
    
    @Override
    public void mouseClicked(final int mouseX, final int mouseY, final int clickedButton) {
        if (this.isMouseOverButton(mouseX, mouseY)) {
            final LegacyTheme clientTheme = (LegacyTheme)Wrapper.getClickableGui().getTheme("Legacy");
            if (clientTheme.getCurrentColor() != this.colorObject) {
                clientTheme.setCurrentColor(this.colorObject);
            }
        }
    }
    
    @Override
    public boolean isMouseOverButton(final int mouseX, final int mouseY) {
        final boolean rightX = mouseX > this.getElementX() && mouseX <= this.getElementX() + this.getWidth();
        final boolean rightY = mouseY > this.getElementY() && mouseY <= this.getElementY() + this.getHeight();
        return rightX && rightY;
    }
    
    @Override
    public ElementSpacing getElementSpacing() {
        return Wrapper.getClickableGui().getCurrentTheme().getLayout().getButtonLayout();
    }
}
