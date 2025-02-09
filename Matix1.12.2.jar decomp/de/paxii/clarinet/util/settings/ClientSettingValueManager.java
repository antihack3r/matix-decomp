// 
// Decompiled by Procyon v0.6.0
// 

package de.paxii.clarinet.util.settings;

import de.paxii.clarinet.util.settings.type.ClientSettingLong;
import de.paxii.clarinet.util.settings.type.ClientSettingInteger;

public class ClientSettingValueManager
{
    public static ClientSetting patchValue(final ClientSetting clientSetting) {
        if (clientSetting.getValue() instanceof Double) {
            final double doubleValue = clientSetting.getValue();
            final long longValue = (long)doubleValue;
            final int intValue = (int)longValue;
            if (doubleValue == intValue) {
                return new ClientSettingInteger(clientSetting.getName(), intValue);
            }
            if (doubleValue == longValue) {
                return new ClientSettingLong(clientSetting.getName(), longValue);
            }
        }
        return clientSetting;
    }
    
    public static Object patchValue(final Object value) {
        if (value instanceof Double) {
            final double doubleValue = (double)value;
            final long longValue = (long)doubleValue;
            final int intValue = (int)longValue;
            if (doubleValue == intValue) {
                return intValue;
            }
            if (doubleValue == longValue) {
                return longValue;
            }
        }
        return value;
    }
}
