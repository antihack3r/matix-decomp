// 
// Decompiled by Procyon v0.6.0
// 

package de.paxii.clarinet.gui.ingame.panel.element.elements;

import de.paxii.clarinet.gui.ingame.panel.theme.layout.ElementSpacing;
import de.paxii.clarinet.Wrapper;
import net.minecraft.util.EnumActionResult;
import de.paxii.clarinet.util.function.TwoArgumentsFunction;
import de.paxii.clarinet.gui.ingame.panel.element.PanelElement;

public class PanelKeyBindButton extends PanelElement
{
    private String caption;
    private TwoArgumentsFunction<Boolean, Integer, PanelKeyBindButton> callback;
    private boolean listening;
    
    public PanelKeyBindButton(final String caption, final TwoArgumentsFunction<Boolean, Integer, PanelKeyBindButton> callback) {
        super(90, 12);
        this.caption = caption;
        this.callback = callback;
    }
    
    @Override
    public void mouseClicked(final int mouseX, final int mouseY, final int buttonClicked) {
        if (this.listening) {
            this.keyPressed(buttonClicked - 100);
        }
        else if (this.isMouseOverButton(mouseX, mouseY)) {
            this.listening = true;
        }
        super.mouseClicked(mouseX, mouseY, buttonClicked);
    }
    
    @Override
    public EnumActionResult keyPressed(final int keyCode) {
        if (this.listening) {
            if (!this.callback.apply(keyCode, this)) {
                this.listening = false;
            }
            return EnumActionResult.SUCCESS;
        }
        return EnumActionResult.PASS;
    }
    
    @Override
    public void drawElement(final int elementX, final int elementY, final int mouseX, final int mouseY) {
        Wrapper.getClickableGui().getCurrentTheme().drawButton(this.caption, elementX, elementY, this.getWidth(), this.getHeight(), this.listening, this.isMouseOverButton(mouseX, mouseY));
        super.drawElement(elementX, elementY, mouseX, mouseY);
    }
    
    @Override
    public ElementSpacing getElementSpacing() {
        return Wrapper.getClickableGui().getCurrentTheme().getLayout().getButtonLayout();
    }
    
    public void setCaption(final String caption) {
        this.caption = caption;
    }
}
