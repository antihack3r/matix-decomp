// 
// Decompiled by Procyon v0.6.0
// 

package de.paxii.clarinet.util.module.settings;

import java.util.Iterator;
import de.paxii.clarinet.util.settings.ClientSettingValueManager;
import java.util.Map;
import de.paxii.clarinet.util.settings.ClientSetting;
import de.paxii.clarinet.module.Module;
import de.paxii.clarinet.util.settings.ClientSettingSettingsObject;
import java.util.HashMap;

public class ModuleSettingsObject
{
    @Deprecated
    private String moduleName;
    private int moduleKey;
    private boolean enabled;
    private HashMap<String, ClientSettingSettingsObject> moduleSettings;
    private HashMap<String, ValueBaseSettingsObject> moduleValues;
    
    public ModuleSettingsObject(final Module module) {
        this(module.getName(), module.getKey(), module.isEnabled(), module.getModuleSettings(), module.getModuleValues());
    }
    
    public ModuleSettingsObject(final String moduleName, final int moduleKey, final boolean enabled, final HashMap<String, ClientSetting> moduleSettings, final HashMap<String, ValueBase> moduleValues) {
        this.moduleName = moduleName;
        this.moduleKey = moduleKey;
        this.enabled = enabled;
        this.moduleSettings = new HashMap<String, ClientSettingSettingsObject>();
        this.moduleValues = new HashMap<String, ValueBaseSettingsObject>();
        moduleSettings.forEach((settingsName, setting) -> {
            final ClientSettingSettingsObject clientSettingSettingsObject = this.moduleSettings.put(settingsName, new ClientSettingSettingsObject(setting.getName(), setting.getValue()));
            return;
        });
        moduleValues.forEach((valueName, valueBase) -> {
            final ValueBaseSettingsObject valueBaseSettingsObject = this.moduleValues.put(valueName, new ValueBaseSettingsObject(valueBase));
        });
    }
    
    public void restoreToModule(final Module module) {
        for (final Map.Entry<String, ClientSettingSettingsObject> settingsEntry : this.getModuleSettings().entrySet()) {
            settingsEntry.getValue().setValue(ClientSettingValueManager.patchValue(settingsEntry.getValue().getValue()));
            module.setValue(settingsEntry.getKey(), settingsEntry.getValue().getValue());
        }
        this.getModuleValues().forEach((k, v) -> {
            if (ValueBase.doesValueExist(v.getName())) {
                final ValueBase vb = ValueBase.getValueBase(v.getName());
                if (vb != null) {
                    vb.setValue(v.getValue());
                    vb.setMin(v.getMin());
                    vb.setMax(v.getMax());
                    vb.setName(v.getName());
                }
            }
            return;
        });
        module.setKey(this.getModuleKey());
    }
    
    @Deprecated
    public String getModuleName() {
        return this.moduleName;
    }
    
    public int getModuleKey() {
        return this.moduleKey;
    }
    
    public void setModuleKey(final int moduleKey) {
        this.moduleKey = moduleKey;
    }
    
    public boolean isEnabled() {
        return this.enabled;
    }
    
    public void setEnabled(final boolean enabled) {
        this.enabled = enabled;
    }
    
    public HashMap<String, ClientSettingSettingsObject> getModuleSettings() {
        return this.moduleSettings;
    }
    
    public HashMap<String, ValueBaseSettingsObject> getModuleValues() {
        return this.moduleValues;
    }
}
