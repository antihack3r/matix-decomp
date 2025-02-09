// 
// Decompiled by Procyon v0.6.0
// 

package de.paxii.clarinet.module;

import de.paxii.clarinet.util.settings.ClientSettings;
import de.paxii.clarinet.util.settings.type.ClientSettingLong;

public enum ModuleCategory
{
    PLAYER, 
    WORLD, 
    COMBAT, 
    RENDER, 
    MOVEMENT, 
    OTHER;
    
    @Override
    public String toString() {
        final String curString = super.toString();
        return curString.charAt(0) + curString.substring(1).toLowerCase();
    }
    
    public int getColor() {
        return ClientSettings.getSetting("category." + this.name().toLowerCase(), ClientSettingLong.class).getValue().intValue();
    }
}
