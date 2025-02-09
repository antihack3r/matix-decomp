// 
// Decompiled by Procyon v0.6.0
// 

package optifine.xdelta;

import java.io.IOException;
import java.io.DataOutputStream;

public class GDiffWriter implements DiffWriter
{
    byte[] buf;
    int buflen;
    protected boolean debug;
    DataOutputStream output;
    
    public GDiffWriter(final DataOutputStream os) throws IOException {
        this.buf = new byte[256];
        this.buflen = 0;
        this.debug = false;
        this.output = null;
        (this.output = os).writeByte(209);
        this.output.writeByte(255);
        this.output.writeByte(209);
        this.output.writeByte(255);
        this.output.writeByte(4);
    }
    
    public void setDebug(final boolean flag) {
        this.debug = flag;
    }
    
    @Override
    public void addCopy(final int offset, final int length) throws IOException {
        if (this.buflen > 0) {
            this.writeBuf();
        }
        if (this.debug) {
            System.err.println("COPY off: " + offset + ", len: " + length);
        }
        if (offset > Integer.MAX_VALUE) {
            this.output.writeByte(255);
        }
        else if (offset < 65536) {
            if (length < 256) {
                this.output.writeByte(249);
                this.output.writeShort(offset);
                this.output.writeByte(length);
            }
            else if (length > 65535) {
                this.output.writeByte(251);
                this.output.writeShort(offset);
                this.output.writeInt(length);
            }
            else {
                this.output.writeByte(250);
                this.output.writeShort(offset);
                this.output.writeShort(length);
            }
        }
        else if (length < 256) {
            this.output.writeByte(252);
            this.output.writeInt(offset);
            this.output.writeByte(length);
        }
        else if (length > 65535) {
            this.output.writeByte(254);
            this.output.writeInt(offset);
            this.output.writeInt(length);
        }
        else {
            this.output.writeByte(253);
            this.output.writeInt(offset);
            this.output.writeShort(length);
        }
    }
    
    @Override
    public void addData(final byte b) throws IOException {
        if (this.buflen >= 246) {
            this.writeBuf();
        }
        this.buf[this.buflen] = b;
        ++this.buflen;
    }
    
    private void writeBuf() throws IOException {
        if (this.debug) {
            System.err.print("DATA:");
            for (int i = 0; i < this.buflen; ++i) {
                if (this.buf[i] == 10) {
                    System.err.print("\\n");
                }
                else {
                    System.err.print(String.valueOf((char)this.buf[i]));
                }
            }
            System.err.println("");
        }
        if (this.buflen > 0) {
            this.output.writeByte(this.buflen);
            this.output.write(this.buf, 0, this.buflen);
        }
        this.buflen = 0;
    }
    
    @Override
    public void flush() throws IOException {
        if (this.buflen > 0) {
            this.writeBuf();
        }
        this.buflen = 0;
        this.output.flush();
    }
    
    @Override
    public void close() throws IOException {
        this.flush();
    }
}
