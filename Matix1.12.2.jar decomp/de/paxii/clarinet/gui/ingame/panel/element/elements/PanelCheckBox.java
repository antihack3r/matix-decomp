// 
// Decompiled by Procyon v0.6.0
// 

package de.paxii.clarinet.gui.ingame.panel.element.elements;

import de.paxii.clarinet.gui.ingame.panel.theme.layout.ElementSpacing;
import de.paxii.clarinet.Wrapper;
import de.paxii.clarinet.gui.ingame.panel.element.AbstractPanelValueElement;

public class PanelCheckBox extends AbstractPanelValueElement<Boolean>
{
    private String caption;
    
    public PanelCheckBox(final String caption, final boolean checked) {
        this.caption = caption;
        this.value = (T)Boolean.valueOf(checked);
    }
    
    @Override
    public void drawElement(final int elementX, final int elementY, final int mouseX, final int mouseY) {
        final boolean buttonHovered = this.isMouseOverButton(mouseX, mouseY);
        Wrapper.getClickableGui().getCurrentTheme().drawCheckBox(this.caption, (boolean)this.value, elementX, elementY, this.getWidth(), this.getHeight(), buttonHovered);
        super.drawElement(elementX, elementY, mouseX, mouseY);
    }
    
    @Override
    public void mouseClicked(final int mouseX, final int mouseY, final int buttonClicked) {
        if (this.isMouseOverButton(mouseX, mouseY)) {
            this.setValue(!this.getValue());
        }
    }
    
    @Override
    public ElementSpacing getElementSpacing() {
        return Wrapper.getClickableGui().getCurrentTheme().getLayout().getCheckboxLayout();
    }
}
