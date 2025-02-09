// 
// Decompiled by Procyon v0.6.0
// 

package de.paxii.clarinet.util.player.capesapi;

import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.client.renderer.texture.ITextureObject;
import java.io.File;
import net.minecraft.client.renderer.ThreadDownloadImageData;
import net.minecraft.src.CapeUtils;
import java.awt.image.BufferedImage;
import net.minecraft.client.renderer.IImageBuffer;
import de.paxii.clarinet.Wrapper;
import java.util.Date;
import net.minecraft.util.ResourceLocation;
import java.util.HashMap;
import com.mojang.authlib.GameProfile;
import java.util.ArrayList;

public class CapesApi
{
    private static final String BASE_URL = "http://capesapi.com/api/v1/%s/getCape";
    private static final ArrayList<GameProfile> pendingRequests;
    private static final HashMap<String, ResourceLocation> capeMap;
    
    public static void loadCape(final GameProfile gameProfile) {
        if (hasPendingRequests(gameProfile)) {
            return;
        }
        addCape(gameProfile, null);
        final String url = String.format("http://capesapi.com/api/v1/%s/getCape", gameProfile.getId().toString());
        final ResourceLocation resourceLocation = new ResourceLocation(String.format("capesapi/capes/%s.png", new Date().getTime()));
        final TextureManager textureManager = Wrapper.getMinecraft().getTextureManager();
        final ThreadDownloadImageData threadDownloadImageData = new ThreadDownloadImageData(null, url, null, new IImageBuffer() {
            @Override
            public BufferedImage parseUserSkin(final BufferedImage image) {
                return CapeUtils.parseCape(image);
            }
            
            @Override
            public void skinAvailable() {
                CapesApi.addCape(gameProfile, resourceLocation);
                CapesApi.pendingRequests.remove(gameProfile);
            }
        });
        textureManager.loadTexture(resourceLocation, threadDownloadImageData);
        CapesApi.pendingRequests.add(gameProfile);
    }
    
    public static void addCape(final GameProfile gameProfile, final ResourceLocation resourceLocation) {
        CapesApi.capeMap.put(gameProfile.getId().toString(), resourceLocation);
    }
    
    public static ResourceLocation getCape(final GameProfile gameProfile) {
        return CapesApi.capeMap.getOrDefault(gameProfile.getId().toString(), null);
    }
    
    public static boolean hasCape(final GameProfile gameProfile) {
        final boolean hasCape = CapesApi.capeMap.containsKey(gameProfile.getId().toString());
        final ResourceLocation resourceLocation = CapesApi.capeMap.get(gameProfile.getId().toString());
        if (hasCape && resourceLocation == null && !hasPendingRequests(gameProfile)) {
            loadCape(gameProfile);
            return false;
        }
        return hasCape && resourceLocation != null;
    }
    
    public static void reset() {
        CapesApi.capeMap.keySet().forEach(userId -> {
            final ResourceLocation resourceLocation = CapesApi.capeMap.put(userId, null);
            return;
        });
        CapesApi.pendingRequests.clear();
    }
    
    private static boolean hasPendingRequests(final GameProfile gameProfile) {
        return CapesApi.pendingRequests.contains(gameProfile);
    }
    
    static {
        pendingRequests = new ArrayList<GameProfile>();
        capeMap = new HashMap<String, ResourceLocation>();
    }
}
