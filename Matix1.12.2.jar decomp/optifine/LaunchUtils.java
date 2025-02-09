// 
// Decompiled by Procyon v0.6.0
// 

package optifine;

import java.lang.reflect.Field;
import java.util.Map;

public class LaunchUtils
{
    private static Boolean forgeServer;
    
    public static boolean isForgeServer() {
        if (LaunchUtils.forgeServer == null) {
            try {
                final Class oclass = Class.forName("net.minecraft.launchwrapper.Launch");
                final Field field = oclass.getField("blackboard");
                final Map<String, Object> map = (Map<String, Object>)field.get(null);
                final Map<String, String> map2 = map.get("launchArgs");
                final String s = map2.get("--accessToken");
                final String s2 = map2.get("--version");
                final boolean flag = s == null && Utils.equals(s2, "UnknownFMLProfile");
                LaunchUtils.forgeServer = flag;
            }
            catch (final Throwable throwable) {
                System.out.println("Error checking Forge server: " + throwable.getClass().getName() + ": " + throwable.getMessage());
                LaunchUtils.forgeServer = Boolean.FALSE;
            }
        }
        return LaunchUtils.forgeServer;
    }
    
    static {
        LaunchUtils.forgeServer = null;
    }
}
