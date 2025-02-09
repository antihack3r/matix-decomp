// 
// Decompiled by Procyon v0.6.0
// 

package optifine.xdelta;

import java.io.IOException;
import java.io.InputStream;

public class SeekableSourceInputStream extends InputStream
{
    SeekableSource ss;
    
    public SeekableSourceInputStream(final SeekableSource ss) {
        this.ss = ss;
    }
    
    @Override
    public int read() throws IOException {
        final byte[] abyte = { 0 };
        this.ss.read(abyte, 0, 1);
        return abyte[0];
    }
    
    @Override
    public int read(final byte[] b, final int off, final int len) throws IOException {
        return this.ss.read(b, off, len);
    }
    
    @Override
    public void close() throws IOException {
        this.ss.close();
    }
}
