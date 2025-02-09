// 
// Decompiled by Procyon v0.6.0
// 

package optifine.json;

import java.util.List;
import java.util.Map;
import java.io.Writer;
import java.io.IOException;
import java.io.StringReader;
import java.io.Reader;

public class JSONValue
{
    public static Object parse(final Reader in) {
        try {
            final JSONParser jsonparser = new JSONParser();
            return jsonparser.parse(in);
        }
        catch (final Exception var2) {
            return null;
        }
    }
    
    public static Object parse(final String s) {
        final StringReader stringreader = new StringReader(s);
        return parse(stringreader);
    }
    
    public static Object parseWithException(final Reader in) throws IOException, ParseException {
        final JSONParser jsonparser = new JSONParser();
        return jsonparser.parse(in);
    }
    
    public static Object parseWithException(final String s) throws ParseException {
        final JSONParser jsonparser = new JSONParser();
        return jsonparser.parse(s);
    }
    
    public static void writeJSONString(final Object value, final Writer out) throws IOException {
        if (value == null) {
            out.write("null");
        }
        else if (value instanceof String) {
            out.write(34);
            out.write(escape((String)value));
            out.write(34);
        }
        else if (value instanceof Double) {
            if (!((Double)value).isInfinite() && !((Double)value).isNaN()) {
                out.write(value.toString());
            }
            else {
                out.write("null");
            }
        }
        else if (!(value instanceof Float)) {
            if (value instanceof Number) {
                out.write(value.toString());
            }
            else if (value instanceof Boolean) {
                out.write(value.toString());
            }
            else if (value instanceof JSONStreamAware) {
                ((JSONStreamAware)value).writeJSONString(out);
            }
            else if (value instanceof JSONAware) {
                out.write(((JSONAware)value).toJSONString());
            }
            else if (value instanceof Map) {
                JSONObject.writeJSONString((Map)value, out);
            }
            else if (value instanceof List) {
                JSONArray.writeJSONString((List)value, out);
            }
            else {
                out.write(value.toString());
            }
        }
        else if (!((Float)value).isInfinite() && !((Float)value).isNaN()) {
            out.write(value.toString());
        }
        else {
            out.write("null");
        }
    }
    
    public static String toJSONString(final Object value) {
        if (value == null) {
            return "null";
        }
        if (value instanceof String) {
            return "\"" + escape((String)value) + "\"";
        }
        if (value instanceof Double) {
            return (!((Double)value).isInfinite() && !((Double)value).isNaN()) ? value.toString() : "null";
        }
        if (value instanceof Float) {
            return (!((Float)value).isInfinite() && !((Float)value).isNaN()) ? value.toString() : "null";
        }
        if (value instanceof Number) {
            return value.toString();
        }
        if (value instanceof Boolean) {
            return value.toString();
        }
        if (value instanceof JSONAware) {
            return ((JSONAware)value).toJSONString();
        }
        if (value instanceof Map) {
            return JSONObject.toJSONString((Map)value);
        }
        return (value instanceof List) ? JSONArray.toJSONString((List)value) : value.toString();
    }
    
    public static String escape(final String s) {
        if (s == null) {
            return null;
        }
        final StringBuffer stringbuffer = new StringBuffer();
        escape(s, stringbuffer);
        return stringbuffer.toString();
    }
    
    static void escape(final String s, final StringBuffer sb) {
        for (int i = 0; i < s.length(); ++i) {
            final char c0 = s.charAt(i);
            switch (c0) {
                case '\b': {
                    sb.append("\\b");
                    break;
                }
                case '\t': {
                    sb.append("\\t");
                    break;
                }
                case '\n': {
                    sb.append("\\n");
                    break;
                }
                case '\f': {
                    sb.append("\\f");
                    break;
                }
                case '\r': {
                    sb.append("\\r");
                    break;
                }
                case '\"': {
                    sb.append("\\\"");
                    break;
                }
                case '\\': {
                    sb.append("\\\\");
                    break;
                }
                default: {
                    if ((c0 >= '\0' && c0 <= '\u001f') || (c0 >= '\u007f' && c0 <= '\u009f') || (c0 >= '\u2000' && c0 <= '\u20ff')) {
                        final String hexString = Integer.toHexString(c0);
                        sb.append("\\u");
                        for (int j = 0; j < 4 - hexString.length(); ++j) {
                            sb.append('0');
                        }
                        sb.append(hexString.toUpperCase());
                        break;
                    }
                    sb.append(c0);
                    break;
                }
            }
        }
    }
}
