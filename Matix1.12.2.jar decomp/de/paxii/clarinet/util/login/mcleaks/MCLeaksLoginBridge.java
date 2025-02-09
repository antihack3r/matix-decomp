// 
// Decompiled by Procyon v0.6.0
// 

package de.paxii.clarinet.util.login.mcleaks;

import java.util.regex.Matcher;
import java.util.ArrayList;
import java.util.regex.Pattern;
import java.util.HashMap;
import de.paxii.clarinet.Wrapper;
import de.paxii.clarinet.util.web.JsonFetcher;
import net.minecraft.util.Session;

public class MCLeaksLoginBridge
{
    private static final String endPoint = "https://auth.mcleaks.net/v1/%s";
    
    public static Session loginWithToken(final String token) {
        final MCLeaksResponse mcLeaksResponse = JsonFetcher.post(String.format("https://auth.mcleaks.net/v1/%s", "redeem"), String.format("{\"token\": \"%s\"}", token), MCLeaksResponse.class);
        if (mcLeaksResponse != null && mcLeaksResponse.isSuccess()) {
            final Session session = new Session(mcLeaksResponse.getUsername(), "", "", "mojang");
            Wrapper.getMinecraft().setSession(session);
            Wrapper.getClient().getMcLeaksManager().setMcLeaksName(mcLeaksResponse.getUsername());
            Wrapper.getClient().getMcLeaksManager().setMcLeaksSession(mcLeaksResponse.getSession());
            Wrapper.getClient().getMcLeaksManager().setUsingMcLeaks(true);
            return session;
        }
        return null;
    }
    
    @Deprecated
    public static Session loginWithNewToken() {
        final HashMap<String, String> requestProperties = new HashMap<String, String>();
        requestProperties.put("Content-Type", "application/x-www-form-urlencoded");
        JsonFetcher.post("https://mcleaks.net/get", "posttype=true", String.class, requestProperties);
        final String response = JsonFetcher.get("https://mcleaks.net/get", String.class);
        final Pattern pattern = Pattern.compile("<input type=\"text\" class=\"form-control\" value=\"(.*?)\">");
        final Matcher matcher = pattern.matcher(response);
        final ArrayList<String> inputFields = new ArrayList<String>();
        while (matcher.find()) {
            inputFields.add(matcher.group(1));
        }
        if (inputFields.size() >= 2) {
            return loginWithToken(inputFields.get(1));
        }
        return null;
    }
    
    public static void joinServer(final String session, final String userName, final String server, final String serverHash, final Runnable callBack) {
        final HashMap<String, String> requestData = new HashMap<String, String>();
        final HashMap<String, String> requestProperties = new HashMap<String, String>();
        requestData.put("session", session);
        requestData.put("mcname", userName);
        requestData.put("server", server);
        requestData.put("serverhash", serverHash);
        requestProperties.put("Content-Type", "application/x-www-form-urlencoded");
        final MCLeaksResponse mcLeaksResponse = JsonFetcher.post(String.format("https://auth.mcleaks.net/v1/%s", "joinserver"), requestData, MCLeaksResponse.class, requestProperties);
        if (mcLeaksResponse.isSuccess()) {
            callBack.run();
        }
    }
}
