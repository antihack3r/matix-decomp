// 
// Decompiled by Procyon v0.6.0
// 

package optifine.xdelta;

import java.io.IOException;

public interface SeekableSource
{
    void seek(final long p0) throws IOException;
    
    int read(final byte[] p0, final int p1, final int p2) throws IOException;
    
    void close() throws IOException;
    
    long length() throws IOException;
}
