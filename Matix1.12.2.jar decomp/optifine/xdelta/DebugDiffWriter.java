// 
// Decompiled by Procyon v0.6.0
// 

package optifine.xdelta;

import java.io.IOException;

public class DebugDiffWriter implements DiffWriter
{
    byte[] buf;
    int buflen;
    
    public DebugDiffWriter() {
        this.buf = new byte[256];
        this.buflen = 0;
    }
    
    @Override
    public void addCopy(final int offset, final int length) throws IOException {
        if (this.buflen > 0) {
            this.writeBuf();
        }
        System.err.println("COPY off: " + offset + ", len: " + length);
    }
    
    @Override
    public void addData(final byte b) throws IOException {
        if (this.buflen < 256) {
            this.buf[this.buflen++] = b;
        }
        else {
            this.writeBuf();
        }
    }
    
    private void writeBuf() {
        System.err.print("DATA: ");
        for (int i = 0; i < this.buflen; ++i) {
            if (this.buf[i] == 10) {
                System.err.print("\\n");
            }
            else {
                System.err.print(String.valueOf((char)this.buf[i]));
            }
        }
        System.err.println("");
        this.buflen = 0;
    }
    
    @Override
    public void flush() throws IOException {
    }
    
    @Override
    public void close() throws IOException {
    }
}
