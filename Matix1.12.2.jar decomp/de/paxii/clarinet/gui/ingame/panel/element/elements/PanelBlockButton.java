// 
// Decompiled by Procyon v0.6.0
// 

package de.paxii.clarinet.gui.ingame.panel.element.elements;

import de.paxii.clarinet.gui.ingame.panel.theme.layout.ElementSpacing;
import de.paxii.clarinet.module.render.ModuleXray;
import net.minecraft.block.Block;
import de.paxii.clarinet.Wrapper;
import net.minecraft.block.state.IBlockState;
import de.paxii.clarinet.gui.ingame.panel.element.PanelElement;

public class PanelBlockButton extends PanelElement
{
    private IBlockState iBlockState;
    
    public PanelBlockButton(final IBlockState iBlockState) {
        super(20, 20);
        this.iBlockState = iBlockState;
    }
    
    @Override
    public void drawElement(final int elementX, final int elementY, final int mouseX, final int mouseY) {
        final boolean buttonHovered = this.isMouseOverButton(mouseX, mouseY);
        Wrapper.getClickableGui().getCurrentTheme().drawBlockButton(this.iBlockState, elementX, elementY, this.getWidth(), this.getHeight(), buttonHovered);
        super.drawElement(elementX, elementY, mouseX, mouseY);
    }
    
    @Override
    public void mouseClicked(final int mouseX, final int mouseY, final int clickedButton) {
        if (this.isMouseOverButton(mouseX, mouseY)) {
            final int blockID = Block.getIdFromBlock(this.iBlockState.getBlock());
            if (ModuleXray.getBlockList().contains(blockID)) {
                final ModuleXray moduleXray = (ModuleXray)Wrapper.getModuleManager().getModule("Xray");
                moduleXray.removeBlock(blockID);
            }
            else {
                ModuleXray.getBlockList().add(blockID);
            }
            Wrapper.getMinecraft().renderGlobal.loadRenderers();
        }
    }
    
    @Override
    public ElementSpacing getElementSpacing() {
        return Wrapper.getClickableGui().getCurrentTheme().getLayout().getBlockButtonLayout();
    }
    
    public IBlockState getIBlockState() {
        return this.iBlockState;
    }
}
