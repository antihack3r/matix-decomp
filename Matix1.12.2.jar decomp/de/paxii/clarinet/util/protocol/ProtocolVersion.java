// 
// Decompiled by Procyon v0.6.0
// 

package de.paxii.clarinet.util.protocol;

public class ProtocolVersion
{
    private static CompatibleVersion currentVersion;
    
    public static int getProtocolVersion() {
        return ProtocolVersion.currentVersion.getProtocolVersion();
    }
    
    public static String getGameVersion() {
        return ProtocolVersion.currentVersion.getGameVersion();
    }
    
    public static void cycleVersion() {
        int currentIndex = ProtocolVersion.currentVersion.ordinal();
        if (currentIndex < CompatibleVersion.values().length - 1) {
            ++currentIndex;
        }
        else {
            currentIndex = 0;
        }
        ProtocolVersion.currentVersion = CompatibleVersion.values()[currentIndex];
    }
    
    static {
        ProtocolVersion.currentVersion = CompatibleVersion._1122;
    }
    
    public enum CompatibleVersion
    {
        _1122(340, "1.12.2");
        
        private int protocolVersion;
        private String gameVersion;
        
        private CompatibleVersion(final int protocolVersion, final String gameVersion) {
            this.protocolVersion = protocolVersion;
            this.gameVersion = gameVersion;
        }
        
        public int getProtocolVersion() {
            return this.protocolVersion;
        }
        
        public String getGameVersion() {
            return this.gameVersion;
        }
    }
}
