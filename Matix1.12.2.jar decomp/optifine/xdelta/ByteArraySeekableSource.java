// 
// Decompiled by Procyon v0.6.0
// 

package optifine.xdelta;

import java.io.IOException;

public class ByteArraySeekableSource implements SeekableSource
{
    byte[] source;
    long lastPos;
    
    public ByteArraySeekableSource(final byte[] source) {
        this.lastPos = 0L;
        this.source = source;
    }
    
    @Override
    public void seek(final long pos) throws IOException {
        this.lastPos = pos;
    }
    
    @Override
    public int read(final byte[] b, final int off, int len) throws IOException {
        final int i = this.source.length - (int)this.lastPos;
        if (i <= 0) {
            return -1;
        }
        if (i < len) {
            len = i;
        }
        System.arraycopy(this.source, (int)this.lastPos, b, off, len);
        this.lastPos += len;
        return len;
    }
    
    @Override
    public long length() throws IOException {
        return this.source.length;
    }
    
    @Override
    public void close() throws IOException {
        this.source = null;
    }
}
