// 
// Decompiled by Procyon v0.6.0
// 

package optifine.json;

import java.io.IOException;
import java.util.Iterator;
import java.io.Writer;
import java.util.List;
import java.util.ArrayList;

public class JSONArray extends ArrayList implements List, JSONAware, JSONStreamAware
{
    private static final long serialVersionUID = 3957988303675231981L;
    
    public static void writeJSONString(final List list, final Writer out) throws IOException {
        if (list == null) {
            out.write("null");
        }
        else {
            boolean flag = true;
            final Iterator iterator = list.iterator();
            out.write(91);
            while (iterator.hasNext()) {
                if (flag) {
                    flag = false;
                }
                else {
                    out.write(44);
                }
                final Object object = iterator.next();
                if (object == null) {
                    out.write("null");
                }
                else {
                    JSONValue.writeJSONString(object, out);
                }
            }
            out.write(93);
        }
    }
    
    @Override
    public void writeJSONString(final Writer out) throws IOException {
        writeJSONString(this, out);
    }
    
    public static String toJSONString(final List list) {
        if (list == null) {
            return "null";
        }
        boolean flag = true;
        final StringBuffer stringbuffer = new StringBuffer();
        final Iterator iterator = list.iterator();
        stringbuffer.append('[');
        while (iterator.hasNext()) {
            if (flag) {
                flag = false;
            }
            else {
                stringbuffer.append(',');
            }
            final Object object = iterator.next();
            if (object == null) {
                stringbuffer.append("null");
            }
            else {
                stringbuffer.append(JSONValue.toJSONString(object));
            }
        }
        stringbuffer.append(']');
        return stringbuffer.toString();
    }
    
    @Override
    public String toJSONString() {
        return toJSONString(this);
    }
    
    @Override
    public String toString() {
        return this.toJSONString();
    }
}
