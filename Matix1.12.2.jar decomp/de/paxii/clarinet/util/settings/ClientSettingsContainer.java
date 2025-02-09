// 
// Decompiled by Procyon v0.6.0
// 

package de.paxii.clarinet.util.settings;

import java.util.HashMap;

public class ClientSettingsContainer
{
    private HashMap<String, Object> clientSettings;
    
    public ClientSettingsContainer(final HashMap<String, Object> clientSettings) {
        this.clientSettings = clientSettings;
    }
    
    public static ClientSettingsContainer buildContainer(final HashMap<String, ClientSetting> clientSettingHashMap) {
        final ClientSettingsContainer clientSettingsContainer = new ClientSettingsContainer(new HashMap<String, Object>());
        clientSettingHashMap.forEach((k, v) -> clientSettingsContainer.getClientSettings().put(k, v.getValue()));
        return clientSettingsContainer;
    }
    
    public HashMap<String, Object> getClientSettings() {
        return this.clientSettings;
    }
    
    public void setClientSettings(final HashMap<String, Object> clientSettings) {
        this.clientSettings = clientSettings;
    }
}
