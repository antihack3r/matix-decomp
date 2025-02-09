// 
// Decompiled by Procyon v0.6.0
// 

package de.paxii.clarinet.gui.ingame.settings;

import de.paxii.clarinet.gui.ingame.panel.GuiPanel;
import java.util.ArrayList;
import java.util.HashMap;

public class PanelSettingsContainer
{
    private HashMap<String, PanelSettingsObject> panelSettings;
    
    public PanelSettingsContainer(final ArrayList<GuiPanel> panels) {
        this.panelSettings = new HashMap<String, PanelSettingsObject>();
        panels.forEach(panel -> {
            final PanelSettingsObject panelSettingsObject = this.panelSettings.put(panel.getPanelName(), new PanelSettingsObject(panel));
        });
    }
    
    public PanelSettingsContainer(final HashMap<String, PanelSettingsObject> panelSettings) {
        this.panelSettings = panelSettings;
    }
    
    public HashMap<String, PanelSettingsObject> getPanelSettings() {
        return this.panelSettings;
    }
    
    public void setPanelSettings(final HashMap<String, PanelSettingsObject> panelSettings) {
        this.panelSettings = panelSettings;
    }
}
