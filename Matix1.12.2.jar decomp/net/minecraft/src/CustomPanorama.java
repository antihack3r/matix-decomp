// 
// Decompiled by Procyon v0.6.0
// 

package net.minecraft.src;

import java.io.InputStream;
import java.io.IOException;
import java.util.List;
import net.minecraft.util.ResourceLocation;
import java.util.ArrayList;
import java.util.Properties;
import java.util.Random;

public class CustomPanorama
{
    private static CustomPanoramaProperties customPanoramaProperties;
    private static final Random random;
    
    public static CustomPanoramaProperties getCustomPanoramaProperties() {
        return CustomPanorama.customPanoramaProperties;
    }
    
    public static void update() {
        CustomPanorama.customPanoramaProperties = null;
        final String[] astring = getPanoramaFolders();
        if (astring.length > 1) {
            final Properties[] aproperties = getPanoramaProperties(astring);
            final int[] aint = getWeights(aproperties);
            final int i = getRandomIndex(aint);
            final String s = astring[i];
            Properties properties = aproperties[i];
            if (properties == null) {
                properties = aproperties[0];
            }
            if (properties == null) {
                properties = new Properties();
            }
            final CustomPanoramaProperties custompanoramaproperties = CustomPanorama.customPanoramaProperties = new CustomPanoramaProperties(s, properties);
        }
    }
    
    private static String[] getPanoramaFolders() {
        final List<String> list = new ArrayList<String>();
        list.add("textures/gui/title/background");
        for (int i = 0; i < 100; ++i) {
            final String s = "optifine/gui/background" + i;
            final String s2 = s + "/panorama_0.png";
            final ResourceLocation resourcelocation = new ResourceLocation(s2);
            if (Config.hasResource(resourcelocation)) {
                list.add(s);
            }
        }
        final String[] astring = list.toArray(new String[list.size()]);
        return astring;
    }
    
    private static Properties[] getPanoramaProperties(final String[] p_getPanoramaProperties_0_) {
        final Properties[] aproperties = new Properties[p_getPanoramaProperties_0_.length];
        for (int i = 0; i < p_getPanoramaProperties_0_.length; ++i) {
            String s = p_getPanoramaProperties_0_[i];
            if (i == 0) {
                s = "optifine/gui";
            }
            else {
                Config.dbg("CustomPanorama: " + s);
            }
            final ResourceLocation resourcelocation = new ResourceLocation(s + "/background.properties");
            try {
                final InputStream inputstream = Config.getResourceStream(resourcelocation);
                if (inputstream != null) {
                    final Properties properties = new Properties();
                    properties.load(inputstream);
                    Config.dbg("CustomPanorama: " + resourcelocation.getResourcePath());
                    aproperties[i] = properties;
                    inputstream.close();
                }
            }
            catch (final IOException ex) {}
        }
        return aproperties;
    }
    
    private static int[] getWeights(final Properties[] p_getWeights_0_) {
        final int[] aint = new int[p_getWeights_0_.length];
        for (int i = 0; i < aint.length; ++i) {
            Properties properties = p_getWeights_0_[i];
            if (properties == null) {
                properties = p_getWeights_0_[0];
            }
            if (properties == null) {
                aint[i] = 1;
            }
            else {
                final String s = properties.getProperty("weight", null);
                aint[i] = Config.parseInt(s, 1);
            }
        }
        return aint;
    }
    
    private static int getRandomIndex(final int[] p_getRandomIndex_0_) {
        final int i = MathUtils.getSum(p_getRandomIndex_0_);
        final int j = CustomPanorama.random.nextInt(i);
        int k = 0;
        for (int l = 0; l < p_getRandomIndex_0_.length; ++l) {
            k += p_getRandomIndex_0_[l];
            if (k > j) {
                return l;
            }
        }
        return p_getRandomIndex_0_.length - 1;
    }
    
    static {
        CustomPanorama.customPanoramaProperties = null;
        random = new Random();
    }
}
