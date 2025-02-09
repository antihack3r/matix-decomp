// 
// Decompiled by Procyon v0.6.0
// 

package de.paxii.clarinet.util.login.mcleaks;

import de.paxii.clarinet.Wrapper;
import net.minecraft.network.NetworkManager;

public class MCLeaksManager
{
    private boolean usingMcLeaks;
    private NetworkManager networkManager;
    private String mcLeaksName;
    private String mcLeaksSession;
    private MCLeaksListener mcLeaksListener;
    
    public MCLeaksManager() {
        Wrapper.getEventManager().register(this.mcLeaksListener = new MCLeaksListener());
    }
    
    public boolean isUsingMcLeaks() {
        return this.usingMcLeaks;
    }
    
    public void setUsingMcLeaks(final boolean usingMcLeaks) {
        this.usingMcLeaks = usingMcLeaks;
    }
    
    public NetworkManager getNetworkManager() {
        return this.networkManager;
    }
    
    public void setNetworkManager(final NetworkManager networkManager) {
        this.networkManager = networkManager;
    }
    
    public String getMcLeaksName() {
        return this.mcLeaksName;
    }
    
    public void setMcLeaksName(final String mcLeaksName) {
        this.mcLeaksName = mcLeaksName;
    }
    
    public String getMcLeaksSession() {
        return this.mcLeaksSession;
    }
    
    public void setMcLeaksSession(final String mcLeaksSession) {
        this.mcLeaksSession = mcLeaksSession;
    }
}
