// 
// Decompiled by Procyon v0.6.0
// 

package optifine.xdelta;

import java.io.IOException;
import java.io.RandomAccessFile;

public class RandomAccessFileSeekableSource implements SeekableSource
{
    RandomAccessFile raf;
    
    public RandomAccessFileSeekableSource(final RandomAccessFile raf) {
        this.raf = raf;
    }
    
    @Override
    public void seek(final long pos) throws IOException {
        this.raf.seek(pos);
    }
    
    @Override
    public int read(final byte[] b, final int off, final int len) throws IOException {
        return this.raf.read(b, off, len);
    }
    
    @Override
    public long length() throws IOException {
        return this.raf.length();
    }
    
    @Override
    public void close() throws IOException {
        this.raf.close();
    }
}
