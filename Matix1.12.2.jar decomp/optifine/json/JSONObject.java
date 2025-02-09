// 
// Decompiled by Procyon v0.6.0
// 

package optifine.json;

import java.io.IOException;
import java.util.Iterator;
import java.io.Writer;
import java.util.Map;
import java.util.LinkedHashMap;

public class JSONObject extends LinkedHashMap implements Map, JSONAware, JSONStreamAware
{
    private static final long serialVersionUID = -503443796854799292L;
    
    public static void writeJSONString(final Map map, final Writer out) throws IOException {
        if (map == null) {
            out.write("null");
        }
        else {
            boolean flag = true;
            final Iterator iterator = map.entrySet().iterator();
            out.write(123);
            while (iterator.hasNext()) {
                if (flag) {
                    flag = false;
                }
                else {
                    out.write(44);
                }
                final Map.Entry entry = iterator.next();
                out.write(34);
                out.write(escape(String.valueOf(entry.getKey())));
                out.write(34);
                out.write(58);
                JSONValue.writeJSONString(entry.getValue(), out);
            }
            out.write(125);
        }
    }
    
    @Override
    public void writeJSONString(final Writer out) throws IOException {
        writeJSONString(this, out);
    }
    
    public static String toJSONString(final Map map) {
        if (map == null) {
            return "null";
        }
        final StringBuffer stringbuffer = new StringBuffer();
        boolean flag = true;
        final Iterator iterator = map.entrySet().iterator();
        stringbuffer.append('{');
        while (iterator.hasNext()) {
            if (flag) {
                flag = false;
            }
            else {
                stringbuffer.append(',');
            }
            final Map.Entry entry = iterator.next();
            toJSONString(String.valueOf(entry.getKey()), entry.getValue(), stringbuffer);
        }
        stringbuffer.append('}');
        return stringbuffer.toString();
    }
    
    @Override
    public String toJSONString() {
        return toJSONString(this);
    }
    
    private static String toJSONString(final String key, final Object value, final StringBuffer sb) {
        sb.append('\"');
        if (key == null) {
            sb.append("null");
        }
        else {
            JSONValue.escape(key, sb);
        }
        sb.append('\"').append(':');
        sb.append(JSONValue.toJSONString(value));
        return sb.toString();
    }
    
    @Override
    public String toString() {
        return this.toJSONString();
    }
    
    public static String toString(final String key, final Object value) {
        final StringBuffer stringbuffer = new StringBuffer();
        toJSONString(key, value, stringbuffer);
        return stringbuffer.toString();
    }
    
    public static String escape(final String s) {
        return JSONValue.escape(s);
    }
}
