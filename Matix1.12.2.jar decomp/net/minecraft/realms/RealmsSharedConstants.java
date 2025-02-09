// 
// Decompiled by Procyon v0.6.0
// 

package net.minecraft.realms;

import net.minecraft.util.ChatAllowedCharacters;
import de.paxii.clarinet.util.protocol.ProtocolVersion;

public class RealmsSharedConstants
{
    public static int NETWORK_PROTOCOL_VERSION;
    public static int TICKS_PER_SECOND;
    public static String VERSION_STRING;
    public static char[] ILLEGAL_FILE_CHARACTERS;
    
    static {
        RealmsSharedConstants.NETWORK_PROTOCOL_VERSION = ProtocolVersion.getProtocolVersion();
        RealmsSharedConstants.TICKS_PER_SECOND = 20;
        RealmsSharedConstants.VERSION_STRING = ProtocolVersion.getGameVersion();
        RealmsSharedConstants.ILLEGAL_FILE_CHARACTERS = ChatAllowedCharacters.ILLEGAL_FILE_CHARACTERS;
    }
}
