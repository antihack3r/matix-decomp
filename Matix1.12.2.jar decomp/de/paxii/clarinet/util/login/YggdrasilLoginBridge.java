// 
// Decompiled by Procyon v0.6.0
// 

package de.paxii.clarinet.util.login;

import de.paxii.clarinet.gui.menu.login.AltObject;
import com.mojang.authlib.UserAuthentication;
import com.mojang.authlib.exceptions.AuthenticationException;
import de.paxii.clarinet.Wrapper;
import com.mojang.authlib.yggdrasil.YggdrasilUserAuthentication;
import com.mojang.authlib.Agent;
import com.mojang.authlib.yggdrasil.YggdrasilAuthenticationService;
import java.util.UUID;
import java.net.Proxy;
import net.minecraft.util.Session;

public class YggdrasilLoginBridge
{
    public static Session loginWithPassword(final String username, final String password) {
        final UserAuthentication auth = (UserAuthentication)new YggdrasilUserAuthentication(new YggdrasilAuthenticationService(Proxy.NO_PROXY, UUID.randomUUID().toString()), Agent.MINECRAFT);
        auth.setUsername(username);
        auth.setPassword(password);
        try {
            auth.logIn();
            final String userName = auth.getSelectedProfile().getName();
            final UUID playerUUID = auth.getSelectedProfile().getId();
            final String accessToken = auth.getAuthenticatedToken();
            System.out.println(userName + "'s (UUID: '" + playerUUID.toString() + "') accessToken: " + accessToken);
            final Session session = new Session(userName, playerUUID.toString(), accessToken, username.contains("@") ? "mojang" : "legacy");
            Wrapper.getMinecraft().setSession(session);
            Wrapper.getClient().getMcLeaksManager().setUsingMcLeaks(false);
            return session;
        }
        catch (final AuthenticationException e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public static Session loginWithoutPassword(final String username) {
        Wrapper.getMinecraft().setSession(new Session(username, "", "", "legacy"));
        return Wrapper.getMinecraft().getSession();
    }
    
    public static Session loginWithAlt(final AltObject alt) {
        if (alt.isPremium()) {
            return loginWithPassword(alt.isMojang() ? alt.getEmail() : alt.getUserName(), alt.getPassword());
        }
        return loginWithoutPassword(alt.getUserName());
    }
}
