// 
// Decompiled by Procyon v0.6.0
// 

package de.paxii.clarinet.util.update;

public class VersionObject
{
    private int clientBuild;
    private String gameVersion;
    private String url;
    
    public VersionObject(final int clientBuild, final String gameVersion, final String url) {
        this.clientBuild = clientBuild;
        this.gameVersion = gameVersion;
        this.url = url;
    }
    
    public int getClientBuild() {
        return this.clientBuild;
    }
    
    public String getGameVersion() {
        return this.gameVersion;
    }
    
    public String getUrl() {
        return this.url;
    }
}
