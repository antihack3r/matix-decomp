// 
// Decompiled by Procyon v0.6.0
// 

package de.paxii.clarinet.gui.ingame.panel.element.elements;

import de.paxii.clarinet.gui.ingame.panel.theme.layout.ElementSpacing;
import de.paxii.clarinet.Wrapper;
import de.paxii.clarinet.util.module.settings.ValueBase;
import de.paxii.clarinet.gui.ingame.panel.element.PanelElement;

public class PanelSlider extends PanelElement
{
    private float dragX;
    private float lastDragX;
    private ValueBase sliderValue;
    private boolean shouldRound;
    private boolean isDragging;
    
    public PanelSlider(final ValueBase sliderValue, final boolean shouldRound) {
        super(110, 15);
        this.sliderValue = sliderValue;
        this.shouldRound = shouldRound;
        this.setValue(sliderValue.getValue());
    }
    
    private void dragSlider(final int mouseX) {
        this.dragX = mouseX - this.lastDragX;
    }
    
    @Override
    public void drawElement(final int elementX, final int elementY, final int mouseX, final int mouseY) {
        this.setElementX(elementX);
        this.setElementY(elementY);
        this.setValue(this.sliderValue.getValue());
        if (this.isDragging()) {
            this.dragSlider(mouseX);
        }
        if (this.dragX < 0.0f) {
            this.dragX = 0.0f;
        }
        if (this.dragX > this.getWidth()) {
            this.dragX = (float)this.getWidth();
        }
        Wrapper.getClickableGui().getCurrentTheme().drawSlider(this.sliderValue, this.getElementX(), this.getElementY(), this.getWidth(), this.getHeight(), this.dragX, this.shouldRound);
    }
    
    @Override
    public void mouseClicked(final int mouseX, final int mouseY, final int buttonClicked) {
        if (buttonClicked == 0 && this.isMouseOverSlider(mouseX, mouseY)) {
            this.lastDragX = mouseX - this.dragX;
            this.setDragging(true);
        }
    }
    
    @Override
    public void mouseMovedOrUp(final int mouseX, final int mouseY, final int buttonClicked) {
        if (buttonClicked == 0) {
            this.setDragging(false);
        }
    }
    
    public boolean isMouseOverSlider(final int mouseX, final int mouseY) {
        final boolean rightX = mouseX >= this.getElementX() + this.dragX - 3.0f && mouseX <= this.getElementX() + this.dragX + 6.0f + 3.0f;
        final boolean rightY = mouseY >= this.getElementY() && mouseY <= this.getElementY() + this.getHeight();
        return rightX && rightY;
    }
    
    public void setValue(float value) {
        this.sliderValue.setValue(value);
        value -= this.sliderValue.getMin();
        final float fraction = this.getWidth() / (this.sliderValue.getMax() - this.sliderValue.getMin());
        this.dragX = fraction * value;
    }
    
    @Override
    public ElementSpacing getElementSpacing() {
        return Wrapper.getClickableGui().getCurrentTheme().getLayout().getSliderLayout();
    }
    
    public ValueBase getSliderValue() {
        return this.sliderValue;
    }
    
    public boolean isShouldRound() {
        return this.shouldRound;
    }
    
    public void setShouldRound(final boolean shouldRound) {
        this.shouldRound = shouldRound;
    }
    
    public boolean isDragging() {
        return this.isDragging;
    }
    
    public void setDragging(final boolean isDragging) {
        this.isDragging = isDragging;
    }
}
