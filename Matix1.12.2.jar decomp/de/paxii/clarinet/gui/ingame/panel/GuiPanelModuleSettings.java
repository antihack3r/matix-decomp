// 
// Decompiled by Procyon v0.6.0
// 

package de.paxii.clarinet.gui.ingame.panel;

import de.paxii.clarinet.module.Module;

public class GuiPanelModuleSettings extends GuiPanel
{
    public GuiPanelModuleSettings(final Module module, final int panelX, final int panelY) {
        super(module.getName() + " Settings", panelX, panelY);
        this.setVisible(false);
        this.setDraggable(false);
        this.setCollapsible(false);
    }
}
