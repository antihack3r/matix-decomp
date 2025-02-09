// 
// Decompiled by Procyon v0.6.0
// 

package de.paxii.clarinet.gui.ingame.settings;

import de.paxii.clarinet.gui.ingame.panel.GuiPanel;

public class PanelSettingsObject
{
    private int posX;
    private int posY;
    private boolean opened;
    private boolean pinned;
    private boolean visible;
    
    public PanelSettingsObject(final GuiPanel guiPanel) {
        this.posX = guiPanel.getPanelX();
        this.posY = guiPanel.getPanelY();
        this.opened = guiPanel.isOpened();
        this.pinned = guiPanel.isPinned();
        this.visible = guiPanel.isVisible();
    }
    
    public int getPosX() {
        return this.posX;
    }
    
    public int getPosY() {
        return this.posY;
    }
    
    public boolean isOpened() {
        return this.opened;
    }
    
    public boolean isPinned() {
        return this.pinned;
    }
    
    public boolean isVisible() {
        return this.visible;
    }
}
