// 
// Decompiled by Procyon v0.6.0
// 

package optifine.xdelta;

import java.io.IOException;

public interface DiffWriter
{
    void addCopy(final int p0, final int p1) throws IOException;
    
    void addData(final byte p0) throws IOException;
    
    void flush() throws IOException;
    
    void close() throws IOException;
}
