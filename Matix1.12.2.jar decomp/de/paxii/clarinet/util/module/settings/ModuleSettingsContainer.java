// 
// Decompiled by Procyon v0.6.0
// 

package de.paxii.clarinet.util.module.settings;

import java.util.ArrayList;

public class ModuleSettingsContainer
{
    private ArrayList<ModuleSettingsObject> moduleSettings;
    
    public ModuleSettingsContainer(final ArrayList<ModuleSettingsObject> moduleSettings) {
        this.moduleSettings = moduleSettings;
    }
    
    public ArrayList<ModuleSettingsObject> getModuleSettings() {
        return this.moduleSettings;
    }
    
    public void setModuleSettings(final ArrayList<ModuleSettingsObject> moduleSettings) {
        this.moduleSettings = moduleSettings;
    }
}
