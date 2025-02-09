// 
// Decompiled by Procyon v0.6.0
// 

package de.paxii.clarinet.gui.ingame.panel.element.elements;

import java.util.Collection;
import java.util.Arrays;
import de.paxii.clarinet.util.objects.IntObject;
import java.util.ArrayList;
import de.paxii.clarinet.gui.ingame.panel.theme.layout.ElementSpacing;
import de.paxii.clarinet.gui.ingame.panel.element.PanelElement;

public class PanelBlockRow extends PanelElement
{
    private ElementSpacing elementSpacing;
    private ArrayList<PanelBlockButton> blockButtons;
    
    public PanelBlockRow(final PanelBlockButton... blockButtons) {
        super(190, 20);
        this.elementSpacing = new ElementSpacing(190, 20);
        final IntObject width = new IntObject(10);
        Arrays.stream(blockButtons).forEach(blockButton -> width.add(blockButton.getWidth()));
        this.getElementSpacing().setWidth(width.getInteger());
        (this.blockButtons = new ArrayList<PanelBlockButton>()).addAll(Arrays.asList(blockButtons));
    }
    
    @Override
    public void drawElement(final int elementX, final int elementY, final int mouseX, final int mouseY) {
        final IntObject indexX = new IntObject(elementX);
        this.blockButtons.forEach(blockButton -> {
            blockButton.drawElement(indexX.getInteger(), elementY, mouseX, mouseY);
            indexX.add(blockButton.getWidth());
            return;
        });
        super.drawElement(elementX, elementY, mouseX, mouseY);
    }
    
    @Override
    public void mouseClicked(final int mouseX, final int mouseY, final int buttonClicked) {
        this.blockButtons.forEach(blockButton -> blockButton.mouseClicked(mouseX, mouseY, buttonClicked));
        super.mouseClicked(mouseX, mouseY, buttonClicked);
    }
    
    @Override
    public ElementSpacing getElementSpacing() {
        return this.elementSpacing;
    }
    
    public ArrayList<PanelBlockButton> getBlockButtons() {
        return this.blockButtons;
    }
}
