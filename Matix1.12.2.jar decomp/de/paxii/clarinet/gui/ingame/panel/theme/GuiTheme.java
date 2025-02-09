// 
// Decompiled by Procyon v0.6.0
// 

package de.paxii.clarinet.gui.ingame.panel.theme;

import de.paxii.clarinet.gui.ingame.panel.theme.layout.GuiThemeLayout;
import de.paxii.clarinet.util.module.settings.ValueBase;
import net.minecraft.block.state.IBlockState;
import de.paxii.clarinet.module.Module;
import de.paxii.clarinet.gui.ingame.panel.GuiPanel;

public interface GuiTheme
{
    String getName();
    
    void drawPanel(final GuiPanel p0, final int p1, final int p2);
    
    void drawButton(final String p0, final int p1, final int p2, final int p3, final int p4, final boolean p5, final boolean p6);
    
    void drawModuleButton(final Module p0, final int p1, final int p2, final int p3, final int p4, final boolean p5, final boolean p6, final boolean p7);
    
    void drawCheckBox(final String p0, final boolean p1, final int p2, final int p3, final int p4, final int p5, final boolean p6);
    
    void drawDropdown(final String p0, final String[] p1, final int p2, final int p3, final int p4, final int p5, final int p6, final boolean p7, final boolean p8);
    
    void drawBlockButton(final IBlockState p0, final int p1, final int p2, final int p3, final int p4, final boolean p5);
    
    void drawColorButton(final String p0, final int p1, final int p2, final int p3, final int p4, final boolean p5);
    
    default void drawSlider(final ValueBase valueBase, final int sliderX, final int sliderY, final int sliderWidth, final int sliderHeight, final float dragX, final boolean shouldRound) {
        final float max = valueBase.getMax();
        final float min = valueBase.getMin();
        final float fraction = sliderWidth / (max - min);
        valueBase.setValue(shouldRound ? ((int)(dragX / fraction) + min) : (dragX / fraction + min));
    }
    
    GuiThemeLayout getLayout();
}
