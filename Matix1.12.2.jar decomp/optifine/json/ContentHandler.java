// 
// Decompiled by Procyon v0.6.0
// 

package optifine.json;

import java.io.IOException;

public interface ContentHandler
{
    void startJSON() throws ParseException, IOException;
    
    void endJSON() throws ParseException, IOException;
    
    boolean startObject() throws ParseException, IOException;
    
    boolean endObject() throws ParseException, IOException;
    
    boolean startObjectEntry(final String p0) throws ParseException, IOException;
    
    boolean endObjectEntry() throws ParseException, IOException;
    
    boolean startArray() throws ParseException, IOException;
    
    boolean endArray() throws ParseException, IOException;
    
    boolean primitive(final Object p0) throws ParseException, IOException;
}
