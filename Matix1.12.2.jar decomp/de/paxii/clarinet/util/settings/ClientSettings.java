// 
// Decompiled by Procyon v0.6.0
// 

package de.paxii.clarinet.util.settings;

import de.paxii.clarinet.util.settings.type.ClientSettingLong;
import de.paxii.clarinet.util.settings.type.ClientSettingBoolean;
import java.util.UUID;
import de.paxii.clarinet.util.settings.type.ClientSettingString;
import java.util.HashMap;

public class ClientSettings
{
    private static HashMap<String, ClientSetting> clientSettings;
    private static ClientSettingString clientFolderPath;
    
    public static void put(final ClientSetting clientSetting) {
        ClientSettings.clientSettings.put(clientSetting.getName(), clientSetting);
    }
    
    public static <T extends ClientSetting> T put(final ClientSetting<T> clientSetting, final Class<T> type) {
        return type.cast(ClientSettings.clientSettings.put(clientSetting.getName(), clientSetting));
    }
    
    public static <T extends ClientSetting> T getSetting(final String settingName, final Class<T> type) {
        return type.cast(ClientSettings.clientSettings.get(settingName));
    }
    
    public static <T> T getValue(final String settingName, final Class<T> type) {
        return type.cast(ClientSettings.clientSettings.get(settingName).getValue());
    }
    
    @Override
    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof ClientSettings)) {
            return false;
        }
        final ClientSettings other = (ClientSettings)o;
        return other.canEqual(this);
    }
    
    protected boolean canEqual(final Object other) {
        return other instanceof ClientSettings;
    }
    
    @Override
    public int hashCode() {
        final int result = 1;
        return result;
    }
    
    @Override
    public String toString() {
        return "ClientSettings()";
    }
    
    public static HashMap<String, ClientSetting> getClientSettings() {
        return ClientSettings.clientSettings;
    }
    
    public static ClientSettingString getClientFolderPath() {
        return ClientSettings.clientFolderPath;
    }
    
    static {
        ClientSettings.clientSettings = new HashMap<String, ClientSetting>();
        put(ClientSettings.clientFolderPath = new ClientSettingString("client.folder", "./resourcepacks/1.12 R3D Craft 256x"));
        put(new ClientSettingString("client.enckey", UUID.randomUUID().toString().replaceAll("-", "")));
        put(new ClientSettingString("client.prefix", "#"));
        put(new ClientSettingString("client.guitheme", "Matix2HD"));
        put(new ClientSettingBoolean("client.hidden", false));
        put(new ClientSettingBoolean("client.update", true));
        put(new ClientSettingLong("category.player", 4292026050L));
        put(new ClientSettingLong("category.world", 4280205480L));
        put(new ClientSettingLong("category.combat", 4290447121L));
        put(new ClientSettingLong("category.render", 4288934704L));
        put(new ClientSettingLong("category.movement", 4292780581L));
        put(new ClientSettingLong("category.other", 4280523153L));
    }
}
