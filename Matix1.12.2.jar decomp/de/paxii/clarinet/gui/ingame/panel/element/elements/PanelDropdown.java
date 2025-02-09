// 
// Decompiled by Procyon v0.6.0
// 

package de.paxii.clarinet.gui.ingame.panel.element.elements;

import de.paxii.clarinet.gui.ingame.panel.theme.layout.ElementSpacing;
import de.paxii.clarinet.Wrapper;
import java.util.Collection;
import de.paxii.clarinet.gui.ingame.panel.element.AbstractPanelValueElement;

public class PanelDropdown extends AbstractPanelValueElement<String>
{
    private int defaultElementHeight;
    private String[] values;
    private boolean opened;
    
    public PanelDropdown(final String value, final Collection<String> values) {
        this(value, values.toArray(new String[values.size()]));
    }
    
    public PanelDropdown(final String value, final String[] values) {
        this.defaultElementHeight = 12;
        this.value = (T)value;
        this.values = values;
        this.defaultElementHeight = this.getElementSpacing().getHeight();
    }
    
    @Override
    public void drawElement(final int elementX, final int elementY, final int mouseX, final int mouseY) {
        Wrapper.getClickableGui().getCurrentTheme().drawDropdown((String)this.value, this.values, this.getElementX(), this.getElementY(), this.getWidth(), this.getHeight(), this.defaultElementHeight, this.opened, this.isMouseOverButton(mouseX, mouseY));
        super.drawElement(elementX, elementY, mouseX, mouseY);
    }
    
    @Override
    public void mouseClicked(final int mouseX, final int mouseY, final int buttonClicked) {
        if (this.isMouseOverButton(mouseX, mouseY)) {
            this.setOpened(!this.opened);
        }
        else if (this.opened) {
            final int hovered = this.getHoveredId(mouseX, mouseY);
            if (hovered != -1) {
                this.setValue(this.values[hovered]);
                this.setOpened(false);
            }
        }
        super.mouseClicked(mouseX, mouseY, buttonClicked);
    }
    
    private void setOpened(final boolean opened) {
        this.opened = opened;
        this.getElementSpacing().setHeight(opened ? this.calculateHeight() : this.defaultElementHeight);
    }
    
    @Override
    public boolean isMouseOverButton(final int mouseX, final int mouseY) {
        final boolean rightX = mouseX > this.getElementX() && mouseX <= this.getElementX() + this.getWidth();
        final boolean rightY = mouseY > this.getElementY() && mouseY <= this.getElementY() + this.getHeight();
        return rightX && rightY;
    }
    
    private int getHoveredId(final int mouseX, final int mouseY) {
        int indexY = this.getElementY() + this.getHeight();
        for (int i = 0; i < this.values.length; ++i) {
            final int lowerY = indexY + this.getHeight();
            final boolean hoverX = mouseX >= this.getElementX() && mouseX <= this.getElementX() + this.getWidth();
            final boolean hoverY = mouseY >= indexY && mouseY <= lowerY;
            if (hoverX && hoverY) {
                return i;
            }
            indexY = lowerY;
        }
        return -1;
    }
    
    private int calculateHeight() {
        return this.defaultElementHeight + this.values.length * this.defaultElementHeight;
    }
    
    @Override
    public ElementSpacing getElementSpacing() {
        return Wrapper.getClickableGui().getCurrentTheme().getLayout().getDropdownLayout();
    }
    
    public String[] getValues() {
        return this.values;
    }
    
    public void setValues(final String[] values) {
        this.values = values;
    }
}
