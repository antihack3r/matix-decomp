// 
// Decompiled by Procyon v0.6.0
// 

package net.minecraft.src;

import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.world.World;
import net.minecraft.client.renderer.texture.ITextureObject;
import java.io.InputStream;
import java.util.List;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.Properties;
import net.minecraft.util.ResourceLocation;
import java.util.ArrayList;

public class CustomSky
{
    private static CustomSkyLayer[][] worldSkyLayers;
    
    public static void reset() {
        CustomSky.worldSkyLayers = null;
    }
    
    public static void update() {
        reset();
        if (Config.isCustomSky()) {
            CustomSky.worldSkyLayers = readCustomSkies();
        }
    }
    
    private static CustomSkyLayer[][] readCustomSkies() {
        final CustomSkyLayer[][] acustomskylayer = new CustomSkyLayer[10][0];
        final String s = "mcpatcher/sky/world";
        int i = -1;
        for (int j = 0; j < acustomskylayer.length; ++j) {
            final String s2 = s + j + "/sky";
            final List list = new ArrayList();
            for (int k = 1; k < 1000; ++k) {
                final String s3 = s2 + k + ".properties";
                try {
                    final ResourceLocation resourcelocation = new ResourceLocation(s3);
                    final InputStream inputstream = Config.getResourceStream(resourcelocation);
                    if (inputstream == null) {
                        break;
                    }
                    final Properties properties = new Properties();
                    properties.load(inputstream);
                    inputstream.close();
                    Config.dbg("CustomSky properties: " + s3);
                    final String s4 = s2 + k + ".png";
                    final CustomSkyLayer customskylayer = new CustomSkyLayer(properties, s4);
                    if (customskylayer.isValid(s3)) {
                        final ResourceLocation resourcelocation2 = new ResourceLocation(customskylayer.source);
                        final ITextureObject itextureobject = TextureUtils.getTexture(resourcelocation2);
                        if (itextureobject == null) {
                            Config.log("CustomSky: Texture not found: " + resourcelocation2);
                        }
                        else {
                            customskylayer.textureId = itextureobject.getGlTextureId();
                            list.add(customskylayer);
                            inputstream.close();
                        }
                    }
                }
                catch (final FileNotFoundException var15) {
                    break;
                }
                catch (final IOException ioexception) {
                    ioexception.printStackTrace();
                }
            }
            if (list.size() > 0) {
                final CustomSkyLayer[] acustomskylayer2 = list.toArray(new CustomSkyLayer[list.size()]);
                acustomskylayer[j] = acustomskylayer2;
                i = j;
            }
        }
        if (i < 0) {
            return null;
        }
        final int l = i + 1;
        final CustomSkyLayer[][] acustomskylayer3 = new CustomSkyLayer[l][0];
        for (int i2 = 0; i2 < acustomskylayer3.length; ++i2) {
            acustomskylayer3[i2] = acustomskylayer[i2];
        }
        return acustomskylayer3;
    }
    
    public static void renderSky(final World p_renderSky_0_, final TextureManager p_renderSky_1_, final float p_renderSky_2_) {
        if (CustomSky.worldSkyLayers != null) {
            final int i = p_renderSky_0_.provider.getDimensionType().getId();
            if (i >= 0 && i < CustomSky.worldSkyLayers.length) {
                final CustomSkyLayer[] acustomskylayer = CustomSky.worldSkyLayers[i];
                if (acustomskylayer != null) {
                    final long j = p_renderSky_0_.getWorldTime();
                    final int k = (int)(j % 24000L);
                    final float f = p_renderSky_0_.getCelestialAngle(p_renderSky_2_);
                    final float f2 = p_renderSky_0_.getRainStrength(p_renderSky_2_);
                    float f3 = p_renderSky_0_.getThunderStrength(p_renderSky_2_);
                    if (f2 > 0.0f) {
                        f3 /= f2;
                    }
                    for (int l = 0; l < acustomskylayer.length; ++l) {
                        final CustomSkyLayer customskylayer = acustomskylayer[l];
                        if (customskylayer.isActive(p_renderSky_0_, k)) {
                            customskylayer.render(k, f, f2, f3);
                        }
                    }
                    final float f4 = 1.0f - f2;
                    Blender.clearBlend(f4);
                }
            }
        }
    }
    
    public static boolean hasSkyLayers(final World p_hasSkyLayers_0_) {
        if (CustomSky.worldSkyLayers == null) {
            return false;
        }
        if (Config.getGameSettings().renderDistanceChunks < 8) {
            return false;
        }
        final int i = p_hasSkyLayers_0_.provider.getDimensionType().getId();
        if (i >= 0 && i < CustomSky.worldSkyLayers.length) {
            final CustomSkyLayer[] acustomskylayer = CustomSky.worldSkyLayers[i];
            return acustomskylayer != null && acustomskylayer.length > 0;
        }
        return false;
    }
    
    static {
        CustomSky.worldSkyLayers = null;
    }
}
